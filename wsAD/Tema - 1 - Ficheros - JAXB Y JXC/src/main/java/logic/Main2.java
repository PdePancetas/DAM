package logic;

import java.io.File;

import beansPedido.Pedido;
import beansPedido.Pedidos;
import beansPedido.Producto;
import beansPedido.Cliente;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class Main2 {

	public static void main(String[] args) throws JAXBException {

		Cliente cliente = new Cliente("1", "Julia");
		cliente.setId(1);
		
		Producto p1 = new Producto("ordenador", 2000, 5);
		Producto p2 = new Producto("pantalla", 10000, 1);
		
		Pedido ped = new Pedido();
		ped.setCliente(cliente);
		ped.getProductos().add(p1);
		ped.getProductos().add(p2);
		
		Pedidos pedidos = new Pedidos();
		pedidos.getPedidos().add(ped);
		
		escribirFichero(pedidos, new File("pedidos2.xml"));
		
	}

	
	public static void escribirFichero(Pedidos pedidos, File f) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		// Indentacion
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(pedidos, f);

	}

}
