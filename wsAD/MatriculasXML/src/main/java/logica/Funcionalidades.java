package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import beans.Asignatura;
import beans.Matricula;
import beans.Matriculas;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Funcionalidades {

	public static File getFichero() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config/config.properties"));
		File f = new File(configuracion.getProperty("file"));
		return f;
	}

	public static Matriculas leerFichero(File f) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Matriculas.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Matriculas alumnos = (Matriculas) unmarshaller.unmarshal(f);

		return alumnos;
	}

	public static void escribirFichero(Matriculas matriculas) throws JAXBException, FileNotFoundException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Matriculas.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(matriculas, getFichero());

	}

	public static void matricularAlumno(Matricula matricula) throws FileNotFoundException, JAXBException, IOException {
		Matriculas matriculas;
		if (!getFichero().exists())
			matriculas = new Matriculas();
		else
			matriculas = leerFichero(getFichero());

		matriculas.getMatriculas().add(matricula);

		escribirFichero(matriculas);

	}

	public static ArrayList<String> mostrarAlumnosPorAsignatura(String nombreAsig)
			throws FileNotFoundException, JAXBException, IOException {
		Matriculas matriculas = leerFichero(getFichero());
		ArrayList<String> alumnos = new ArrayList<>();
		for (Matricula m : matriculas.getMatriculas())
			for (Asignatura a : m.getAsignaturas()) {
				if (a.getNombre().equals(nombreAsig))
					alumnos.add(m.getAlumno());
			}

		return alumnos;
	}

	public static Matricula matriculaMasCara() throws FileNotFoundException, JAXBException, IOException {
		Matriculas matriculas = leerFichero(getFichero());

		Matricula matCara = matriculas.getMatriculas().get(0);

		for(Matricula m: matriculas.getMatriculas())
			if(precioTotal(m)>precioTotal(matCara))
				matCara = m;
		
		return matCara;
	}

	public static double precioTotal(Matricula matricula) {
		double precioTotal=0; 
		for(Asignatura a: matricula.getAsignaturas())
			precioTotal+= a.getPrecio();
		
		return precioTotal;
	}

}
