package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import properties.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import beans.BibliotecaVideojuegos;
import beans.Dato;
import beans.Intercambio;
import beans.Juego;
import beans.Usuario;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Func {

	public static File getFichero() throws FileNotFoundException, IOException {

		File f = new File(Properties.getConfig().getProperty("fichero"));

		return f;
	}

	public static Document leerFicheroXML(File f)
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(f);
	}

	public static BibliotecaVideojuegos leerFicheroJAXB(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		// Contexto : Clase Raiz
		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);

		// Como el parser
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso de fichero a objeto
		BibliotecaVideojuegos BibliotecaVideojuegos = (BibliotecaVideojuegos) unmarshaller.unmarshal(f);

		return BibliotecaVideojuegos;
	}

	public static void escribirFicheroJAXB(BibliotecaVideojuegos bibliotecaVideojuegos)
			throws JAXBException, FileNotFoundException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(bibliotecaVideojuegos, getFichero());
	}

	/**
	 * 1. Dado un id de juego, mostrar por consola si está intercambiado, y si es
	 * así mostrar los identificadores de los usuarios que han realizado el
	 * intercambio. Hay que realizar esta funcionalidad usando los métodos de la API
	 * DOM.
	 * 
	 * @param idJuego
	 */
	static void intercambiado(int idJuego) {

		try {
			Document doc = leerFicheroXML(getFichero());
			Element BibliotecaVideojuegos = (Element) doc.getDocumentElement();
			NodeList intercambios = BibliotecaVideojuegos.getElementsByTagName("intercambios").item(0).getChildNodes();

			for (int i = 0; i < intercambios.getLength(); i++) {
				if (intercambios.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element intercambio = (Element) intercambios.item(i);
					int ID_Juego = Integer
							.parseInt(intercambio.getElementsByTagName("ID_Juego").item(0).getTextContent());
					if (ID_Juego == idJuego) {
						System.out.println(
								"\nIntercambio encontrado: ");

						int ID_Emisor = Integer.parseInt(
								intercambio.getElementsByTagName("ID_Usuario_Emisor").item(0).getTextContent());
						int ID_Receptor = Integer.parseInt(
								intercambio.getElementsByTagName("ID_Usuario_Receptor").item(0).getTextContent());

						System.out.println("ID emisor: " + ID_Emisor);
						System.out.println("ID receptor: " + ID_Receptor);
						System.out.println("ID juego : " + ID_Juego);
					}
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2. Eliminar usuario y todos sus intercambios. Dado un id de usuario,
	 * eliminarlo del fichero, así como todos los intercambios en los que
	 * participase. Se tiene que mostrar por consola una confirmación de la
	 * operación realizada. Hay que realizar esta funcionalidad usando JAXB.
	 * 
	 * @param idUsuario
	 */
	static void eliminaUsuario(int idUsuario) {

		try {
			BibliotecaVideojuegos bibliotecaVideojuegos = leerFicheroJAXB(getFichero());

			ArrayList<Usuario> users = bibliotecaVideojuegos.getUsuarios();
			ArrayList<Intercambio> exchanges = bibliotecaVideojuegos.getIntercambios();

			bibliotecaVideojuegos.setUsuarios(new ArrayList<Usuario>(
					users.stream().filter(user -> user.getIdUsuario() != idUsuario).toList()));
			
			bibliotecaVideojuegos.setIntercambios(new ArrayList<Intercambio>(exchanges.stream()
					.filter(exchange -> exchange.getIdEmisor() != idUsuario || exchange.getIdReceptor() != idUsuario)
					.toList()));

			

			escribirFicheroJAXB(bibliotecaVideojuegos);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 3. Generar un informe dado un id de usuario que muestre los nombres de los
	 * usuarios con los que tiene intercambiados juegos junto con el nombre del
	 * juego intercambiado.
	 * 
	 * ***********************************SIN TERMINAR
	 *
	 * @param idUsuario
	 * @throws JRException
	 */
	static void generaInforme(int idUsuario) throws JRException {

		ArrayList<Dato> datos = new ArrayList<>();
		try {
			BibliotecaVideojuegos bibliotecaVideojuegos = leerFicheroJAXB(getFichero());
			for (Intercambio i : bibliotecaVideojuegos.getIntercambios())
				if (i.getIdEmisor() == idUsuario || i.getIdReceptor() == idUsuario)
					datos.add(crearDato(bibliotecaVideojuegos, i, idUsuario));

			String ficheroJasper = Properties.getConfig().getProperty("ficheroJasper");
			String informePdf = "datos/informe_User-"+idUsuario+".pdf";

			JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("idUsuario", idUsuario);
			params.put("nombreUsuario", getUserName(idUsuario, bibliotecaVideojuegos).get());

			JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

			JasperExportManager.exportReportToPdfFile(informe, informePdf);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	private static Dato crearDato(BibliotecaVideojuegos biblio, Intercambio i, int idUsuario) {
		Dato dato = new Dato();

		dato.setIdUsuario(idUsuario);
		dato.setNombreUsuario(getUserName(idUsuario, biblio).get());

		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(getUser(i.getIdEmisor(), biblio).get());
		usuarios.add(getUser(i.getIdReceptor(), biblio).get());
		dato.setUsuarios(usuarios);

		dato.setNombreJuego(getGameTitle(i.getIdJuego(), biblio).get());

		return dato;
	}

	public static Optional<String> getUserName(int idUsuario, BibliotecaVideojuegos biblio) {

		return biblio.getUsuarios().stream().filter(user -> user.getIdUsuario() == idUsuario).map(Usuario::getNombre)
				.findFirst();
	}

	public static Optional<String> getGameTitle(int idJuego, BibliotecaVideojuegos biblio) {

		return biblio.getJuegos().stream().filter(juego -> juego.getIdJuego() == idJuego).map(Juego::getTitulo)
				.findFirst();
	}

	public static Optional<Usuario> getUser(int idUsuario, BibliotecaVideojuegos biblio) {
		return biblio.getUsuarios().stream().filter(usr -> usr.getIdUsuario() == idUsuario).findFirst();
	}

	public static List<Integer> getUserIds() throws FileNotFoundException, JAXBException, IOException {

		BibliotecaVideojuegos biblio = leerFicheroJAXB(getFichero());
		
		return biblio.getUsuarios().stream()
				.map(Usuario::getIdUsuario).toList();
	}

	public static List<Integer> getGameIds() throws FileNotFoundException, JAXBException, IOException {
		BibliotecaVideojuegos biblio = leerFicheroJAXB(getFichero());
		
		return biblio.getJuegos().stream()
				.map(Juego::getIdJuego).toList();
	}

}
