package data;

import java.util.LinkedList;

public class Profesor implements Comparable<Profesor> {
	/*
	 * Clase Profesor: declara el nif del profesor (String), el nombre (String) y el
	 * conjunto de alumnos que tiene asignados (List de objetos Alumno)
	 */

	private String nifProfesor, nombre;
	private LinkedList<Alumno> alumnosProfesor = new LinkedList<>();

	public Profesor(String nifProfesor, String nombre) {
		this.nifProfesor = nifProfesor;
		this.nombre = nombre;
	}

	/**
	 * @return the nifProfesor
	 */
	public String getNifProfesor() {
		return nifProfesor;
	}

	/**
	 * @param nifProfesor the nifProfesor to set
	 */
	public void setNifProfesor(String nifProfesor) {
		this.nifProfesor = nifProfesor;
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

	/**
	 * @return the alumnos
	 */
	public LinkedList<Alumno> getAlumnosProfesor() {
		return alumnosProfesor;
	}

	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnosProfesor(LinkedList<Alumno> alumnos) {
		this.alumnosProfesor = alumnos;
	}

	public Profesor() {
	}

	@Override
	public String toString() {
		return "Profesor [nifProfesor=" + nifProfesor + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		return nifProfesor.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return nifProfesor.equals(other.nifProfesor);
	}

	@Override
	public int compareTo(Profesor p) {
		return this.nifProfesor.compareTo(p.nifProfesor);
	}

	public boolean checkAlumno(Alumno a) {
		return this.alumnosProfesor.contains(a);
	}

	public boolean addAlumno(Alumno a) {
		return this.alumnosProfesor.add(a);
	}

	public String getAlumnosEmpresa() {
		String s = "Alumnos con Empresa: \n";
		for (Alumno a : this.alumnosProfesor) {
			if (a.getCodEmpresa() != null)
				s += "NIA: " + a.getNIA() + "; Codigo de empresa: " + a.getCodEmpresa() + "; Codigo de Sede: "
						+ a.getCodSede() + "\n";
		}

		return s;
	}

	public int getNumAlumnosEmpresa() {
		int numAlumnos = 0;
		for (Alumno a : this.alumnosProfesor) {
			if (a.getCodEmpresa() != null)
				numAlumnos++;
		}

		return numAlumnos;
	}

}
