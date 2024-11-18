package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import beans.Contacto;
import beans.Datos;
import utilidadesTeclado.Teclado;

public class AppAgenda1 {

	private static TreeMap<Integer, Contacto> agenda = new TreeMap<>();
	private static int id;

	public static void main(String[] args) {

		System.out.println("Nombre de la agenda a trabajar: ");
		String nombreAgenda = Teclado.leerCadena();

		try {
			recuperarContactos(new File(nombreAgenda));
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Hubo un error al recuperar los datos");
			e.printStackTrace();
		}

		int op = -1;
		do {
			System.out.println("_______________________________________________________");
			System.out.println("Elige una opcion: ");
			System.out.println("1. Insertar");
			System.out.println("2. Consultar ");
			System.out.println("3. Guardar");
			System.out.println("0. Salir");

			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				insContacto();
				break;
			case 2:

				System.out.print("Nombre: ");
				String nombre = Teclado.leerCadena();
				for (Contacto c : recuperaContacto(nombre))
					System.out.println(c.toString());
				break;

			case 3:

				try {
					guardarAgenda(new File(nombreAgenda));
				} catch (Exception e) {
					System.out.println("Hubo un error al guardar la agenda");
					e.printStackTrace();
					break;
				}
				System.out.println("Agenda guardada");
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

	@SuppressWarnings("unchecked")
	private static void recuperarContactos(File nombreAgenda) throws IOException, ClassNotFoundException {
		if (!nombreAgenda.exists())
			return;

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreAgenda));
		agenda = (TreeMap<Integer, Contacto>) ois.readObject();
		System.out.println("Contactos recuperados con exito.");

		ois.close();
		Set<Integer> claves = agenda.keySet();
		int max = 0;
		for (Integer i : claves)
			if (i > max)
				max = i;
		id = max++;

	}

	private static void insContacto() {
		System.out.print("Nombre: ");
		String nombre = Teclado.leerCadena();

		System.out.print("direccion: ");
		String dir = Teclado.leerCadena();

		System.out.print("telefono: ");
		int tlf = Teclado.leerEntero();

		agenda.put(id++, new Contacto(nombre, new Datos(tlf, dir)));

	}

	private static LinkedList<Contacto> recuperaContacto(String nombre) {

		LinkedList<Contacto> contactos = new LinkedList<>();

		for (Map.Entry<Integer, Contacto> cts : agenda.entrySet()) {
			if (cts.getValue().getNombre().equals(nombre))
				contactos.add(cts.getValue());
		}

//		Set<Integer> claves = agenda.keySet();
//		Iterator<Integer> it = claves.iterator();
//
//		while (it.hasNext()) {
//			Integer clave = it.next();
//			if (agenda.get(clave).getNombre().equals(nombre))
//				contactos.add(agenda.get(clave));
//		}

		return contactos;
	}

	private static void guardarAgenda(File f) throws IOException {

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

		oos.writeObject(agenda);
		oos.close();
	}

}
