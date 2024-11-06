package model;

public abstract class Asignatura {

	protected String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Asignatura() {
	}

	public Asignatura(String nombre) {
		this.nombre = nombre;
	}

}
