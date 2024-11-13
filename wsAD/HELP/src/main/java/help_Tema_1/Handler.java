package help_Tema_1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

//	private LinkedList<Pedido> pedidos = new LinkedList<>();
//	private boolean enNombre, enNif, enDescripcion, enPrecio, enCantidad;
//	private String nombre, nif, desc, precio, cant;
//
//	private Cliente cli = new Cliente();
//	private Pedido ped = new Pedido();
//	private Producto pro = new Producto();
//
//	public LinkedList<Pedido> getPedidos() {
//		return pedidos;
//	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

//		if (qName.equals("nombre")) {
//			enNombre = true;
//		} else if (qName.equals("nif")) {
//			enNif = true;
//		} else if (qName.equals("descripcion")) {
//			enDescripcion = true;
//		} else if (qName.equals("precio")) {
//			enPrecio = true;
//		} else if (qName.equals("cantidad")) {
//			enCantidad = true;
//		} else if (qName.equals("cliente")) { ///ESTO PARA ATRIBUTOS TAMBIEN
//			cli.setId(Integer.parseInt(attributes.getValue("id")));
//		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

//		if (enNombre) {
//			nombre = new String(ch, start, length);
//			enNombre = false;
//			cli.setNombre(nombre);
//		} else if (enNif) {
//			nif = new String(ch, start, length);
//			enNif = false;
//			cli.setNif(nif);
//		} else if (enDescripcion) {
//			desc = new String(ch, start, length);
//			enDescripcion = false;
//			pro.setDescripcion(desc);
//		} else if (enPrecio) {
//			precio = new String(ch, start, length);
//			enPrecio = false;
//			pro.setPrecio(Double.parseDouble(precio));
//		} else if (enCantidad) {
//			cant = new String(ch, start, length);
//			enCantidad = false;
//			pro.setCantidad(Integer.parseInt(cant));
//		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		if (qName.equals("cliente")) {
//			ped.setCliente(cli);
//			cli = new Cliente(); // con el set se sobreescribirian los datos, pero asi queda mas claro
//		} else if (qName.equals("producto")) {
//			ped.getListaProductos().add(pro);
//			pro = new Producto();
//		} else if (qName.equals("listaProductos")) {
//			pedidos.add(ped);
//			ped = new Pedido();
//		}
	}

}
