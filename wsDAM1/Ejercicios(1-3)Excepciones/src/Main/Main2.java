package Main;

import data.Datos;
import model.Alumno;

public class Main2 {

	public static void main(String[] args) {
		
		
		Datos.insertarAlumno("abcd", Alumno.crearAlumno("Jose",2000,1));
		Datos.addNota("abcd", 1.3);
		Datos.addNota("abcd", 2.8);
		Datos.addNota("abcd", 3.2);
		Datos.addNota("abcd", 4);
		Datos.addNota("abcd", 5.6);
		Datos.addNota("abcd", 3);
		
		Datos.insertarAlumno("abc", Alumno.crearAlumno("Pepe",2010,1));
		Datos.addNota("abc", 3);
		Datos.addNota("abc", 5);
		Datos.addNota("abc", 6);
		Datos.addNota("abc", 1);
		Datos.addNota("abc", 9);
		Datos.addNota("abc", 4);
		
		
		
		Datos.insertarAlumno("ab", Alumno.crearAlumno("ALBERTOOO",2015,2));
		Datos.addNota("ab", 3);
		Datos.addNota("ab", 5);
		Datos.addNota("ab", 6);
		Datos.addNota("ab", 1);
		Datos.addNota("ab", 9);
		Datos.addNota("ab", 4);
		
		
		System.out.println(Datos.notaMediaAlumno("ab"));
		System.out.println(Datos.notaMediaCurso(2));
		System.out.println(Datos.notaMediaCurso(1));
		
	}

}
