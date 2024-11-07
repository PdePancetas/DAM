package logic;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import objetosXJC.Pedidos;
import objetosXJC.Pedidos.Pedido;
import objetosXJC.Pedidos.Pedido.Cliente;
import objetosXJC.Pedidos.Pedido.ListaProductos;
import objetosXJC.Pedidos.Pedido.ListaProductos.Producto;

public class Func {

	static void insertarProducto(int id, String nombre, String nif, String desc, double precio, int cant)
			throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));
		Pedidos.Pedido pedido = new Pedido();
		Pedidos.Pedido.Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNif(nif);
		cliente.setNombre(nombre);
		Pedidos.Pedido.ListaProductos.Producto producto = new Producto();
		producto.setCantidad(cant);
		producto.setDescripcion(desc);
		producto.setPrecio(precio);
		Pedidos.Pedido.ListaProductos productos = new ListaProductos();
		productos.getProducto().add(producto);

		pedido.setCliente(cliente);
		pedido.setListaProductos(productos);
		pedidos.getPedido().add(pedido);

		escribirFichero(pedidos);

	}

	public static void insertarProducto(int id, String desc, double precio, int cant) throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		for (Pedidos.Pedido p : pedidos.getPedido())
			if (p.getCliente().getId() == id) {
				Pedidos.Pedido.ListaProductos.Producto producto = new Producto();
				producto.setCantidad(cant);
				producto.setDescripcion(desc);
				producto.setPrecio(precio);
				p.getListaProductos().getProducto().add(producto);

			}

		escribirFichero(pedidos);
	}

	static Pedidos.Pedido.Cliente ClienteMayorFactura() throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));
		Pedidos.Pedido pedidoMax = pedidos.getPedido().get(0);
		
		for(Pedidos.Pedido p: pedidos.getPedido()) {
			if(Func.precioTotal(p) > Func.precioTotal(pedidoMax))
				pedidoMax = p;
		}
			

		return pedidoMax.getCliente();
	}

	private static double precioTotal(Pedido p) {
		double precioTotal = 0;
		for(Pedidos.Pedido.ListaProductos.Producto producto : p.getListaProductos().getProducto())
			precioTotal+= producto.getPrecio()*producto.getCantidad();
		
		return precioTotal;
	}

	public static Pedidos leerFichero(File f) throws JAXBException { // Leer el
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Pedidos pedidos = (Pedidos) unmarshaller.unmarshal(f);

		return pedidos;
	}

	public static void escribirFichero(Pedidos pedidos) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(pedidos, new File("pedidos.xml"));

	}

	public static boolean pedidoExiste(int id) throws JAXBException {

		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		for (Pedidos.Pedido p : pedidos.getPedido())
			if (p.getCliente().getId() == id) {
				return true;
			}

		return false;
	}

}
