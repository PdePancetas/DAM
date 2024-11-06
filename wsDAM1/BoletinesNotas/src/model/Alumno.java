package model;

public class Alumno {

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

	@Override
	public int hashCode() {
		return this.nif.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return this.nif.equals(other.nif);
	}

	@Override
	public String toString() {
		return "Alumo [nif=" + nif + ", nombre=" + nombre + "]";
	}

	public Alumno() {
	}

	public Alumno(String nif, String nombre) {
		this.nif = nif;
		this.nombre = nombre;
	}
	
}
