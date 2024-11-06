package logics;

import model.Alumno;
import model.AlumnoFP;
import model.AlumnoSecundaria;
import model.Asignatura;
import model.AsignaturaFP;
import model.AsignaturaSecundaria;
import Datos.BaseDatos;

public class Operaciones {

	public static void obtenerTitulo(Titulable alumno) {

		if (alumno.generarTitulo() != null)
			System.out.println(alumno.generarTitulo());
		else
			System.out.println("El alumno no puede titular.");
	}

	public static void listadoNotas(Alumno a) {
		for (int i = 0; i < a.getAsignaturas().length; i++)
			if (a.getAsignaturas()[i] instanceof AsignaturaSecundaria) {
				System.out.println(
						a.getAsignaturas()[i].getNombre() + ": " + ((AsignaturaFP) a.getAsignaturas()[i]).getNota());
			} else
				System.out.println(a.getAsignaturas()[i].getNombre() + ": "
						+ ((AsignaturaSecundaria) a.getAsignaturas()[i]).getNota());

	}

	public static Alumno[] cuartaConvocatoria(String nombreAsignatura) {

		Alumno[] convocatoria4 = new Alumno[0];

		for (int i = 0; i < BaseDatos.alumnos.length; i++) {
			if (BaseDatos.alumnos[i] instanceof AlumnoFP) {

				for (int j = 0; j < ((AlumnoFP) BaseDatos.alumnos[i]).getAsignaturas().length; i++) {
					if (((AlumnoFP) BaseDatos.alumnos[i]).getAsignaturas()[j] instanceof AsignaturaFP) {

						if (((AsignaturaFP) ((AlumnoFP) BaseDatos.alumnos[i]).getAsignaturas()[j])
								.getConvocatoria() == 4) {
							Alumno[] aux = new Alumno[convocatoria4.length + 1];
							for (int k = 0; k < convocatoria4.length; k++)
								convocatoria4[k] = aux[k];
							aux[aux.length - 1] = BaseDatos.alumnos[i];
							convocatoria4 = aux;
						}
					}
				}
			}
		}

		return convocatoria4;
	}

	public static Integer devolverTotalHoras(Alumno a) {

		if (a instanceof AlumnoSecundaria)
			return null;
		else {
			int totHoras = 0;
			for (int i = 0; i < ((AlumnoFP) a).getAsignaturas().length; i++)
				totHoras += ((AsignaturaFP) ((AlumnoFP) a).getAsignaturas()[i]).getNumHoras();

			return totHoras;
		}

	}

	public static boolean matriculado(Asignatura asignatura, Alumno nombre) {

		for (int i = 0; i < nombre.getAsignaturas().length; i++)
			if (nombre.getAsignaturas()[i].getNombre().equals(asignatura.getNombre()))
				return true;

		return false;
	}

}
