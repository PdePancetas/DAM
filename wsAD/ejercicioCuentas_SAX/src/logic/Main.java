package logic;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import handler.HandlerCuentas;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		HandlerCuentas handler = new HandlerCuentas();

		saxParser.parse("cuentas.xml", handler);

//		Utilizando SAX, mostrar el total de dinero de cuentas que tiene un titular.
		System.out.println("Introduce nombre de titular");
		String nombreTitular = Teclado.leerCadena();
		System.out.println(Func.mostrarDineroTotal(nombreTitular, handler.getCuentas()));
		
	}
	

}
