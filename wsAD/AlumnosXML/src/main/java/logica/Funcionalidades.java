package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import beans.Alumnos;
import beans.Alumno;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Funcionalidades {

	public static File getFichero() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("file"));		
		return f;
	}

	public static Alumnos leerFichero(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		// Contexto : Clase Libros
		JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);

		// Como el parser
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso de fichero a objeto
		Alumnos alumnos = (Alumnos) unmarshaller.unmarshal(f);

		return alumnos;
	}

	public static void escribirFichero(Alumnos alumnos) throws JAXBException, FileNotFoundException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		// Indentacion
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(alumnos, getFichero());

	}

	public static TreeSet<Alumno> listarAlumnos() throws JAXBException, FileNotFoundException, IOException {

		Alumnos alumnos = leerFichero(getFichero());
		return alumnos.getAlumno();
	}

	public static String mostrarDatosAlumno(int nif) throws FileNotFoundException, JAXBException, IOException {
		Alumnos alumnos = leerFichero(getFichero());

		for (Alumno a : alumnos.getAlumno())
			if (a.getNif() == nif)
				return a.toString();

		return "No se encontró al alumno";
	}

	public static double notaMediaTotal() throws FileNotFoundException, JAXBException, IOException {
		double mediaTotal;
		double sumaNotas = 0;
		Alumnos alumnos = leerFichero(getFichero());

		for(Alumno a: alumnos.getAlumno())
			sumaNotas+=a.getNota();
		
		mediaTotal = sumaNotas / alumnos.getAlumno().size();
		return mediaTotal;
	}

	public static void modNotaAlumno(int nif, double nota) throws FileNotFoundException, JAXBException, IOException {
		Alumnos alumnos = leerFichero(getFichero());
		for(Alumno a: alumnos.getAlumno())
			if(a.getNif() == nif)
				a.setNota(nota);
		
		escribirFichero(alumnos);
	}
	
	public static void deleteAlumno(int nif) throws FileNotFoundException, JAXBException, IOException {
		Alumnos alumnos = leerFichero(getFichero());
		
		Iterator<Alumno> it = alumnos.getAlumno().iterator();
		while(it.hasNext()) {
			Alumno a = it.next();
			if(a.getNif() == nif)
				it.remove();
		}
		
		escribirFichero(alumnos);
		
	}

	public static void insAlumno(Alumno alumno) throws FileNotFoundException, JAXBException, IOException {
		Alumnos alumnos = leerFichero(getFichero());
		
		alumnos.getAlumno().add(alumno);
		
		escribirFichero(alumnos);
	}
	
	public static boolean existeAlumno(int nif) throws FileNotFoundException, JAXBException, IOException {
		Alumnos alumnos = leerFichero(getFichero());
		for(Alumno a: alumnos.getAlumno())
			if(a.getNif() == nif)
				return true;
		
		return false;
	}

}
