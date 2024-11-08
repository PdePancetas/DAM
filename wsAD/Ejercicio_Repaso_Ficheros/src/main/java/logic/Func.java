package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

	/**
	 * 
	 * @return Fichero xml de la ruta de config
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File getFicheroXML() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("fileXml"));

		return f;
	}

	/**
	 * 
	 * @return Fichero json de la ruta de config
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File getFicheroJSON() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty("fileJson"));

		return f;
	}

	/**
	 * 
	 * @param f Fichero (json)
	 * @return Un array de objetos Json sacado de f
	 * @throws IOException
	 */
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

	/**
	 * 
	 * @param nombreAutor nombre como filtro
	 * @return Objeto Libros que contengan en sus autores a nombreAutor
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static Libros mostrarPorAutor(String nombreAutor)
			throws FileNotFoundException, JAXBException, IOException {
		
		Libros libros  = new Libros();
		JSONArray librosJson = Func.leerFicheroJSON(getFicheroJSON());

		for (int i = 0; i < librosJson.length(); i++) {
			boolean esta = false;
			JSONObject libro = librosJson.getJSONObject(i);
			String titulo = libro.getString("titulo");
			JSONArray autoresJson = libro.getJSONArray("autores");
			String autoresCadena = "";
			for (int j = 0; j < autoresJson.length(); j++) {
				if (autoresJson.getString(j).equals(nombreAutor))
					esta = true;
				autoresCadena += autoresJson.getString(j) + ", ";
			}
			int stock = libro.getInt("stock");

			if (esta) {
				autoresCadena = autoresCadena.substring(0, autoresCadena.length() - 2);
				libros.getLibros().add(new Libro(titulo, autoresCadena, stock));
			}
		}

		return libros;
	}
	/**
	 * 
	 * @param autorConString boolean con el que se decide si se devuelve un objeto Libros
	 *  cuya propiedad autores sea un ArrayList o un String
	 * @return Objeto Libros leido desde el archivo json
	 * @throws IOException
	 */

	public static Libros getLibrosJson(boolean autorConString) throws IOException {
		JSONArray librosJson = Func.leerFicheroJSON(getFicheroJSON());
		Libros libros = new Libros();

		for (int i = 0; i < librosJson.length(); i++) {

			JSONObject libro = librosJson.getJSONObject(i);
			ArrayList<String> autores = new ArrayList<>();
			String autoresCadena = "";
			for (int j = 0; j < libro.getJSONArray("autores").length(); j++) {
				if (autorConString)
					autoresCadena += libro.getJSONArray("autores").getString(j) + ", ";
				else
					autores.add(libro.getJSONArray("autores").getString(j));
			}
			if (autorConString) {
				autoresCadena = autoresCadena.substring(0, autoresCadena.length() - 2);
				libros.getLibros().add(new Libro(libro.getString("titulo"), autoresCadena, libro.getInt("stock")));
			} else
				libros.getLibros().add(new Libro(libro.getString("titulo"), autores, libro.getInt("stock")));
		}

		return libros;
	}

	/**
	 * 
	 * @param libros objeto que se escribira en un fichero xml
	 * @throws JAXBException
	 */
	public static void escribirFicheroXMLJAXB(Libros libros) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(libros, new File("datos/libros.xml"));
	}

	/**
	 * 
	 * @param autorConString boolean con el que se decide si se escribe en el xml un objeto Libros
	 *  cuya propiedad autores es un ArrayList o un String 
	 *  (en este caso sera un ArrayList)
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void crearXML(boolean autorConString) throws IOException, JAXBException {
		Libros libros = getLibrosJson(autorConString);

		escribirFicheroXMLJAXB(libros);

	}

	/**
	 * 
	 * @return Objeto DOM de un archivo xml
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document leerFicheroXMLDOM()
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(getFicheroXML());
	}

	/**
	 * 
	 * @param stockDado cantidad utilizada como filtro
	 * @return Objeto Libros cuya lista de libros 
	 *  solo tiene libros con un stock menor a stockDado
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Libros mostrarLibrosConStockMenor(int stockDado)
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		Libros libros = new Libros();
		Document librosDom = leerFicheroXMLDOM();

		NodeList nodosLibros = librosDom.getElementsByTagName("libro");
		for (int i = 0; i < nodosLibros.getLength(); i++) {
			Element libro = (Element) nodosLibros.item(i);
			NodeList nodoAutores = libro.getElementsByTagName("autor");
			String autoresCadena = "";
			for (int j = 0; j < nodoAutores.getLength(); j++) {
				autoresCadena += nodoAutores.item(j).getTextContent() + ", ";
			}
			int stock = Integer.parseInt(libro.getElementsByTagName("stock").item(0).getTextContent());
			if (stock < stockDado) {
				autoresCadena = autoresCadena.substring(0, autoresCadena.length() - 2);
				libros.getLibros().add(
						new Libro(libro.getElementsByTagName("titulo").item(0).getTextContent(), autoresCadena, stock));
			}
		}

		return libros;
	}

	/**
	 * 
	 * @param libros objeto Libros del que se obtendran los datos para el informe
	 * @param tituloInforme nombre que tendra el archivo pdf que se creara
	 * @throws JRException
	 */
	public static void genInforme(Libros libros, String tituloInforme) throws JRException {

		Properties config = new Properties();
		try {
			config.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ficheroJasper = config.getProperty("fileJasper");

		String informePdf = "datos/" + tituloInforme + ".pdf";

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(libros.getLibros());

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}

}
