package logics;

import java.util.LinkedList;

import DatosAlumno.datosAlumno;
import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {

		LinkedList<datosAlumno> alumnos = cargarAlumnos();
		
		try {
			informes.genInforme(alumnos);
		} catch (JRException e) {
			System.out.println("No se puedo acceder a");
			e.printStackTrace();
		}
		
	}

	private static LinkedList<datosAlumno> cargarAlumnos() {

		LinkedList<datosAlumno> alumnos = new LinkedList<>();
		for (int i = 0; i < 40; i++) {
			alumnos.add(new datosAlumno("Pepito", (int) (Math.random()*10)));
			alumnos.add(new datosAlumno("Jose", (int) (Math.random()*10)));
			alumnos.add(new datosAlumno("Pedro", (int) (Math.random()*10)));
			alumnos.add(new datosAlumno("John", (int) (Math.random()*10)));
			alumnos.add(new datosAlumno("Alberto", (int) (Math.random()*10)));
			alumnos.add(new datosAlumno("Manuel", (int) (Math.random()*10)));
			
		}
		return alumnos;
	}

}
