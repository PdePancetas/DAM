package beans;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"titulo" , "autores", "stock"})
public class Libro {

	private String titulo;
	private ArrayList<String> autores = new ArrayList<>();
	private String autoresCadena = autores.toString();
	private int stock;

	public Libro(String titulo, ArrayList<String> autores, int stock) {
		super();
		this.titulo = titulo;
		this.autores = autores;
		this.stock = stock;
	}
	

	public Libro(String titulo, String autoresCadena, int stock) {
		super();
		this.titulo = titulo;
		this.autoresCadena = autoresCadena;
		this.stock = stock;
	}


	public Libro() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElementWrapper(name = "autores")
	@XmlElement(name = "autor")
	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@XmlTransient
	public String getAutoresCadena() {
		return autoresCadena;
	}

	public void setAutoresCadena(String autoresCadena) {
		this.autoresCadena = autoresCadena;
	}

}
