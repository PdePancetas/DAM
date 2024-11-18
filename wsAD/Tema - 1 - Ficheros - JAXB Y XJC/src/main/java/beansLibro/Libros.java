package beansLibro;

import java.util.List;

//import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "librosCasa") //Solo si el nodo del xml tiene otro nombre, entonces se especifica
public class Libros {

//	@XmlElements(value = { @XmlElement })
	private List<Libro> libro;

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

	public Libros(List<Libro> libro) {
		super();
		this.libro = libro;
	}

	public Libros() {
		
	}

}
