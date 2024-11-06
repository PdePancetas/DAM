package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import data.Datos;
import model.Alumno;
import utilidadesTeclado.Teclado;

public class Repositorio {

//	 1. mostrar por consola el boletin de un alumno dado nif 
//	 2. Asociar boletin de notas dado el nombre a un alumno nuevo 
//	 3. Mostrar todos los alumnos que tienen nota en una asignatura concreta dado el nombre de la asignatura

	public static void mostrarNotasAlumno(String nif) throws IOException {
		Set<Entry<Alumno, File>> boletines = Datos.getBoletines().entrySet();
		for (Entry<Alumno, File> e : boletines) {
			if (e.getKey().getNif().equals(nif)) {
				FileReader fr = new FileReader(e.getValue());
				BufferedReader br = new BufferedReader(fr);
				String linea;
				System.out.println("Asignaturas de "+e.getKey().getNombre()+":");
				while ((linea = br.readLine()) != null)
					System.out.println("  "+linea);
				br.close();
			}
		}
	}

	public static void asociarBoletinNuevoAlumnoNuevo(String nif, String nombre) throws IOException {
		System.out.println("Introduce el contenido del boletin (Ej linea: Lengua: 6): ");
		File f = new File(Datos.getDir(),"Boletin de "+nombre+", "+nif+".txt");
		f.createNewFile();
		FileWriter fr = new FileWriter(f,true);
		String s = "";
		while(!s.equals("fin")) {
			s = Teclado.leerCadena();
			fr.write((s.equals("fin"))?"":s+"\n");
		}
		
		fr.close();
		Datos.getBoletines().put(new Alumno(nif, nombre), f);

	}
	
	public static void mostrarAlumnosConNotaEnAsignatura(String nomAsig) throws IOException {
		System.out.println("Alumnos con nota en "+nomAsig+":");
		Set<Entry<Alumno, File>> boletines = Datos.getBoletines().entrySet();
		for (Entry<Alumno, File> e : boletines) {
			FileReader fr = new FileReader(e.getValue());
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null){
				if(s.contains(nomAsig)) {
					System.out.println("- "+e.getKey().getNombre());
				}
			}
		}
	}

	public static void crearCarpetaBoletines(File dir) throws IOException {
		
		dir.mkdir();
	}

}
