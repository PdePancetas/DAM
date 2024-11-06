package main;

import Datos.BaseDatos;
import model.Alumno;
import model.AlumnoFP;
import model.AlumnoSecundaria;
import model.Asignatura;
import model.AsignaturaFP;
import model.AsignaturaSecundaria;
import utilidadesTeclado.Teclado;
import logics.Operaciones;

public class Main {

	public static void main(String[] args) {

		/*
		 * La aplicación se comunicará mediante un menú con las opciones: (a) Gestionar
		 * alumno • Pedirá (i) (e) (m) (c), y en función de eso llamará al método
		 * correspondiente (b) Mostrar título (c) Mostrar listado de nombres de los
		 * alumnos matriculados en una asignatura dado su nombre. Hacerlo usando el
		 * método matriculado (d) Listado notas de alumno (e) Total horas de alumno
		 */

		String op;
		do {
			System.out.println(
					"_________________________________________________________________________________________________________");
			System.out.println("Elige una opción: ");
			System.out.println("	a. Gestionar alumno");
			System.out.println("	b. Mostrar título");
			System.out.println(
					"	c. Mostrar listado de nombres de los alumnos matriculados en una asignatura dado su nombre.");
			System.out.println("	d. Listado notas de alumno");
			System.out.println("	e. Total horas de alumno");
			System.out.println(". Salir \n");
			System.out.print("-> ");
			op = Teclado.leerCadena();

			switch (op) {
			case "a", "A":

				int sel;
				do {
					BaseDatos.menuCRUD();
					sel = Teclado.leerEntero();
					switch (sel) {
					case 1:

						System.out.print("Nombre del alumno a añadir: ");
						String nombre = Teclado.leerCadena();
						System.out.println("(1)FP o (2)Secundaria?");
						int tipo = Teclado.leerEntero();
						Alumno a;
						if (tipo == 1)
							a = new AlumnoFP(nombre);
						else
							a = new AlumnoSecundaria(nombre);
						BaseDatos.addAlumno(a);
						System.out.println("Alumno añadido!");

						break;
					case 2:
						if (BaseDatos.alumnos.length == 0)
							System.out.println("No hay alumnos en la base de datos.");
						else {
							System.out.print("Nombre del alumno a eliminar: ");
							nombre = Teclado.leerCadena();
							for (int i = 0; i < BaseDatos.alumnos.length; i++)
								if (BaseDatos.alumnos[i].getNombre().equals(nombre)) {
									BaseDatos.delAlumno(nombre);
									System.out.println("Alumno eliminado!");
								} else
									System.out.println("Este alumno no esta en la base de datos.");
						}
						break;
					case 3:
						if (BaseDatos.alumnos.length == 0)
							System.out.println("No hay alumnos en la base de datos.");
						else {
							System.out.print("Nombre del alumno a obtener: ");
							nombre = Teclado.leerCadena();
							for (int i = 0; i < BaseDatos.alumnos.length; i++)
								if (BaseDatos.alumnos[i].getNombre().equals(nombre)) {
									BaseDatos.checkAlumno(nombre);
									System.out.println("Alumno obtenido!");
								} else
									System.out.println("Este alumno no esta en la base de datos.");
						}

						break;
					case 4:
						if (BaseDatos.alumnos.length == 0)
							System.out.println("No hay alumnos en la base de datos.");
						else {
							System.out.print("Nombre del alumno a modificar: ");
							nombre = Teclado.leerCadena();
							for (int i = 0; i < BaseDatos.alumnos.length; i++)
								if (BaseDatos.alumnos[i].getNombre().equals(nombre)) {
									BaseDatos.modAlumno(nombre);
									System.out.println("Alumno modificado!");
								} else
									System.out.println("Este alumno no esta en la base de datos.");
						}
						break;
					case 0:
						System.out.println("Saliendo de Gestionar Alumno... \n");
						break;
					default:
						System.out.println("Opción no válida");
					}
				} while (sel != 0);

				break;
			case "b", "B":
				if (BaseDatos.alumnos.length == 0)
					System.out.println("No hay alumnos en la base de datos.");
				else {
					System.out.print("Nombre del alumno: ");
					String nombre = Teclado.leerCadena();
					for (int i = 0; i < BaseDatos.alumnos.length; i++)
						if (BaseDatos.alumnos[i].getNombre().equals(nombre))
							Operaciones.obtenerTitulo(BaseDatos.alumnos[i]);
					System.out.println();
				}
				break;
			case "c", "C":
				if (BaseDatos.alumnos.length == 0)
					System.out.println("No hay alumnos en la base de datos.");
				else {
					System.out.print("Nombre de la asignatura: ");
					Asignatura asig = null;
					String nombre = Teclado.leerCadena();
					boolean encontrada = false;
					for (int i = 0; i < BaseDatos.alumnos.length && !encontrada; i++)
						for (int j = 0; j < BaseDatos.alumnos[i].getAsignaturas().length && !encontrada; i++)
							if (BaseDatos.alumnos[i].getAsignaturas()[j].getNombre().equals(nombre))
								if (BaseDatos.alumnos[i].getAsignaturas()[j] instanceof AsignaturaFP) {
									asig = ((AsignaturaFP) BaseDatos.alumnos[i].getAsignaturas()[j]);
									encontrada = true;
								} else {
									asig = ((AsignaturaSecundaria) BaseDatos.alumnos[i].getAsignaturas()[j]);
									encontrada = true;
								}
					for (int i = 0; i < BaseDatos.alumnos.length; i++)
						if (Operaciones.matriculado(asig, BaseDatos.alumnos[i]))
							System.out.println("- " + BaseDatos.alumnos[i].getNombre());
					System.out.println();
				}
				break;
			case "d", "D":
				if (BaseDatos.alumnos.length == 0)
					System.out.println("No hay alumnos en la base de datos.");
				else {
					System.out.print("Nombre del alumno: ");
					String nombre = Teclado.leerCadena();
					for (int i = 0; i < BaseDatos.alumnos.length; i++)
						if (BaseDatos.alumnos[i].getNombre().equals(nombre))
							Operaciones.listadoNotas(BaseDatos.alumnos[i]);
					System.out.println();
				}
				break;
			case "e", "E":
				if (BaseDatos.alumnos.length == 0)
					System.out.println("No hay alumnos en la base de datos.");
				else {
					System.out.print("Nombre del alumno: ");
					String nombre = Teclado.leerCadena();
					for (int i = 0; i < BaseDatos.alumnos.length; i++)
						if (BaseDatos.alumnos[i].getNombre().equals(nombre))
							Operaciones.devolverTotalHoras(BaseDatos.alumnos[i]);
					System.out.println();
				}
				break;
			case ".":
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
			}

		} while (!op.equals("."));

	}

}
