package handler;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import beans.Cuenta;

public class HandlerCuentas extends DefaultHandler {

	private LinkedList<Cuenta> cuentas = new LinkedList<>();
	private boolean enIdCuenta, enNombreTitular, enCantidad;
	private String nombreTitular;
	private int cant, idCuenta;

	private Cuenta cuenta = new Cuenta();
	private LinkedList<String> titulares = new LinkedList<String>();

	public LinkedList<Cuenta> getCuentas() {
		return cuentas;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equals("idCuenta")) {
			enIdCuenta = true;
		} else if (qName.equals("nombreTitular")) {
			enNombreTitular = true;
		} else if (qName.equals("cantidad")) {
			enCantidad = true;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (enIdCuenta) {
			idCuenta = Integer.parseInt(new String(ch, start, length));
			enIdCuenta = false;
			cuenta.setIdCuenta(idCuenta);
		} else if (enNombreTitular) {
			nombreTitular = new String(ch, start, length);
			enNombreTitular = false;
			titulares.add(nombreTitular);
		} else if (enCantidad) {
			cant = Integer.parseInt(new String(ch, start, length));
			enCantidad = false;
			cuenta.setCantidad(cant);
			cuentas.add(cuenta);
			cuenta = new Cuenta();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equals("titulares")) {
			cuenta.setTitulares(titulares);
			titulares = new LinkedList<String>();
		}
	}

}
