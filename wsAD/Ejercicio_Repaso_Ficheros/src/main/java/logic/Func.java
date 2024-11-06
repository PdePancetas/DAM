package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.Libro;
import beans.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Func {

	public static File getFicheroXML() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("fileXml"));

		return f;
	}

	public static File getFicheroJSON() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("fileJson"));

		return f;
	}

	public static JSONArray leerFicheroJSON(File f) throws IOException {

		String texto = "";
		String linea;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(f.getName())));

		while ((linea = br.readLine()) != null)
			texto += linea;

		JSONArray libros = new JSONArray(texto);
		br.close();
		return libros;
	}

	public static ArrayList<Libro> mostrarPorAutor(String nombreAutor)
			throws FileNotFoundException, JAXBException, IOException {

		ArrayList<Libro> librosAutor = new ArrayList<Libro>();
		JSONArray libros = Func.leerFicheroJSON(getFicheroJSON());

		for (int i = 0; i < libros.length(); i++) {
			boolean esta = false;
			JSONObject libro = libros.getJSONObject(i);
			String titulo = libro.getString("titulo");
			JSONArray autoresJson = libro.getJSONArray("autores");
			ArrayList<String> autores = new ArrayList<>();
			for (int j = 0; j < autoresJson.length(); j++) {
				if (autoresJson.getString(j).equals(nombreAutor))
					esta = true;
				autores.add(autoresJson.getString(j));
			}
			int stock = libro.getInt("stock");

			if (esta)
				librosAutor.add(new Libro(titulo, autores, stock));
		}

		return librosAutor;
	}

	public static Libros getLibrosJson() throws IOException {
		JSONArray librosJson = Func.leerFicheroJSON(getFicheroJSON());
		Libros libros = new Libros();

		for (int i = 0; i < librosJson.length(); i++) {

			JSONObject libro = librosJson.getJSONObject(i);
			ArrayList<String> autores = new ArrayList<>();
			for (int j = 0; j < libro.getJSONArray("autores").length(); j++)
				autores.add(libro.getJSONArray("autores").getString(j));
			
			libros.getLibros().add(new Libro(libro.getString("titulo"), autores, libro.getInt("stock")));
		}

		return libros;
	}

	public static void escribirFicheroXMLJAXB(Libros libros) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(libros, new File("datos/libros.xml"));
	}

	public static void crearXML() throws IOException, JAXBException {
		Libros libros = getLibrosJson();

		escribirFicheroXMLJAXB(libros);

	}

	public static Document leerFicheroXMLDOM()
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(getFicheroXML());
	}

	@SuppressWarnings("unused")
	private static void escribirFicheroXMLDOM(Document doc)
			throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(getFicheroXML());

		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(source, result);
	}

	public static ArrayList<Libro> mostrarLibrosConStockMenor(int stockDado)
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		ArrayList<Libro> libros = new ArrayList<>();
		Document librosDom = leerFicheroXMLDOM();

		NodeList nodosLibros = librosDom.getElementsByTagName("libro");
		for (int i = 0; i < nodosLibros.getLength(); i++) {
			Element libro = (Element) nodosLibros.item(i);
			NodeList nodoAutores = libro.getElementsByTagName("autor");
			ArrayList<String> autores = new ArrayList<>();
			for(int j=0;j<nodoAutores.getLength();j++) {
				autores.add(nodoAutores.item(j).getTextContent());
			}
			int stock = Integer.parseInt(libro.getElementsByTagName("stock").item(0).getTextContent());
			if (stock < stockDado) {
				libros.add(new Libro(libro.getElementsByTagName("titulo").item(0).getTextContent(), autores, stock));
			}
		}

		return libros;
	}
	
	public static void genInforme(Libros libros) throws JRException {

		Properties config = new Properties();
		try {
			config.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ficheroJasper = config.getProperty("fileJasper");
		
		String informePdf = config.getProperty("pathPDF");

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(libros.getLibros());
	
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params , camposInforme);
		
		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}

}
