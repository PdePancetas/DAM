package objects;

import java.io.Serializable;

public class Alumno implements Serializable{

	private String nif, nombre;

	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
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

	public Alumno(String nif, String nombre) {
		this.nif = nif;
		this.nombre = nombre;
	}

	public Alumno() {
	}
	
}
