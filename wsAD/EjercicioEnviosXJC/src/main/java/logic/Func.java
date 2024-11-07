package logic;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import objetosXJC.ShipOrder;
import objetosXJC.ShipOrder.Item;

public class Func {

	public static ShipOrder leerFichero(File f) throws JAXBException { // Leer el
		JAXBContext jaxbContext = JAXBContext.newInstance(ShipOrder.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ShipOrder pedidos = (ShipOrder) unmarshaller.unmarshal(f);

		return pedidos;
	}

	public static void escribirFichero(ShipOrder shipOrder) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ShipOrder.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(shipOrder, new File("pedidos.xml"));

	}

	public static void addItem(String orderId, String title, String note, BigInteger quantity, BigDecimal price)
			throws JAXBException {
		ShipOrder shipOrder = leerFichero(new File("pedidos.xml"));
		if (shipOrder.getOrderid().equals(orderId)) {
			shipOrder.getItem().add(new Item(title, note, quantity, price));
		}

		escribirFichero(shipOrder);
	}

	public static void changeItemPrice(String orderId, String title, BigDecimal newPrice) throws JAXBException {

		ShipOrder shipOrder = leerFichero(new File("pedidos.xml"));
		if (shipOrder.getOrderid().equals(orderId)) {
			for (Item i : shipOrder.getItem()) {
				if (i.getTitle().equals(title))
					i.setPrice(newPrice);
			}
		}

		escribirFichero(shipOrder);
	}

	public static boolean existePedido(String id) throws JAXBException {
		ShipOrder shipOrder = leerFichero(new File("pedidos.xml"));
		if (shipOrder.getOrderid().equals(id))
			return true;
		return false;
	}

	public static boolean existeItem(String id, String title) throws JAXBException {
		ShipOrder shipOrder = leerFichero(new File("pedidos.xml"));
		if (shipOrder.getOrderid().equals(id))
			for (Item i : shipOrder.getItem())
				if (i.getTitle().equals(title))
					return true;

		return false;
	}
}
