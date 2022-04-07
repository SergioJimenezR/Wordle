package sergiojimenez.wordle.otros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/*
 * Tras haber descargado el diccionario completo (https://github.com/JorgeDuenasLerin/diccionario-espanol-txt), 
 * procesar para quedarnos solamente con palabras de 5 dígitos en mayúsculas, sin tildes y sin repetir. 
 */
public class GenerarDiccionario {

	public static void main(String[] args) throws FileNotFoundException {

		HashMap<String, Object> map = new HashMap<>();

		Scanner sc = new Scanner(new File("diccionario.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5}")) {
				s = StringUtils.stripAccents(s.toUpperCase());
				if (!map.containsKey(s))
					map.put(s, null);
			}
		}
		sc.close();

		PrintWriter pw = new PrintWriter(new File("diccionario5.txt"));
		List<String> lista = new ArrayList<>(map.keySet());
		Collections.sort(lista);
		for (int i = 0; i < lista.size(); i++)
			pw.println(lista.get(i));
		pw.close();

	}

}
