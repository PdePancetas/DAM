package Ejercicio5;

import java.util.Objects;

public class Pista {

	private String titulo;
	private int duracion;

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo);
	}

	public Pista(String titulo, int duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pista other = (Pista) obj;
		return Objects.equals(titulo, other.titulo);
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
