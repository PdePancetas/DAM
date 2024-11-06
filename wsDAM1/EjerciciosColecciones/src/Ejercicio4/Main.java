package Ejercicio4;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		Profesor profesor = new Profesor();

		int op;
		do {
			System.out.println("_____________________________________________________");
			System.out.println("Elige una opción");
			System.out.println("	1. Incorporar alumno al profesor siempre y cuando no sobrepasen los 30 alumnos");
			System.out.println("	2. Dar de baja alumno ");
			System.out.println("	3. Mostrar listado de alumnos");
			System.out.println("	4. Mostrar porcentaje de aprobados");
			System.out.println("0. Salir \n");
			System.out.print("-> ");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				profesor.addAlumno();

				break;

			case 2:
				System.out.println("Nombre del alumno: ");
				String nombre = Teclado.leerCadena();
				profesor.delAlumno(nombre);

				break;
			case 3:
				profesor.listAlumnos();

				break;
			case 4:
				profesor.porcentajeAprobados();

				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion no válida");
			}
			
		} while (op != 0);

	}

}
