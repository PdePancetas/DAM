package main;

import java.io.IOException;

import data.*;
import logic.*;
import model.*;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		try {
			if (Datos.getDir().exists()) {
				for(int i=0;i< Datos.getDir().listFiles().length;i++)
					Datos.getDir().listFiles()[i].delete();
			}
			
			Repositorio.crearCarpetaBoletines(Datos.getDir());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int op = -1;
		do {
			System.out.println("Introduce una opcion:");
			System.out.println("   1. Crear alumno con su boletin.");
			System.out.println("   2. Mostrar boletin de alumno.");
			System.out.println("   3. Mostrar alumnos que contienen una nota en una asignatura especifica.");
			System.out.println("0. Salir \n");

			try {
				System.out.print("-> ");
				op = Teclado.leerEntero();
			} catch (NumberFormatException e) {
				op = 4;
			}

			switch (op) {

			case 1:
				System.out.print("Nif del nuevo alumno: ");
				String nif = Teclado.leerCadena();
				System.out.print("Nombre del nuevo alumno: ");
				String nombre = Teclado.leerCadena();
				try {
					Repositorio.asociarBoletinNuevoAlumnoNuevo(nif, nombre);
					System.out.println("El boletin de " + nombre + " se encuentra en "
							+ Datos.getBoletines().get(new Alumno(nif, "")).getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.print("Introduce el nif del alumno: ");
				nif = Teclado.leerCadena();
				try {
					Repositorio.mostrarNotasAlumno(nif);
					System.out.println("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				System.out.print("Introduce la asignatura: ");
				String nomAsig = Teclado.leerCadena();
				try {
					Repositorio.mostrarAlumnosConNotaEnAsignatura(nomAsig);
					System.out.println();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion no valida \n");

			}

		} while (op != 0);

	}

}
