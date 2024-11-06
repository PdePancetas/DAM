package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;

import beans.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Func {

	public static JSONArray leerFicheroJSON(String nombreFichero) throws IOException {

		String texto = "";
		String linea;
		InputStream is = Main.class.getClassLoader().getResourceAsStream(nombreFichero);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((linea = br.readLine()) != null)
				texto += linea;
		}

		JSONArray libros = new JSONArray(texto);

		return libros;
	}

	public static Libros leerFichero(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Libros libros = (Libros) unmarshaller.unmarshal(f);

		return libros;
	}

	public static void escribirFichero(Libros libros) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(libros, new File("datos/libros.xml"));
	}

}
