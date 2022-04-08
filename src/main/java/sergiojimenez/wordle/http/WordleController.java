package sergiojimenez.wordle.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sergiojimenez.wordle.dao.PalabraDAO;
import sergiojimenez.wordle.model.Palabra;

@RestController
@RequestMapping("wordle")
public class WordleController {

	@Autowired
	PalabraDAO palabraDAO;

	@GetMapping("/getNuevaPalabra")
	public String getNuevaPalabra() {
		return palabraDAO.findRandom().getPalabra();
	}

	@PostMapping("/rehacerDiccionario")
	public void rehacerDiccionario() throws FileNotFoundException {
		palabraDAO.deleteAll();
		List<Palabra> lista = generarDiccionario(5);
		for (int i = 0; i < lista.size(); i++) {
			System.out.println((i + 1) + "/" + lista.size());
			palabraDAO.save(lista.get(i));
		}
	}

	/*
	 * Tras haber descargado el diccionario completo
	 * (https://github.com/JorgeDuenasLerin/diccionario-espanol-txt), procesar para
	 * quedarnos solamente con palabras de 5 dígitos en mayúsculas, sin tildes y sin
	 * repetir.
	 */
	public static List<Palabra> generarDiccionario(int longitud) throws FileNotFoundException {
		HashMap<Palabra, Object> map = new HashMap<>();

		Scanner sc = new Scanner(new File("diccionario.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{" + longitud + "}")) {
				s = StringUtils.stripAccents(s.toUpperCase());
				Palabra p = new Palabra(s);
				if (!map.containsKey(p))
					map.put(p, null);
			}
		}
		sc.close();
		List<Palabra> lista = new ArrayList<>(map.keySet());
		Collections.sort(lista);
		return lista;
	}

	public static void imprimirDiccionario(List<String> lista) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("diccionario5.txt"));
		for (int i = 0; i < lista.size(); i++)
			pw.println(lista.get(i));
		pw.close();
	}

}
