package help_Tema_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Metodos_Comunes {

	// Instanciar logger de una clase
	// public static Logger logger = LogManager.getLogger(Nombre_Clase.class);

	// Para obtener recursos de la carpeta resources en Maven
	// InputStream is =
	// Main.class.getClassLoader().getResourceAsStream("nombreFichero");

	//////////////////////////////// XML/////////////////////////////
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

	@SuppressWarnings("unused")
	private static void escribirFicheroXML(Document doc)
			throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(getFichero());

		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(source, result);
	}
	//////////////////////////////////////////////////////////////////////

	///////////////////////////////// JAXB////////////////////////////////

//	public static Raiz leerFichero(File f) throws JAXBException {
//		// Leer el documento xml (leer contenido y pasar a objeto Libros)
//
//		// Contexto : Clase Raiz 
//		JAXBContext jaxbContext = JAXBContext.newInstance(Raiz.class);
//
//		// Como el parser 
//		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//		// Traspaso de fichero a objeto 
//		Raiz raiz = (Raiz) unmarshaller.unmarshal(f);
//
//		return raiz;
//	}

//	public static void escribirFichero(Objeto objeto) throws JAXBException {
//	  
//	  JAXBContext jaxbContext = JAXBContext.newInstance(Raiz.class); 
//	  
//	  Marshaller marshaller = jaxbContext.createMarshaller();
//	  
//	  Indentacion 
//	  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//	  
//	  marshaller.marshal(objeto, new File(""));
//	  }

	///////////////////////////////////////////////////////////////////////////////

	/////////////////////////////// JAVA NIO///////////////////////////////////////
	@SuppressWarnings("unused")
	private static void ejemploJavaNIO() {
		// clases Path, Paths y Files
		Path p = Paths.get("ficheroPrueba");
		List<String> lineas = null;
		try {
			lineas = Files.readAllLines(p);
		} catch (IOException e) {
			System.out.println("No se pudieron obtener los datos");
			e.printStackTrace();
		}

		if (lineas != null)
			for (String s : lineas)
				System.out.println(s);
	}

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// SAX//////////////////////////////////////////
	public static void SAXPArser() throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		// ESTA CLASE SE CREA
		Handler handler = new Handler();

		saxParser.parse(getFichero(), handler);
	}
	////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// JSON/////////////////////////////////////////
	// Los mas importantes
	// JSONObject;
	// JSONArray; //Recorrerlo con for de indice
	
	// OBTENER LISTA DE UN JSON
	// ObjectMapper mapper = new ObjectMapper();
	// ArrayList<tipo de dato> nombrelista =mapper.readValue( getFicheroJson(), mapper.getTypeFactory().constructCollectionType(List.class, clase.class));
	
	// ObjectMapper mapper = new ObjectMapper();
	// ArrayList<Objeto> nombrelista =mapper.readValue(new File(archivo.json), mapper.getTypeFactory().constructCollectionType(Tipo de coleccion, Clase a mapear));
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// JASPERREPORTS ///////////////////////////////
	public static void genInforme(/*Datos*/) throws JRException {

//		String plantilla = "ruta de la plantilla para el pdf";
		String ficheroJasper = "ruta del fichero .jasper creado por JasperSoft Studio";
		String informePdf = "ruta donde se guardará el pdf";

		// Transformamos los datos a una fuente de datos que JasperReports entiende
		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(null /*Una Coleccion*/);

		// Compilamos la plantilla
//		JasperReport jasperReport = JasperCompileManager.compileReport(plantilla);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();
		
		// Rellenamos el informe con la fuente de datos
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		// Exportamos a pdf
		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}
	////////////////////////////////////////////////////////////////////////////////////
}
