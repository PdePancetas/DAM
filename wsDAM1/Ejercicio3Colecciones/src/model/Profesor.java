package model;

import java.util.LinkedList;
import java.util.List;

public class Profesor {
	
	private String nif, nombre;
	private List<Alumno> alumnos= new LinkedList<Alumno>();
	
	
	public String getNif() {
		return nif;
	}
	
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public Profesor(String nif, String nombre) {
		this.nif = nif;
		this.nombre = nombre;
	}

	public Profesor() {
	}
	
	
	
}
