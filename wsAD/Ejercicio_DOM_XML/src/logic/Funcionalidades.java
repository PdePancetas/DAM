package logic;

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
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.Datos;

public class Funcionalidades {

	public static File getFichero() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("file"));

		return f;
	}

	public static Document leerFicheroXML()
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(getFichero());
	}

	private static void escribirFicheroXML(Document doc)
			throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(getFichero());

		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(source, result);
	}

	public static void construirDoc() throws FileNotFoundException, DOMException, IOException,
			ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException {
		Document doc;
		Element raiz;
		if (!getFichero().exists()) {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			raiz = doc.createElement("pedidos");
			doc.appendChild(raiz);
		} else {
			doc = leerFicheroXML();
			raiz = (Element) doc.getFirstChild();
		}

		for (int i = 0; i < Datos.getModulos().length; i++) {
			Element modulo = doc.createElement("modulo");
			modulo.setAttribute("horas", Datos.getHoras()[i] + "");
			modulo.setAttribute("permiteFCT", Datos.getPermiteFCT()[i] ? "si" : "no");

			Element nombre = doc.createElement("nombre");
			nombre.appendChild(doc.createTextNode(Datos.getModulos()[i]));
			modulo.appendChild(nombre);

			Element nota = doc.createElement("nota");
			nota.appendChild(doc.createTextNode(Datos.getNotas()[i] + ""));
			modulo.appendChild(nota);

			raiz.appendChild(modulo);
		}

		/*
		 * <modulos> <modulo horas=”6” permiteFCT=”false”> <nombre>Acceso a
		 * Datos</nombre> <nota>8.45</> </modulo> ... </modulos>
		 */

		escribirFicheroXML(doc);

	}
	
	
}
