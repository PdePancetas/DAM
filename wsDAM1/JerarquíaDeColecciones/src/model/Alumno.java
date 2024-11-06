package model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {

	private int numMatricula;
	private String nombre;
	private int edad;
	private List<String> asignaturas = new ArrayList<String>();

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<String> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Alumno() {
	}

	public Alumno(int numMatricula, String nombre, int edad) {
		this.numMatricula = numMatricula;
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {

		String datos = "";
		datos += "El alumno " + nombre + " con número de matrícula " + numMatricula + " tiene " + edad + " años \n";

		if (asignaturas == null) {
			datos += "y no está matriculado en ninguna asignatura. \n \n";
			return datos;
		} else {
			datos += "y está matriculado en las siguientes asignaturas: \n";
			for (String asig : asignaturas) {
				datos += " · " + asig.substring(0, 1).toUpperCase() + asig.substring(1, asig.length());
			}
			datos += ". \n \n";
		}

		return datos;
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
		return numMatricula == other.numMatricula;
	}

}
