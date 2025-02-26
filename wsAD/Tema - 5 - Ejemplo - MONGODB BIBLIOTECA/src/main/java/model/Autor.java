package model;

public class Autor {

	private String nombre;
	private int sueldo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public Autor(String nombre, int sueldo) {
		super();
		this.nombre = nombre;
		this.sueldo = sueldo;
	}

	public Autor() {
		super();
	}

	@Override
	public String toString() {
		return "Autor nombre=" + nombre + ", sueldo=" + sueldo;
	}
	

}
