package logic;

import java.io.File;

import beansLibro.Libro;
import beansLibro.Libros;
import beansPedido.Pedido;
import beansPedido.Pedidos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Main {

	public static void main(String[] args) throws JAXBException {

		Libros libros = leerFicheroLibros(new File("libros.xml"));

		System.out.println(getTituloAutor(libros, "Pepe"));
		
		Pedidos pedidos = leerFicheroPedidos(new File("pedidos.xml"));
		
		System.out.println(getNombreId(pedidos, 1));

	}

	private static String getTituloAutor(Libros libros, String autor) {

		for (Libro l : libros.getLibro())
			if (l.getAutor().equals(autor))
				return l.getTitulo();
		// TODO Auto-generated method stub
		return null;
	}

	private static String getNombreId(Pedidos pedidos, int id) {

		for (Pedido p : pedidos.getPedidos())
			if (p.getCliente().getId() == id)
				return p.getCliente().getNombre();
		// TODO Auto-generated method stub
		return null;
	}

	public static Libros leerFicheroLibros(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		// Contexto : Clase Libros
		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

		// Como el parser
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso de fichero a objeto
		Libros libros = (Libros) unmarshaller.unmarshal(f);

		return libros;
	}
	
	public static Pedidos leerFicheroPedidos(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		// Contexto : Clase Libros
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);

		// Como el parser
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso de fichero a objeto
		Pedidos pedidos = (Pedidos) unmarshaller.unmarshal(f);

		return pedidos;
	}

}
