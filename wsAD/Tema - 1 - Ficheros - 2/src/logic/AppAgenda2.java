package logic;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import beans.Contacto;
import beans.Datos;
import io.ObjectOutputStreamSinHeader;
import utilidadesTeclado.Teclado;

public class AppAgenda2 {

	private static File ficheroAgenda;

	public static void main(String[] args) {

		System.out.println("Nombre de la agenda a trabajar: ");
		String nombreAgenda = Teclado.leerCadena();

		ficheroAgenda = new File(nombreAgenda);

		int op = -1;
		do {
			System.out.println("_______________________________________________________");
			System.out.println("Elige una opcion: ");
			System.out.println("1. Insertar contacto");
			System.out.println("2. Consultar contacto");
			System.out.println("3. Eliminar contacto");
			System.out.println("4. Listar Contactos");
			System.out.println("0. Salir \n");
			System.out.println("->");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				try {
					insertarContacto();
				} catch (FileNotFoundException e) {
					System.err.println("No se ha encontrado el fichero agenda");
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println("No se puedo insertar el contacto");
					e.printStackTrace();
				}

				break;
			case 2:

				System.out.print("Nombre: ");
				String nombre = Teclado.leerCadena();
				try {
					for (Contacto c : recuperaContacto(nombre))
						System.out.println(c.toString());
				} catch (FileNotFoundException e) {
					System.err.println("No se ha encontrado el fichero agenda");
					e.printStackTrace();
				} catch (ClassNotFoundException | IOException e) {
					System.err.println("No se ha encontrado el contacto especificado");
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.print("Elige contacto: ");
				nombre = Teclado.leerCadena();
				try {
					eliminarContacto(nombre);
				} catch (IOException e) {
					System.err.println("Hubo un error I/O");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.err.println("No se ha encontrado el contacto especificado");
					e.printStackTrace();
				}

				System.out.println("Contacto eliminado");

				break;
			case 4:
				try {
					for (Contacto c : listarContactos())
						System.out.println(c.toString());
				} catch (FileNotFoundException e) {
					System.err.println("No se ha encontrado el fichero agenda");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.err.println("No se ha encontrado el contacto especificado");
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println("Hubo un error I/O");
					e.printStackTrace();
				}
				break;

			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Esa no es una opcion valida");
				break;
			}

		} while (op != 0);

	}

	/**
	 * 
	 * @param nombre
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void eliminarContacto(String nombre) throws IOException, ClassNotFoundException {

		File f = new File("temp");

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

		boolean finArchivo = false;

		while (!finArchivo)
			try {
				Contacto c = (Contacto) ois.readObject();
				if (!c.getNombre().equals(nombre))
					oos.writeObject(c);
			} catch (EOFException e) {
				finArchivo = true;
			}
		ois.close();
		oos.close();

		ficheroAgenda.delete();
		f.renameTo(ficheroAgenda);

	}

	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static LinkedList<Contacto> listarContactos()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		LinkedList<Contacto> contactos = new LinkedList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo)
			try {
				contactos.add((Contacto) ois.readObject());
			} catch (EOFException e) {
				finArchivo = true;
				ois.close();
			}

		return contactos;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static LinkedList<Contacto> recuperaContacto(String nombre)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		LinkedList<Contacto> contactos = new LinkedList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo)
			try {
				Contacto c = (Contacto) ois.readObject();
				if (c.getNombre().equals(nombre))
					contactos.add(c);
			} catch (EOFException e) {
				finArchivo = true;
				ois.close();
			}

		return contactos;
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void insertarContacto() throws FileNotFoundException, IOException {
		System.out.print("Nombre: ");
		String nombre = Teclado.leerCadena();

		System.out.print("direccion: ");
		String dir = Teclado.leerCadena();

		System.out.print("telefono: ");
		int tlf = Teclado.leerEntero();
		Contacto c = new Contacto(nombre, new Datos(tlf, dir));

		ObjectOutputStream oos;
		if (ficheroAgenda.exists())
			oos = new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroAgenda, true));
		else
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda));

		oos.writeObject(c);
		oos.close();
	}

}
