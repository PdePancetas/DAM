package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import beans.Contacto;
import beans.Datos;
import dao.ContactoDao;
import daoImpl.ContactoDaoImpl;
import utilidadesTeclado.Teclado;

public class AppAgenda3 {
	
	public static void main(String[] args) {
		
		
//		System.out.println("Nombre de la agenda a trabajar: ");
//		String nombreAgenda = Teclado.leerCadena();
		
		//Leemos el nombre del fichero de un archivo de configuracion
		Properties props= new Properties();
		try {
			props.load(new FileInputStream("config.props"));
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		String nombreAgenda = props.getProperty("Agenda");
		
		
		
		ContactoDao dao  =new ContactoDaoImpl(new File(nombreAgenda));
		
		int op = -1;
		do {
			System.out.println("_______________________________________________________");
			System.out.println("Elige una opcion: ");
			System.out.println("1. Insertar contacto");
			System.out.println("2. Consultar contacto");
			System.out.println("3. Modificar contacto");
			System.out.println("4. Eliminar contacto");
			System.out.println("5. Listar Contactos");
			System.out.println("0. Salir \n");
			System.out.print("-> ");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				System.out.print("Nombre: ");
				String nombre = Teclado.leerCadena();
				System.out.print("direccion: ");
				String dir = Teclado.leerCadena();

				System.out.print("telefono: ");
				int tlf = Teclado.leerEntero();
				Contacto c = new Contacto(nombre, new Datos(tlf, dir));
				try {
					dao.addContacto(c);
				} catch (FileNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				
				break;
			case 2:
				System.out.println("Nombre: ");
				nombre = Teclado.leerCadena();
				try {
					System.out.println(dao.getContacto(nombre));
				} catch (FileNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Contacto a modificar: ");
				nombre = Teclado.leerCadena();
				System.out.print("Nueva direccion: ");
				dir = Teclado.leerCadena();

				System.out.print("Nuevo telefono: ");
				tlf = Teclado.leerEntero();
				
				c = new Contacto(nombre, new Datos(tlf, dir));
				
				try {
					dao.updateContacto(nombre, c);
				} catch (ClassNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				
				break;
				
			case 4:
				System.out.println("Contacto a eliminar: ");
				nombre = Teclado.leerCadena();
				
				try {
					dao.deleteContacto(nombre);
				} catch (FileNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				
				break;
			case 5:
				try {
					for (Contacto contacto : dao.listarContactos())
						System.out.println(contacto.toString());
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

	
	
	
	
}
