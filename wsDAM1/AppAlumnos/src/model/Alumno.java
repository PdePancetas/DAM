package model;

import java.time.LocalDate;

import logics.Titulable;

public abstract class Alumno implements Titulable {

	protected String nombre;
	protected LocalDate fechaNacimiento;
	protected Asignatura[] asignaturas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Asignatura[] getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Asignatura[] asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Alumno(String nombre, LocalDate fechaNacimiento, Asignatura[] asignaturas) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.asignaturas = asignaturas;
	}

	public Alumno() {
	}

	public abstract String generarTitulo();

}
