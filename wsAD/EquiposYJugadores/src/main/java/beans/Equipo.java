package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class Equipo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 1. Los equipos tienen nombre y conjunto de jugadores
	 */

	private String nombre;

	public Equipo() {
		super();
	}

	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}

	private HashSet<Jugador> jugadores = new HashSet<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HashSet<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(HashSet<Jugador> jugadores) {
		this.jugadores = jugadores;
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
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
