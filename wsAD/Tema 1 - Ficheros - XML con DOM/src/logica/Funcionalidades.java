package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Funcionalidades {
	
	public static void insAlumno(String nombre, int nota) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = leerFicheroXML();
		Element nuevoAlumno = doc.createElement("alumno");
		Node raiz = doc.getFirstChild();
		
		raiz.appendChild(nuevoAlumno);
		
		Element nodoNombre = doc.createElement("nombre");
		nodoNombre.appendChild(doc.createTextNode(nombre));
		nuevoAlumno.appendChild(nodoNombre);
		
		Element nodoNota = doc.createElement("nota");
		nodoNota.appendChild(doc.createTextNode(nota+""));
		nuevoAlumno.appendChild(nodoNota);
		
		escribirFicheroXML(doc);
		
		
	}

	public static int getNota(String nombre) throws ParserConfigurationException, SAXException, IOException {

		int nota = 0;

		Document doc = leerFicheroXML();

//		NodeList nodosHijos = doc.getChildNodes();
//		
//		for(int i=0; i<nodosHijos.getLength();i++) {
//			
//			Node nodo = nodosHijos.item(i);
//			System.out.println(nodo.getNodeName());
//		}
//		Node nodoRaiz = nodosHijos.item(0);

		Node nodoRaiz = doc.getFirstChild();
		NodeList listaNodosAlumnos = nodoRaiz.getChildNodes();

		for (int i = 0; i < listaNodosAlumnos.getLength(); i++) {
			Node alumno = listaNodosAlumnos.item(i);
			if (alumno.getNodeType() == Node.ELEMENT_NODE)
				if (getNombreNodoAlumno(alumno).equals(nombre))
					nota = getNotaNodoAlumno(alumno);

		}

		return nota;
	}

	public static File getFichero() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		return new File(configuracion.getProperty("file"));
	}

	private static int getNotaNodoAlumno(Node alumno) {
		int nota = 0;
		NodeList datosAlumno = alumno.getChildNodes();
		for (int i = 0; i < datosAlumno.getLength(); i++) {
			Node dato = datosAlumno.item(i);
			if (dato.getNodeName().equals("nota"))
				nota = Integer.parseInt(dato.getFirstChild().getNodeValue());

		}
		return nota;
	}

	private static String getNombreNodoAlumno(Node alumno) {
		String nombre = null;

		NodeList datosAlumno = alumno.getChildNodes();
		for (int i = 0; i < datosAlumno.getLength(); i++) {
			Node dato = datosAlumno.item(i);
			if (dato.getNodeName().equals("nombre"))
				nombre = dato.getFirstChild().getNodeValue();

		}
		return nombre;
	}

	public static Document leerFicheroXML()
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(getFichero());
	}

	public static void modNota(String nombre, int nota)
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = leerFicheroXML();

		NodeList alumnos = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < alumnos.getLength(); i++) {
			Node alumno = alumnos.item(i);
			if (alumno.getNodeType() == Node.ELEMENT_NODE) {
				if (getNombreNodoAlumno(alumno).equals(nombre)) {
					modificarNotaAlumno(doc, alumno, nota);
				}
			}
		}
		
		escribirFicheroXML(doc);
	}

	private static void modificarNotaAlumno(Document doc, Node alumno,int nota) {
		
		NodeList datosAlumno = alumno.getChildNodes();
		for(int i=0;i<datosAlumno.getLength();i++) {
			Node dato = datosAlumno.item(i);
			if(dato.getNodeName().equals("nota")) {
				dato.removeChild(dato.getFirstChild());
				dato.appendChild(doc.createTextNode(nota+""));
			}
		}
	}
	
	private static void escribirFicheroXML(Document doc) throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(getFichero());
		
		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		transformer.transform(source, result);
	}
	
	public static ArrayList<String> listadoAprobados() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException{
		ArrayList<String> aprobados= new ArrayList<>();
		Document doc = leerFicheroXML();
		
		for(int i=0;i<doc.getFirstChild().getChildNodes().getLength();i++) {
			Node alumno = doc.getFirstChild().getChildNodes().item(i);
			if(alumno.getNodeType() == Node.ELEMENT_NODE) {
				//CON METODOS HECHOS A MANO
//				if(getNotaNodoAlumno(alumno)>=5) {
//					aprobados.add(getNombreNodoAlumno(alumno));
//				}
				
				//SIN METODOS HECHOS A MANO
				Element alum = (Element) alumno;
				int nota = Integer.parseInt(alum.getElementsByTagName("nota").item(0).getTextContent());
				if(nota>=5)
					aprobados.add(alum.getElementsByTagName("nombre").item(0).getTextContent());
				
			}
			
		}
		return aprobados;
	}
	
	

}
