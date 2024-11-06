package model;

import java.util.LinkedList;
import java.util.List;

import Exception.AlumnoException;

public class Alumno {

	private String nombre;
	private int curso;
	private List<Double> notas = new LinkedList<>();

	/**
	 * @return the curso
	 */
	public int getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(int curso) {
		this.curso = curso;
	}

	/**
	 * @return the notas
	 */
	public List<Double> getNotas() {
		return notas;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}

	private int año;

	public Alumno() {
	}

	private Alumno(String nombre, int año, int curso) {
		super();
		this.nombre = nombre;
		this.año = año;
		this.curso = curso;
	}
	
	private Alumno(String nombre, int año) {
		super();
		this.nombre = nombre;
		this.año = año;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public static Alumno crearAlumno(String nombre, int año, int curso) throws AlumnoException {

		if (año > 0)
			return new Alumno(nombre, año, curso);
		else
			throw new AlumnoException();
	}
	
	public static Alumno crearAlumno(String nombre, int año) throws AlumnoException {

		if (año > 0)
			return new Alumno(nombre, año);
		else
			throw new AlumnoException();
	}

	@Override
	public String toString() {
		return "Alumno " + nombre + " nacido en " + año;
	}

}
