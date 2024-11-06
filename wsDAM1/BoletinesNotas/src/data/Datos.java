package data;

import java.io.File;
import java.util.HashMap;

import model.Alumno;
public class Datos {

	
	
	/**
	 * Contenido del fichero (El titulo sera: Boletin de + el nombre del alumno):
	 * Nombre asignatura: Nota
	 * 		.
	 * 		.
	 * 		.
	 * 
	 */
	private static HashMap<Alumno, File> boletines = new HashMap<>();
	private static File dir = new File(".\\Boletines");

	/**
	 * @return the boletines
	 */
	public static HashMap<Alumno, File> getBoletines() {
		return boletines;
	}

	/**
	 * @param boletines the boletines to set
	 */
	public static void setBoletines(HashMap<Alumno, File> boletines) {
		Datos.boletines = boletines;
	}

	public static File getDir() {
		return dir;
	}

	public static void setDir(File dir) {
		Datos.dir = dir;
	}
	
	
	
}
