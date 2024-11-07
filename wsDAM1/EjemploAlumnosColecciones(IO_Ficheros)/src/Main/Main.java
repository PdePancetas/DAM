package Main;

import java.io.IOException;

import logica.Operaciones;
import modelo.Alumno;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

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
		int op;

		do {
			System.out.println("_______________________________________________________________________________");
			System.out.println("Elige una opcion: \n");
			System.out.println("	1. Insertar alumno (sin asignaturas)");
			System.out.println("	2. Mostrar datos de alumno dado su n�mero de matr�cula");
			System.out.println(
					"	3. Matricular a un alumno de una asignatura, dado el n�mero de matr�cula del alumno y el nombre de asignatura.");
			System.out.println("	4. Desmatricular de una asignatura dado numMatricula y nombre de asignatura");
			System.out
					.println("	5. Mostrar nombre de alumno que esta matriculado de m�s asignaturas entre los alumnos");
			System.out.println("	6. Listar alumnos");
			System.out.println("	7. Eliminar alumno");
			System.out.println("    8. Guardar Datos");
			System.out.println("    9. Recuperar Alumnos \n");

			System.out.println("0. Salir");
			System.out.print("-> ");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				System.out.println("Datos del alumno: ");
				System.out.print("	N�mero de Matr�cula: ");
				int numMatricula = Teclado.leerEntero();
				System.out.print("	Nombre: ");
				String nombre = Teclado.leerCadena();
				System.out.print("	Edad: ");
				int edad = Teclado.leerEntero();
				Alumno alumno = new Alumno(numMatricula, nombre, edad);
				Operaciones.insertarAlumno(alumno);
				System.out.println("Alumno a�adido...");

				break;
			case 2:
				System.out.print("N�mero de matr�cula: ");
				numMatricula = Teclado.leerEntero();
				String a=  Operaciones.mostrarDatos(numMatricula);
				System.out.println("\n" +a);

				break;
			case 3:

				System.out.print("N�mero de matr�cula del alumno: ");
				numMatricula = Teclado.leerEntero();
				System.out.print("	Nombre de la asignatura: ");
				String nomAsig = Teclado.leerCadena();
				Operaciones.matricularAlumno(numMatricula, nomAsig);

//				boolean encontrado = false;
//				for (int i = 0; i < Operaciones.alumnos.size() && !encontrado; i++)
//					if (Operaciones.alumnos.get(i).getNumMatricula() == numMatricula) {
//						encontrado = true;
//						System.out.print("	Nombre de la asignatura: ");
//						nomAsig = Teclado.leerCadena();
//						Operaciones.alumnos.get(i).getAsignaturas().add(nomAsig);
//						System.out.println(Operaciones.alumnos.get(i).getNombre() + " se ha matriculado en " + nomAsig + ".");
//					}
//
//				if (!encontrado)
//					System.out.println("No hay ningun alumno con ese n�mero de matr�cula");

				break;
			case 4:
				System.out.print("N�mero de matr�cula del alumno: ");
				numMatricula = Teclado.leerEntero();
				System.out.print("	Nombre de la asignatura: ");
				nomAsig = Teclado.leerCadena();
				Operaciones.desmatricularAlumno(numMatricula, nomAsig);

//				boolean encontrado = false;
//				for (int i = 0; i < Operaciones.alumnos.size() && !encontrado; i++)
//					if (Operaciones.alumnos.get(i).getNumMatricula() == numMatricula) {
//						encontrado = true;
//						System.out.print("	Nombre de la asignatura: ");
//						nomAsig = Teclado.leerCadena();
//						Operaciones.alumnos.get(i).getAsignaturas().remove(nomAsig);
//						System.out.println(Operaciones.alumnos.get(i).getNombre() + " se ha desmatriculado de " + nomAsig + ".");
//					}
//
//				if (!encontrado)
//					System.out.println("No hay ningun alumno con ese n�mero de matr�cula");

				break;
			case 5:
				Operaciones.alumnoMasAsignaturas();
				break;
			case 6:
				if (Operaciones.alumnos.size() == 0)
					System.out.println("No hay alumnos que mostrar.");
				else
					for (int i = 0; i < Operaciones.alumnos.size(); i++)
						System.out.println(Operaciones.alumnos.get(i).toString());

				break;
			case 7:
				System.out.print("N�mero de matr�cula del alumno: ");
				numMatricula = Teclado.leerEntero();
				Operaciones.eliminarAlumno(numMatricula);
				System.out.println("Eliminando alumno...");

				break;
			case 8:
				System.out.print("Nombre del fichero: ");
				String s = Teclado.leerCadena();
				try {
					Operaciones.guardarDatos(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:
				System.out.print("Nombre del fichero: ");
				String nomFichero = Teclado.leerCadena();
				Operaciones.recuperarDatos(nomFichero);
				System.out.println("Se han recuperado "+Operaciones.alumnos.size()+" alumnos");

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

}
