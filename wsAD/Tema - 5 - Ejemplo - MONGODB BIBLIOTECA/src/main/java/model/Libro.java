package model;

import java.util.List;

public class Libro {

	private String titulo;
	private List<Autor> autores;
	private int precio;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Libro(String titulo, List<Autor> autores, int precio) {
		super();
		this.titulo = titulo;
		this.autores = autores;
		this.precio = precio;
	}

	public Libro() {
		super();
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro \ntitulo=" + titulo + "\nprecio=" + precio + "\nautores=\n" + autores;
	}

}
