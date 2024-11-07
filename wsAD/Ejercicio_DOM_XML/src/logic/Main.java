package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import data.Datos;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		try {
			Funcionalidades.construirDoc();
		} catch (DOMException | IOException | ParserConfigurationException | SAXException
				| TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}

	}

	private static File getFichero() throws FileNotFoundException, IOException {
		
		Properties p = new Properties();
		p.load(new FileInputStream("configuration.properties"));
		File f = new File(p.getProperty("nombreFichero"));
		f.createNewFile();
		return f;
	}

}
