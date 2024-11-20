package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
								"El juego está intercambiado, los id de usuarios implicados en el intercambio son: ");

						int ID_Emisor = Integer.parseInt(
								intercambio.getElementsByTagName("ID_Usuario_Emisor").item(0).getTextContent());
						int ID_Receptor = Integer.parseInt(
								intercambio.getElementsByTagName("ID_Usuario_Receptor").item(0).getTextContent());

						System.out.println("ID usuario emisor: " + ID_Emisor);
						System.out.println("ID usuario receptor: " + ID_Receptor);
						System.out.println("ID juego intercambiado: " + ID_Juego);
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

			// Eliminar usuario de usuarios
			System.out.println("Usuarios: ");
			users.stream().forEach(System.out::println);

			users.remove(new Usuario(idUsuario, ""));
			System.out.println("\nUsuarios habiendo eliminado Id de usuario " + idUsuario + ": ");
			users.stream().forEach(System.out::println);

			// Eliminar intercambios que contengan en emisor o receptor id idUsuario
			System.out.println("\nIntercambios: ");
			exchanges.stream().forEach(System.out::println);

			for (int i = 0; i < exchanges.size(); i++) {
				if (exchanges.get(i).getIdEmisor() == idUsuario || exchanges.get(i).getIdReceptor() == idUsuario)
					exchanges.remove(i);
			}
			System.out.println("Intercambios habiendo eliminado Id de usuario " + idUsuario + ": ");
			exchanges.stream().forEach(System.out::println);

			System.out.println("\n Se ha eliminado con exito al usuario " + idUsuario);

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

		Usuario user = getUser(idUsuario);

		Juego game = getGame(idUsuario);

		ArrayList<Usuario> users = getUsersExchange(idUsuario);

		datos.add(new Dato(user.getIdUsuario(), user.getNombre(), users, game.getTitulo()));

		String ficheroJasper = Properties.getConfig().getProperty("ficheroJasper");
		String informePdf = Properties.getConfig().getProperty("ficheroPdf");

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idUsuario", user.getIdUsuario());
		params.put("nombreUsuario", user.getNombre());

		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		JasperExportManager.exportReportToPdfFile(informe, informePdf);
	}

	private static Usuario getUser(int idUsuario) {

		BibliotecaVideojuegos bibliotecaVideojuegos;
		try {
			bibliotecaVideojuegos = leerFicheroJAXB(getFichero());
			for (Usuario user : bibliotecaVideojuegos.getUsuarios()) {
				if (user.getIdUsuario() == idUsuario)
					return user;
			}
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static Juego getGame(int idUsuario) {

		BibliotecaVideojuegos bibliotecaVideojuegos;
		try {
			bibliotecaVideojuegos = leerFicheroJAXB(getFichero());
			int idJuego = 0;
			boolean esta = false;
			for (Intercambio exchange : bibliotecaVideojuegos.getIntercambios())
				if (exchange.getIdEmisor() == idUsuario || exchange.getIdReceptor() == idUsuario) {
					idJuego = exchange.getIdJuego();
					esta = true;
				}

			if (esta)
				for (Juego game : bibliotecaVideojuegos.getJuegos())
					if (game.getIdJuego() == idJuego)
						return game;

		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static ArrayList<Usuario> getUsersExchange(int idUsuario) {

		BibliotecaVideojuegos bibliotecaVideojuegos;
		ArrayList<Usuario> users = new ArrayList<>();
		try {
			bibliotecaVideojuegos = leerFicheroJAXB(getFichero());

			for (Intercambio exchange : bibliotecaVideojuegos.getIntercambios())
				if (exchange.getIdEmisor() == idUsuario || exchange.getIdReceptor() == idUsuario) {
					users.add(new Usuario(exchange.getIdEmisor(), "emisor"));
					users.add(new Usuario(exchange.getIdReceptor(), "receptor"));
					return users;
				}

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
