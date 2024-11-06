package Main;

import java.time.Year;
import java.util.LinkedList;

import Exception.AlumnoException;
import model.Alumno;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int numAlum = 0;
		int num = 0;
		do {
			try {
				num = 1;
				System.out.print("Cu�ntos alumnos va a introducir? ");
				numAlum = Teclado.leerEntero();
				if (numAlum <= 0)
					throw new AlumnoException();
			} catch (NumberFormatException e) {
				System.out.println("Eso no es un n�mero, introduce un valor v�lido. \n");
				num = 0;
			} catch (AlumnoException e) {
				System.out.println("Debe ser un n�mero positivo diferente de 0. \n");
				num = 0;
			}
		} while (num != 1);
		System.out.println();
		LinkedList<Alumno> alumnos = new LinkedList<>();
		
		for (int i = 0; i < numAlum; i++) {

			try {
				System.out.print("Nombre alumno " + (i + 1) + ": ");
				String nombre = Teclado.leerCadena();
				System.out.print("A�o de nacimiento alumno " + (i + 1) + ": ");
				int año = Teclado.leerEntero();
				alumnos.add(Alumno.crearAlumno(nombre, año));
				System.out.println(nombre + " a�adido a alumnos. \n");
			} catch (AlumnoException e) {
				System.out.println("El a�o de nacimiento no puede ser negativo o 0. \n");
				i--;
				
			} catch (NumberFormatException e) {
				System.out.println("La fecha y/o el nombre no est�n bien introducidos. \n");
				i--;

			} catch (Exception e) {
				System.out.println(e.getMessage() + "\n");
				i--;
			}

		}
		
		int cont=0;
		for (int i = 0; i < alumnos.size(); i++)
			if (esBisiesto(alumnos.get(i))) {
				System.out.println(alumnos.get(i).getNombre() + " ha nacido en a�o bisiesto!");
				cont++;
			}
		System.out.println("\n Hay "+cont+ " alumno(s) nacidos en a�o bisiesto.");
	}

	private static boolean esBisiesto(Alumno a) {
		return Year.isLeap(a.getAño());
	}

}
