package sergiojimenez.wordle.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Palabra implements Comparable<Palabra> {

	@Id
	String palabra;

	int longitud;

	public Palabra() {
	}

	public Palabra(String palabra) {
		this.palabra = palabra;
		this.longitud = palabra.length();
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public int compareTo(Palabra o) {
		return this.palabra.compareTo(o.getPalabra());
	}

}
