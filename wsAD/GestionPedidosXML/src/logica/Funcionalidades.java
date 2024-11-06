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
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	public static void insertarPedido(String nombre, String nif, String descripcion, double precio, int cantidad)
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException,
			TransformerFactoryConfigurationError, TransformerException {
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

		// PEDIDO
		Element pedido = doc.createElement("pedido");
		// CLIENTE
		Element cliente = doc.createElement("cliente");
		Element nombreCliente = doc.createElement("nombre");
		nombreCliente.appendChild(doc.createTextNode(nombre));
		cliente.appendChild(nombreCliente);

		Element nifCliente = doc.createElement("nif");
		nifCliente.appendChild(doc.createTextNode(nif));
		cliente.appendChild(nifCliente);

		// PRODUCTO
		Element producto = doc.createElement("producto");
		Element descripcionProducto = doc.createElement("descripcion");
		descripcionProducto.appendChild(doc.createTextNode(descripcion));
		producto.appendChild(descripcionProducto);

		Element precioProducto = doc.createElement("precio");
		precioProducto.appendChild(doc.createTextNode(precio + ""));
		producto.appendChild(precioProducto);

		Element cantidadProducto = doc.createElement("cantidad");
		cantidadProducto.appendChild(doc.createTextNode(cantidad + ""));
		producto.appendChild(cantidadProducto);

		pedido.appendChild(cliente);
		pedido.appendChild(producto);
		raiz.appendChild(pedido);

		escribirFicheroXML(doc);

	}

	public static void deletePedido(String nif) throws FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = leerFicheroXML();

		Node raiz = doc.getFirstChild();

		NodeList pedidos = raiz.getChildNodes();

		for (int i = 0; i < pedidos.getLength(); i++) {
			if (pedidos.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if (((Element) pedidos.item(i)).getElementsByTagName("nif").item(0).getTextContent().equals(nif))
					raiz.removeChild(pedidos.item(i));
			}

		}

		escribirFicheroXML(doc);
	}

	public static String showPedidos(String nif) throws FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {

		String datos = "";

		Document doc = leerFicheroXML();

		Node raiz = doc.getFirstChild();

		NodeList pedidos = raiz.getChildNodes();
		Element pedido = null;
		for (int i = 0; i < pedidos.getLength(); i++) {
			if (pedidos.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if (((Element) pedidos.item(i)).getElementsByTagName("nif").item(0).getTextContent().equals(nif)) {
					pedido = (Element) pedidos.item(i);
					datos += mostrarInfo(pedido);
				}
			}

		}

		return datos;
	}

	private static String mostrarInfo(Element pedido) {
		String datos = "";

		String nombreCliente = pedido.getElementsByTagName("nombre").item(0).getTextContent();
		datos += "Nombre del cliente: " + nombreCliente + "\n";

		String descripcion = pedido.getElementsByTagName("descripcion").item(0).getTextContent();
		datos += "Producto: " + descripcion + "\n";
		String precio = pedido.getElementsByTagName("precio").item(0).getTextContent();
		datos += "Precio: " + precio + "\n";
		String cantidad = pedido.getElementsByTagName("cantidad").item(0).getTextContent();
		datos += "Cantidad: " + cantidad + "\n";

		datos += "-----------------------------------\n";

		return datos;
	}

	public static double calcPrecioPedidos(String nif)
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		double suma = 0;

		Document doc = leerFicheroXML();

		Node raiz = doc.getFirstChild();
		NodeList pedidos = raiz.getChildNodes();
		Element pedido = null;
		for (int i = 0; i < pedidos.getLength(); i++)
			if (pedidos.item(i).getNodeType() == Node.ELEMENT_NODE)
				if (((Element) pedidos.item(i)).getElementsByTagName("nif").item(0).getTextContent().equals(nif)) {
					pedido = (Element) pedidos.item(i);
					suma += Double.parseDouble(pedido.getElementsByTagName("precio").item(0).getTextContent());
				}

		return suma;
	}

}
