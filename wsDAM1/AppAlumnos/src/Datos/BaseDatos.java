package Datos;

import model.Alumno;
import model.AlumnoSecundaria;
import model.AlumnoFP;

public class BaseDatos {

	public static Alumno[] alumnos = new Alumno[0];

	/*****************************************
	 * //CRUD create, read, update, delete.
	 *****************************************/

	public static void addAlumno(Alumno a) {
		Alumno[] aux = new Alumno[alumnos.length + 1];
		for (int i = 0; i < alumnos.length; i++)
			aux[i] = alumnos[i];
		aux[aux.length - 1] = a;
		alumnos = aux;
	}

	public static void delAlumno(String nombre) {

		Alumno[] aux = new Alumno[alumnos.length - 1];

		for (int i = 0, j = 0; i < alumnos.length; i++)
			if (!alumnos[i].getNombre().equals(nombre))
				aux[j++] = alumnos[i];

		alumnos = aux;

	}

	public static Alumno checkAlumno(String nombre) {

		for (int i = 0; i < alumnos.length; i++)
			if (alumnos[i].getNombre().equals(nombre))
				return alumnos[i];
		return null;
	}

	public static void modAlumno(String nombre) {

		for (int i = 0; i < alumnos.length; i++)
			if (alumnos[i].getNombre().equals(nombre)) {
				if (alumnos[i] instanceof AlumnoFP) {

				} else {

				}

			}

	}

	public static void menuCRUD() {
		System.out.println("________________________");
		System.out.println("Elige una opción: ");
		System.out.println("	1. Añadir alumno");
		System.out.println("	2. Eliminar alumno");
		System.out.println("	3. Obtener alumno");
		System.out.println("	4. Modificar alumno");
		System.out.println("0. Salir");
		System.out.print("-> ");
	}

}
