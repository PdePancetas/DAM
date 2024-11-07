package objects;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String s;
		do {
			System.out.print("Introduzca nif: ");
			String nif = Teclado.leerCadena();
			System.out.print("Introduzca nombre: ");
			String nombre = Teclado.leerCadena();
			Alumno a = new Alumno(nif, nombre);
			guardarAlumno("alumnos", a);
			System.out.println("continuar?(y/n)");
			s = Teclado.leerCadena();
		} while (!s.equals("n"));

		System.out.print("Nif de busqueda: ");
		String nif = Teclado.leerCadena();
		String nombre = recuperarDatos(nif, "alumnos");
		System.out.println(nombre);
	}

	private static void guardarAlumno(String ficheroAlumnos, Alumno a) throws IOException {
		File f = new File(ficheroAlumnos);
		ObjectOutputStream oos;
		if (!f.exists()) {
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAlumnos, true));
			oos.writeObject(a);
		} else {
			oos = new Object_Output_StreamSinHeader(new FileOutputStream(ficheroAlumnos, true));
			oos.writeObject(a);
		}

	}

	public static String recuperarDatos(String nif, String nomFichero) throws IOException, ClassNotFoundException {

		File f = new File(nomFichero);
		if (!f.exists())
			return null;

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		String nombre = null;
		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Alumno a = (Alumno) ois.readObject();
				if (a.getNif().equals(nif)) {
					nombre = a.getNombre();
					finalArchivo = true;
				}
			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		return nombre;

	}
}
