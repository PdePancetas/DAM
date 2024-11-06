package data;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190456032982999902L;
	private String nombre, descripcion, categoria;
	private Integer cantidadCopias;

	public Libro(String nombre, String descripcion, String categoria, Integer cantidadCopias) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.cantidadCopias = cantidadCopias;
	}

	public Libro() {
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the cantidadCopias
	 */
	public Integer getCantidadCopias() {
		return cantidadCopias;
	}

	/**
	 * @param cantidadCopias the cantidadCopias to set
	 */
	public void setCantidadCopias(Integer cantidadCopias) {
		this.cantidadCopias = cantidadCopias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "\nLibro: "+nombre + "\n\tDescripcion: " + descripcion + "\n\tCategoria: " + categoria
				+ "\n\tStock: " + cantidadCopias + "\n";
	}

	
}
