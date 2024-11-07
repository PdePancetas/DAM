package logica;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import modelo.Alumno;

public class Operaciones {

	public static List<Alumno> alumnos = new LinkedList<Alumno>();

	/**
	 * Inserta el alumno en la coleccion de alumnos
	 * 
	 * @param a Alumno a insertar
	 */
	public static void insertarAlumno(Alumno a) {
		if (alumnos != null)
			alumnos.add(a);
		else {
			alumnos = new LinkedList<>();
			alumnos.add(a);
		}
	}

	public static String mostrarDatos(int numMatricula) {
		String datos = "";
		boolean encontrado = false;
		for (int i = 0; i < alumnos.size() && !encontrado; i++)
			if (alumnos.get(i).getNumMatricula() == numMatricula) {
				encontrado = true;

				Alumno a = alumnos.get(i);

				datos += "El alumno " + a.getNombre() + " con n�mero de matr�cula " + a.getNumMatricula() + " tiene "
						+ a.getEdad() + " a�os \n";

				if (a.getAsignaturas().size() == 0) {
					datos += "y no est� matriculado en ninguna asignatura. \n \n";
					return datos;
				} else {
					datos += "y est� matriculado en las siguientes asignaturas: \n";
					for (String asig : a.getAsignaturas()) {
						datos += " | " + asig.substring(0, 1).toUpperCase() + asig.substring(1, asig.length());
					}
					datos += ".";
				}
			}

		if (!encontrado) {
			return "No hay ning�n alumno con ese n�mero de matr�cula";
		}

		/*
		 * OTRAS FORMAS DE RECORRER UNA LISTA:
		 * 
		 * //El iterador. Iterator<Alumno> it = alumnos.iterator();
		 * 
		 * while(it.hasNext()) { Alumno a = it.next(); //........... }
		 * 
		 * 
		 */

		/*
		 * TAMBIEN SE PUEDE HACER CON INDEXOF()
		 * 
		 * 
		 * Alumno aux = new Alumno(); aux.setNumMatricula(numMatricula); Alumno alum =
		 * alumnos.get(alumnos.indexOf(aux)); //Alum es el alumno que busca datos +=
		 * "El alumno " + alum.getNombre() + " con n�mero de matr�cula " + numMatricula
		 * + " tiene " + alum.getEdad() + " a�os \n";
		 * 
		 * if (alum.getAsignaturas() == null) { datos +=
		 * "y no est� matriculado en ninguna asignatura. \n \n"; return datos; } else {
		 * datos += "y est� matriculado en las siguientes asignaturas: \n"; for (String
		 * asig : alum.getAsignaturas()) { datos += " | " + asig.substring(0,
		 * 1).toUpperCase() + asig.substring(1, asig.length()); } datos += "."; }
		 */

		return datos;
	}

	public static void matricularAlumno(int numMatricula, String asignatura) {

		if (alumnos == null) {
			System.out.println("No hay alumnos en la base de datos");
			return;
		}
		Alumno aux = new Alumno();
		aux.setNumMatricula(numMatricula);
		if (alumnos.indexOf(aux) == -1) {
			System.out.println("No hay ningun alumno con ese n�mero de matr�cula");
			return;
		}
		Alumno alum = alumnos.get(alumnos.indexOf(aux));
		alum.getAsignaturas().add(asignatura);
		System.out.println("Se ha matriculado a " + alum.getNombre() + " en " + asignatura);
	}

	public static void desmatricularAlumno(int numMatricula, String asignatura) {

		if (alumnos == null) {
			System.out.println("No hay alumnos en la base de datos");
			return;
		}

		Alumno aux = new Alumno();
		aux.setNumMatricula(numMatricula);
		if (alumnos.indexOf(aux) == -1) {
			System.out.println("No hay ningun alumno con ese n�mero de matr�cula");
			return;
		}
		Alumno alum = alumnos.get(alumnos.indexOf(aux));
		alum.getAsignaturas().remove(asignatura);
		System.out.println("Se ha desmatriculado a " + alum.getNombre() + " de " + asignatura);
	}

	public static void eliminarAlumno(int numMatricula) {

		Alumno aux = new Alumno();
		aux.setNumMatricula(numMatricula);
		alumnos.remove(aux);
	}

	/**
	 * 
	 * Nombre del alumno con el mayor n�mero de asignaturas
	 * 
	 * @return null si no hay alumnos Si hay varios alumnos con el mismo n�mero
	 *         m�ximo de asignaturas
	 */

	public static String alumnoMasAsignaturas() {

		if (alumnos == null || alumnos.size() == 0) {
			System.out.println("No hay alumnos en la base de datos");
			return null;
		}
		// Inicializaremos a mayor al primer alumno de la lista cuya lista de
		// asignaturas no sea null
		int j = 0;
		while (alumnos.get(j).getAsignaturas() == null)
			j++;

		Alumno mayor = alumnos.get(j);

		for (int i = j + 1; i < alumnos.size(); i++)
			if (alumnos.get(i).getAsignaturas() != null
					&& alumnos.get(i).getAsignaturas().size() > mayor.getAsignaturas().size())
				mayor = alumnos.get(i);

		return "El alumno con el mayor n�mero de asignaturas es " + mayor.getNombre() + ".";
	}

	public static Alumno alumnoMasEdad() {
		Alumno mayor = alumnos.get(0);
		for (int i = 0; i < alumnos.size(); i++)
			if (alumnos.get(i).getEdad() > mayor.getEdad())
				mayor = alumnos.get(i);
		return mayor;
	}

	public static int numAlumnosMatriculados(String nombreAsig) {

		int cont = 0;
		for (int i = 0; i < alumnos.size(); i++)
			if (alumnos.get(i).getAsignaturas().contains(nombreAsig))
				cont++;
		return cont;

	}

	public static boolean existeMatricula(int numMatricula) {

		return alumnos.contains(new Alumno(numMatricula, null, 0));

	}

	public static void guardarDatos(String nomFichero) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichero));

		for (Alumno a : alumnos)
			oos.writeObject(a);
		oos.close();
	}

	public static void recuperarDatos(String nomFichero) throws IOException, ClassNotFoundException {

		File f = new File(nomFichero);
		if (!f.exists())
			return;

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Alumno a = (Alumno) ois.readObject();
				alumnos.add(a);
			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
	}
}
