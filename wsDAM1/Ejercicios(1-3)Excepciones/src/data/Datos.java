package data;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import model.Alumno;

public class Datos {

	public static Map<String, Alumno> alumnos = new TreeMap<>();

	public static void insertarAlumno(String nif, Alumno a) {
		if (!alumnos.containsKey(nif))
			alumnos.put(nif, a);
		else
			System.out.println("El alumno con nif "+nif+" ya está en alumnos");
				
	}

	public static void addNota(String nif, double nota) {
		if (alumnos.containsKey(nif))
			alumnos.get(nif).getNotas().add(nota);
		else
			System.out.println("El alumno con nif "+nif+" no está en alumnos");
	}

	public static double notaMediaAlumno(String nif) {
		if (alumnos.containsKey(nif)) {
			double sumaNota = 0;
			int cont = 0;
			for (int i = 0; i < alumnos.get(nif).getNotas().size(); i++) {
				sumaNota += alumnos.get(nif).getNotas().get(i);
				cont++;
			}

			return (sumaNota / cont);
		} else {
			System.out.println("El alumno con nif "+nif+" no está en alumnos");
			return 0;
		}
	}

	public static double notaMediaCurso(int curso) {
		
		double sumaNotaCurso = 0;
		int cont = 0;
		Set<Entry<String, Alumno>> alumnoS = alumnos.entrySet();
		for (Entry<String, Alumno> a : alumnoS) {
			if (a.getValue().getCurso() == curso) {
				sumaNotaCurso += notaMediaAlumno(a.getKey());
				cont++;
			}
		}

		return (sumaNotaCurso / cont);
	}

}
