package logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import beans.Alumno;
import jakarta.xml.bind.JAXBException;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {
		/*
		 * – visualizar listado de alumnos del fichero – dado un nombre de alumno
		 * mostrar sus datos – mostrar la nota media de los alumnos – modificar nota de
		 * alumno dado su nombre – eliminar alumno dado su nombre – insertar alumno,
		 * solicitando sus datos
		 */

		int op = -1;
		do {
			System.out.println("\nElige una opcion: ");
			System.out.println("1. Visualizar listado de alumnos del fichero");
			System.out.println("2. Dado un nif de alumno mostrar sus datos");
			System.out.println("3. Mostrar la nota media de los alumnos");
			System.out.println("4. Modificar nota de alumno dado su nif");
			System.out.println("5. Eliminar alumno dado su nif");
			System.out.println("6. Insertar alumno, solicitando sus datos");
			System.out.println("0. Salir");
			System.out.print("  -> ");

			try {
				op = Teclado.leerEntero();
			} catch (NumberFormatException e) {
				op = -1;
			}
			try {
				switch (op) {
				case 1:
					try {
						for (Alumno a : Funcionalidades.listarAlumnos())
							System.out.println("Alumno: " + a.getNombre() + ", nif: " + a.getNif());
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 2:
					System.out.print("Nif del alumno: ");
					int nif = Teclado.leerEntero();

					try {
						System.out.println(Funcionalidades.mostrarDatosAlumno(nif));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						System.out.println("La nota media de todos los alumnos es " + Funcionalidades.notaMediaTotal());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 4:
					System.out.print("Nif del alumno: ");
					nif = Teclado.leerEntero();

					System.out.print("Nueva nota: ");
					double nota = Teclado.leerDecimal();

					try {
						Funcionalidades.modNotaAlumno(nif, nota);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 5:
					System.out.print("Nif del alumno: ");
					nif = Teclado.leerEntero();

					try {
						Funcionalidades.deleteAlumno(nif);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 6:
					try {
						System.out.print("Nif del alumno: ");
						nif = Teclado.leerEntero();
						if (Funcionalidades.existeAlumno(nif))
							System.out.println("Ya existe un alumno con este nif");
						else {
							System.out.print("Nombre del alumno: ");
							String nombre = Teclado.leerCadena();

							System.out.print("Direccion del alumno: ");
							String direccion = Teclado.leerCadena();

							System.out.print("Nota del alumno: ");
							nota = Teclado.leerDecimal();

							Funcionalidades.insAlumno(new Alumno(nif, nombre, direccion, nota));
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.err.println("Opcion no valida");
					break;

				}
			} catch (NumberFormatException | InputMismatchException e) {
				System.err.println("Error al introducir datos");
			}

		} while (op != 0);

	}

}
