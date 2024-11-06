package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import exception.VoidException;
import model.Pedido;
import model.Producto;
import objects.Object_Output_StreamSinHeader;
import objects.Object_Input_StreamSinHeader;

public class Ops {

	public static String readFile(File nomFich) throws IOException {
		FileReader fr = new FileReader(nomFich);
		int c;
		String texto = "";
		while ((c = fr.read()) != -1) {
			texto += (char) c;
		}
		fr.close();

		return texto;
	}

	public static void insertarPedido(int codigo, String nombre, String texto, File f)
			throws FileNotFoundException, IOException, VoidException, ClassNotFoundException {

		if (nombre.equals(""))
			throw new VoidException();
		if (texto.equals(""))
			throw new VoidException();

		ObjectOutputStream oos;
		if (!f.exists())
			oos = new ObjectOutputStream(new FileOutputStream(f, true));
		else
			oos = new Object_Output_StreamSinHeader(new FileOutputStream(f, true));

		Pedido pedido = new Pedido();
		pedido.setCodigo(codigo);
		pedido.setNombreCliente(nombre);

		for (int i = 0; i < texto.split("\n").length; i++) {
			Producto producto = new Producto();
			producto.setNombre(texto.split("\n")[i].split("-")[0]);
			producto.setPrecio(Double.parseDouble(texto.split("\n")[i].split("-")[1]));
			pedido.getProductos().add(producto);
		}

		oos.writeObject(pedido);

	}

	public static HashMap<Pedido, Double> getPedidoYPrecio(File f, String cliente)
			throws IOException, ClassNotFoundException {

		HashMap<Pedido, Double> pedidos = new HashMap<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Pedido p = (Pedido) ois.readObject();
				if (p.getNombreCliente().equals(cliente))
					pedidos.put(p, precioTotalPedido(p));

			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		return pedidos;

	}

	public static ArrayList<Pedido> getPedidosPorProductoYCliente(File f, String cliente, String producto)
			throws IOException, ClassNotFoundException {

		ArrayList<Pedido> pedidos = new ArrayList<>();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Pedido p = (Pedido) ois.readObject();
				if (p.getNombreCliente().equals(cliente) && p.getProductos().contains(new Producto(producto, 0)))
					pedidos.add(p);

			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		return pedidos;

	}

	public static HashMap<Pedido, Double> getPedidosPorProducto(File f, String producto)
			throws IOException, ClassNotFoundException {

		HashMap<Pedido, Double> pedidos = new HashMap<>();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Pedido p = (Pedido) ois.readObject();
				for (Producto pr : p.getProductos())
					if (pr.getNombre().equals(producto))
						pedidos.put(p, precioTotalPedido(p));

			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		return pedidos;

	}

	public static double precioTotalPedido(Pedido pe) {
		int total = 0;

		for (Producto pr : pe.getProductos())
			total += pr.getPrecio();

		return total;

	}

	public static Double precioTotalPedidos(HashMap<Pedido, Double> pedidos) {
		double precioTotal = 0;
		Set<Entry<Pedido, Double>> pedidoSet = pedidos.entrySet();
		for (Entry<Pedido, Double> entry : pedidoSet) {
			precioTotal += entry.getValue();
		}
		return precioTotal;
	}

}
