package model;

import java.util.LinkedList;

public class Profesor {

	private String nombre;
	private LinkedList<Alumno> alumnos = new LinkedList<>();

	public Profesor(String nombre, LinkedList<Alumno> alumnos) {
		super();
		this.nombre = nombre;
		this.alumnos = alumnos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(LinkedList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Profesor() {}

}
