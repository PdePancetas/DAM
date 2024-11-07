package logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

import beans.Asignatura;
import beans.Matricula;
import jakarta.xml.bind.JAXBException;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Requisitos: - matricular alumno - mostrar alumnos que tienen una asignatura -
		 * matrícula más cara
		 */
		int op = -1;
		do {
			System.out.println("\nElige una opcion: ");
			System.out.println("1. Matricular alumno");
			System.out.println("2. Mostrar alumnos dada asignatura concreta");
			System.out.println("3. Mostrar la matricula más cara");
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
					System.out.print("Nombre del alumno: ");
					String nombre = Teclado.leerCadena();
					System.out.print("Cuantas asignaturas? ");
					int numAsig = Teclado.leerEntero();
					List<Asignatura> asignaturas = new LinkedList<>();

					for (int i = 1; i <= numAsig; i++) {
						System.out.println("\nAsignatura " + i);
						Asignatura a = new Asignatura();
						System.out.print("Nombre asignatura " + i + ": ");
						a.setNombre(Teclado.leerCadena());
						System.out.print("precio asignatura " + i + ": ");
						a.setPrecio(Teclado.leerEntero());
						asignaturas.add(a);
					}

					Matricula matricula = new Matricula(nombre, asignaturas);
					try {
						Funcionalidades.matricularAlumno(matricula);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 2:
					System.out.print("Nombre asignatura: ");
					String nombreAsig = Teclado.leerCadena();
					System.out.println("Alumnos con " + nombreAsig + ": ");
					try {
						List<String> alumnos = Funcionalidades.mostrarAlumnosPorAsignatura(nombreAsig);
						if (alumnos.size() == 0)
							System.out.println("No hay alumnos con " + nombreAsig);
						else
							for (String alumno : alumnos)
								System.out.println("- " + alumno);

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
						Matricula matriculaCara = Funcionalidades.matriculaMasCara();

						System.out.println("Alumno: " + matriculaCara.getAlumno());
						System.out.println("Asignaturas de " + matriculaCara.getAlumno() + ": ");
						for (Asignatura a : matriculaCara.getAsignaturas()) {
							System.out.println("   Nombre: " + a.getNombre() + ",precio: " + a.getPrecio());
						}
						System.out.println(
								"\nPrecio total de la matricula: " + Funcionalidades.precioTotal(matriculaCara));
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
