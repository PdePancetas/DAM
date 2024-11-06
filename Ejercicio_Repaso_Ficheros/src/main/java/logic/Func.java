package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;

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

	// Contexto : Clase Raiz JAXBContext jaxbContext =
	JAXBContext.newInstance(Raiz.class);

	// Como el parser Unmarshaller unmarshaller =
	jaxbContext.createUnmarshaller();
//
//	// Traspaso de fichero a objeto 
	Raiz raiz = (Raiz) unmarshaller.unmarshal(f);

	return raiz;
}

public static void escribirFichero(Objeto objeto) throws JAXBException {
  
  JAXBContext jaxbContext = JAXBContext.newInstance(Raiz.class); 
  
  Marshaller marshaller = jaxbContext.createMarshaller();
  
  
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	marshaller.marshal(objeto, new File(""));
}

}
