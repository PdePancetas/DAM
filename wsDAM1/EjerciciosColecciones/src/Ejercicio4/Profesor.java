package Ejercicio4;

import java.util.LinkedList;

import utilidadesTeclado.Teclado;

public class Profesor {

	private String nombre;
	private LinkedList<Alumno> alumnos = new LinkedList<>();;

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
	public LinkedList<Alumno> getAlumnos() {
		return alumnos;
	}

	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(LinkedList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Profesor(String nombre) {
		this.nombre = nombre;
	}

	public Profesor() {

	}

	public void addAlumno() {
		if (alumnos.size() == 30) {
			System.out.println("No se pueden añadir más de 30 alumnos, borra alguno si quieres introducir otro.");
			return;
		} else {
			System.out.print("Nombre del alumno: ");
			String nombre = Teclado.leerCadena();
			System.out.print("Nota: ");
			double nota = Teclado.leerDecimal();
			alumnos.add(new Alumno(nombre, nota));
			System.out.println("Alumno añadido a este profesor.");
		}

	}

	public void delAlumno(String nombre) {
		for (int i=0;i<alumnos.size();i++)
			if (alumnos.get(i).getNombre().equals(nombre)) {
				System.out.println(alumnos.get(i).getNombre()+" eliminado.");
				alumnos.remove(alumnos.get(i));
				return;
			}
		System.out.println("Este alumno no está asignado a este profesor.");
		return;

	}

	public void listAlumnos() {
		System.out.println(alumnos.toString());
	}

	public void porcentajeAprobados() {
		int cont = 0;
		for (Alumno a : alumnos)
			if (a.getNota() >= 5)
				cont++;
		System.out.println("Hay un " + (cont * 100) / alumnos.size() + "% de aprobados.");

	}

}