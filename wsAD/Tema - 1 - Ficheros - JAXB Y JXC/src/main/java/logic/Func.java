package logic;

import java.io.File;
import java.util.Iterator;

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
		Pedido pedido = new Pedido(new Cliente(nombre, nif, id), new ListaProductos());
		pedido.getListaProductos().getProducto().add(new Producto(desc, precio, cant));

		escribirFichero(pedidos);

	}

	public static void insertarProducto(int id, String desc, double precio, int cant) throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		for (Pedido p : pedidos.getPedido())
			if (p.getCliente().getId() == id)
				p.getListaProductos().getProducto().add(new Producto(desc, precio, cant));

		escribirFichero(pedidos);
	}

	static Pedido ClienteMayorFactura() throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));
		Pedido pedidoMax = pedidos.getPedido().get(0);

		for (Pedido p : pedidos.getPedido()) {
			if (Func.precioTotal(p) > Func.precioTotal(pedidoMax))
				pedidoMax = p;
		}

		return pedidoMax;
	}

	public static double precioTotal(Pedido p) {
		double precioTotal = 0;
		for (Producto producto : p.getListaProductos().getProducto())
			precioTotal += producto.getPrecio() * producto.getCantidad();

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

	public static boolean productoExiste(int id, String desc) throws JAXBException {

		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		for (Pedido pe : pedidos.getPedido())
			if (pe.getCliente().getId() == id)
				for (Producto pr : pe.getListaProductos().getProducto())
					if (pr.getDescripcion().equals(desc))
						return true;

		return false;
	}

	public static void eliminarProducto(int id, String desc) throws JAXBException {
		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		Iterator<Pedido> it = pedidos.getPedido().iterator();
		while (it.hasNext()) {
			Pedido pe = it.next();
			if (pe.getCliente().getId() == id)
				if (pe.getListaProductos().getProducto().contains(new Producto(desc, 0, 0)))
					pe.getListaProductos().getProducto().remove(new Producto(desc, 0, 0));
		}

		escribirFichero(pedidos);
	}

	public static void eliminarPedido(int id) throws JAXBException {

		Pedidos pedidos = leerFichero(new File("pedidos.xml"));

		for (int i = 0; i < pedidos.getPedido().size(); i++)
			if (pedidos.getPedido().get(i).getCliente().getId() == id)
				pedidos.getPedido().remove(i);

		escribirFichero(pedidos);

	}

}
