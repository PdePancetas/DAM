package Main;

import java.util.LinkedList;
import java.util.List;

import logics.Operaciones;
import logics.CriterioNombre;
import model.Alumno;
import model.Profesor;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * 3. Realizar una aplicación para gestionar los profesores y alumnos de un
		 * centro. Las clases serían: 
		 * Alumno(nif, nombre), 
		 * Profesor(nif, nombre, lista de alumnos siendo esta última propiedad un objeto List). 
		 * En la clase Main se implementarán las funcionalidades.
		 * En esta clase Main se guardará el conjunto de profesores (objeto List) .
		 * Las funcionalidades serán: 
		 * - Insertar profesor (implicará insertar todos sus datos,alumnos incluidos) 
		 * - Dado el nif de un alumno, mostrar los nombres de todos los profesores que lo tienen
		 * - Dado el nif de un profesor, mostrar un listado ordenado alfab�ticamente de sus alumnos
		 */
		
		List<Profesor> profesores = new LinkedList<Profesor>();
		
		int op=10;
		
		do {
			System.out.println("_______________________________________________________________________________");
			System.out.println("Elige una opcion: \n");
			System.out.println("	1. Insertar profesor (implicará insertar todos sus datos,alumnos incluidos)");
			System.out.println("	2. Dado el nif de un alumno, mostrar los nombres de todos los profesores que lo tienen");
			System.out.println("	3. Dado el nif de un profesor, mostrar un listado ordenado alfabéticamente de sus alumnos \n");
			System.out.println("0. Salir");
			System.out.print("-> ");
			
			
			try {
				op = Teclado.leerEntero();
			} catch (Exception e) {
				System.out.println("Se produjo un error: "+e);
			}

			switch (op) {
			case 1:
				Profesor prof = new Profesor();
				System.out.print("NIF del profesor: ");
				String nifProf = Teclado.leerCadena();
				System.out.print("Nombre del profesor: ");
				String nomProf = Teclado.leerCadena();
				boolean seguir=true;
				do {
					
					System.out.print("NIF del alumno: ");
					String nifAlum = Teclado.leerCadena();
					System.out.print("Nombre del alumno: ");
					String nomAlum = Teclado.leerCadena();
					Operaciones.addAlumno(prof, new Alumno(nifAlum, nomAlum));
					System.out.print("Seguir? Y/N: ");
					String res = Teclado.leerCadena();
					if(res.equalsIgnoreCase("y"))
						seguir=true;
					else
						seguir=false;
					
					
				}while(seguir);
				
				profesores.add(new Profesor(nifProf, nomProf));
				System.out.println(prof.getNombre()+" Creado.");
				
				break;
			case 2://Dado el nif de un alumno, mostrar los nombres de todos los profesores que lo tienen
				System.out.print("NIF del alumno: ");
				String nifAlum = Teclado.leerCadena();
				Operaciones.mostrarProfs(nifAlum);
				System.out.println("Profesores con este alumno: ");
				for(int i=0;i<profesores.size();i++)
					for(int j=0;j<profesores.get(i).getAlumnos().size();j++)
						if(profesores.get(i).getAlumnos().get(j).equals(new Alumno(nifAlum, ""))) {
							System.out.println("- "+profesores.get(i).getAlumnos().get(j).getNombre());
				}
				break;
			case 3://Dado el nif de un profesor, mostrar un listado ordenado alfabéticamente de sus alumnos
				System.out.print("NIF del profesor: ");
				nifProf = Teclado.leerCadena();
				Operaciones.mostrarProfs(nifProf);
				for(int i=0;i<profesores.size();i++)
					if(profesores.get(i).getNif().equals(nifProf)) {
						System.out.println("Alumnos de este profesor: ");
						profesores.get(i).getAlumnos().sort(new CriterioNombre());
						for(int j=0;j<profesores.get(i).getAlumnos().size();j++)
							System.out.println(profesores.get(i).getAlumnos().get(j));
					}
				
				break;
			case 0:
				System.out.println("Saliendo...");

				break;

			default:
				System.out.println("Opción no valida.");
			}

		} while (op != 0);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
