package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Funcionalidades2 {
	
	public static int getNota(String nombre) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		
		int nota = 0;
		
		Document doc = leerFicheroXML();
		
//		NodeList nodosHijosDelDocumento = doc.getChildNodes();
//		
//		for(int i=0;i<nodosHijosDelDocumento.getLength();i++) {
//			
//			Node nodo = nodosHijosDelDocumento.item(i);
//			System.out.println(nodo.getNodeName());
//		}
//		
//		Node nodoRaiz = nodosHijosDelDocumento.item(0);
		
		Node nodoRaiz = doc.getFirstChild();
		
		NodeList listaNodosAlumnos = 
				nodoRaiz.getChildNodes();
		
		
		for(int i=0;i<listaNodosAlumnos.getLength();i++) {
			Node alumno = listaNodosAlumnos.item(i);
			if(alumno.getNodeType() == Node.ELEMENT_NODE) {
				//System.out.println(alumno.getNodeName());
				if (getNombreNodoAlumno(alumno).equals(nombre))
					nota = getNotaNodoAlumno(alumno);
				
				
			}
		}//for recorrido alumnos
		
		return nota;
	}
	
	private static int getNotaNodoAlumno(Node alumno) {
		
		int nota = 0;
		
		NodeList datosAlumno = alumno.getChildNodes();
		
		for(int i=0;i<datosAlumno.getLength();i++) {
			
			Node dato = datosAlumno.item(i);
			if(dato.getNodeName().equals("nota")) {
				 nota = Integer.parseInt(dato.getFirstChild().getNodeValue());
				
			}
		}
		
		return nota;
	}

	private static String getNombreNodoAlumno(Node alumno) {
		
		String nombre = null;
		
		NodeList datosAlumno = alumno.getChildNodes();
		
		for(int i=0;i<datosAlumno.getLength();i++) {
			
			Node dato = datosAlumno.item(i);
			if(dato.getNodeName().equals("nombre")) {
				 nombre = dato.getFirstChild().getNodeValue();
				
			}
		}
		
		return nombre;
	}

	private static File getFichero() throws FileNotFoundException, IOException {
		
		Properties configuracion = new Properties();
		
		configuracion.load(new FileInputStream("conf.properties"));
		
		return new File(configuracion.getProperty("file"));
		
	}
	
	public static void modificaNota(String nombre, int nota) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		
		Document doc = leerFicheroXML();
		
		Node raiz = doc.getFirstChild();
		
		NodeList alumnos = raiz.getChildNodes();
		
		for(int i=0; i<alumnos.getLength();i++) {
			
			Node alumno = alumnos.item(i);
			if(alumno.getNodeType() == Node.ELEMENT_NODE) {
				if (getNombreNodoAlumno(alumno).equals(nombre)) {
					modificarNotaAlumno(doc,alumno, nota);
				}
				
			}
		}
		
		
	}
	
	private static void modificarNotaAlumno(Document doc, Node alumno, int nota) {
		
		NodeList datosAlumno = alumno.getChildNodes();
		
		for(int i=0; i<datosAlumno.getLength();i++) {
			
			if(datosAlumno.item(i).getNodeName().equals("nota")) {
				
				Node notaNodo = datosAlumno.item(i);
				notaNodo.removeChild(notaNodo.getFirstChild());
				
				//creamos nodo nuevo:
				Text newNode = doc.createTextNode(nota+"");
				notaNodo.appendChild(newNode);
			}
		}
		
	}

	private static Document leerFicheroXML() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		
		Document doc;
		
		DocumentBuilderFactory dbf = 
				  DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		doc = db.parse(getFichero());
		
		return doc;
	}
	
	private void escribirFicheroXML(Document doc) throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		DOMSource source = new DOMSource(doc);
	     
		StreamResult result = 
		            new StreamResult(getFichero());        
		     
		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		     
		transformer.transform(source, result);
		

	}

}
