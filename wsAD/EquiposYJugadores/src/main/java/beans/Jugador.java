package beans;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Serializable {

	/*
	 * 2. Los jugadores tienen nombre, número y posición
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int numero;
	private int posicion;

	public Jugador() {
		super();
	}

	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Jugador(String nombre, int numero, int posicion) {
		super();
		this.nombre = nombre;
		this.numero = numero;
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
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
		Jugador other = (Jugador) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", numero=" + numero + ", posicion=" + posicion + "]";
	}

}
