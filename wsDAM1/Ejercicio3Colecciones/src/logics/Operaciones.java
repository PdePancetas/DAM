package logics;

import model.Profesor;
import java.util.LinkedList;
import model.Alumno;
import Main.Main;


public class Operaciones {

	public static void addAlumno(Profesor prof, Alumno a) {
		if(prof.getAlumnos()==null) {
			prof.setAlumnos(new LinkedList<Alumno>());
			prof.getAlumnos().add(a);
		} else
			prof.getAlumnos().add(a);
	}

	public static void mostrarProfs(String nifAlum) {
	
	}
	
	
	

}
