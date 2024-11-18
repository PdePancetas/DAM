package logic;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import beans.Pedido;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import handler.HandlerPedidos;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		HandlerPedidos handler = new HandlerPedidos();

		saxParser.parse("pedidos.xml", handler);

		for (Pedido ped : handler.getPedidos()) {
			System.out.println("Cliente: " + ped.getCliente().getNombre()+", id: "+ped.getCliente().getId());
			System.out.println("Productos: " + ped.getListaProductos().size());
		}
	}
	

}
