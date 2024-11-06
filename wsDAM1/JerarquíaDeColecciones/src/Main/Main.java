package Main;

import java.util.LinkedList;

import model.Alumno;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * List<Alumno> alumnos1 = new ArrayList<>();
		 * 
		 * List<Alumno> alumnos2 = new LinkedList<>();
		 * 
		 * alumnos1.add(new Alumno(1,"pepe",20)); alumnos1.add(new Alumno(2,"javi",20));
		 * 
		 * alumnos2.addAll(alumnos1); alumnos1.add(1,new Alumno(3,"luis",20));
		 * alumnos1.add(1,new Alumno(8,"Jose",20)); alumnos1.add(1,new
		 * Alumno(7,"gil",20)); alumnos1.add(1,new Alumno(2,"Fer",20));
		 * alumnos1.add(1,new Alumno(12,"mar",20));
		 * 
		 * System.out.println("Antes de ordenar: \n"); for(int
		 * i=0;i<alumnos1.size();i++) System.out.println(alumnos1.get(i));
		 * System.out.println("******************************************");
		 * 
		 * 
		 * //Ordenamos los alumnos por numMatricula:
		 * 
		 * alumnos1.sort(new CriterioNumMatricula());
		 * 
		 * System.out.println("Despues de ordenar: \n"); for(int
		 * i=0;i<alumnos1.size();i++) System.out.println(alumnos1.get(i));
		 * 
		 * 
		 * alumnos1.sort(new CriterioNombre());
		 * 
		 * System.out.println("Despues de ordenar: \n"); for(int
		 * i=0;i<alumnos1.size();i++) System.out.println(alumnos1.get(i));
		 * 
		 * System.out.println("******************************************"); //Eliminar
		 * por objeto:
		 * 
		 * alumnos1.remove(new Alumno(2,null,98));
		 * 
		 * System.out.println("Despues de eliminar: \n"); for(int
		 * i=0;i<alumnos1.size();i++) System.out.println(alumnos1.get(i));
		 */

		LinkedList<Alumno> alumnos = new LinkedList<Alumno>();

		int op;

		do {
			System.out.println("_______________________________________________________________________________");
			System.out.println("Elige una opcion: \n");
			System.out.println("	1. Insertar alumno (sin asignaturas)");
			System.out.println("	2. Mostrar datos de alumno dado su numero de matricula");
			System.out.println(
					"	3. Matricular a un alumno de una asignatura, dado el numero de matricula del alumno y el nombre de asignatura.");
			System.out.println("	4. Desmatricular de una asignatura dado numMatricula y nombre de asignatura");
			System.out.println(
					"	5. Mostrar nombre de alumno que esta matriculado de más asignaturas entre los alumnos \n");
			System.out.println("0. Salir");
			System.out.print("-> ");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				System.out.println("Datos del alumno: ");
				System.out.print("	Número de Matrícula: ");
				int numMatricula = Teclado.leerEntero();
				System.out.print("	Nombre: ");
				String nombre = Teclado.leerCadena();
				System.out.print("	Edad: ");
				int edad = Teclado.leerEntero();
				alumnos.addLast(new Alumno(numMatricula, nombre, edad));

				break;
			case 2:
				System.out.print("Número de matricula: ");
				numMatricula = Teclado.leerEntero();
				boolean encontrado = false;
				for (int i = 0; i < alumnos.size() && !encontrado; i++)
					if (alumnos.get(i).getNumMatricula() == numMatricula) {
						encontrado = true;
						System.out.println("\n" + alumnos.get(i).toString());
					}

				if (!encontrado)
					System.out.println("No hay ningún alumno con ese número de matrícula");

				break;
			case 3:

				System.out.print("Número de matrícula del alumno: ");
				numMatricula = Teclado.leerEntero();
				encontrado = false;
				for (int i = 0; i < alumnos.size() && !encontrado; i++)
					if (alumnos.get(i).getNumMatricula() == numMatricula) {
						encontrado = true;
						System.out.print("	Nombre de la asignatura: ");
						String nomAsig = Teclado.leerCadena();
						alumnos.get(i).getAsignaturas().addLast(nomAsig);
						System.out.println(alumnos.get(i).getNombre() + " se ha matriculado en " + nomAsig + ".");
					}

				if (!encontrado)
					System.out.println("No hay ningun alumno con ese número de matrícula");

				break;
			case 4:
				System.out.print("Número de matrícula del alumno: ");
				numMatricula = Teclado.leerEntero();
				encontrado = false;
				for (int i = 0; i < alumnos.size() && !encontrado; i++)
					if (alumnos.get(i).getNumMatricula() == numMatricula) {
						encontrado = true;
						System.out.print("	Nombre de la asignatura: ");
						String nomAsig = Teclado.leerCadena();
						alumnos.get(i).getAsignaturas().remove(nomAsig);
						System.out.println(alumnos.get(i).getNombre() + " se ha desmatriculado de " + nomAsig + ".");
					}

				if (!encontrado)
					System.out.println("No hay ningun alumno con ese número de matrícula");
				break;
			case 5:
				Alumno mayor = alumnos.get(0);
				for (int i = 0; i < alumnos.size(); i++)
					if (alumnos.get(i).getAsignaturas().size() > mayor.getAsignaturas().size())
						mayor = alumnos.get(i);
				
				System.out.println("El alumno con el mayor número de asignaturas es " + mayor.getNombre() + ".");
				
				break;
			case 0:
				System.out.println("Saliendo...");

				break;

			default:
				System.out.println("Opcion no valida.");
			}

		} while (op != 0);

	}

	/*
	 * 
	 * Menu: 1. Insertar alumno (sin asignaturas)
	 * 
	 * 2. Mostrar datos de alumno dado su numero de matricula
	 * 
	 * 3. Matricular a un alumno de una asignatura, dado el numero de matricula del
	 * alumno y el nombre de asignatura.
	 * 
	 * 4. Desmatricular de una asignatura dado numMatricula y nombre de asignatura
	 * 
	 * 5. Mostrar nombre de alumno que esta matriculado de más asignaturas de todos
	 * los alumnos
	 * 
	 */

}
