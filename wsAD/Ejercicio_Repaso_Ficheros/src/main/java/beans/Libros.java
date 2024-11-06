package beans;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "libros")
public class Libros {

	private ArrayList<Libro> libros = new ArrayList<>();

	@XmlElement(name = "libro")
	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}

	public Libros(ArrayList<Libro> libros) {
		super();
		this.libros = libros;
	}

	public Libros() {
		super();
	}

}
