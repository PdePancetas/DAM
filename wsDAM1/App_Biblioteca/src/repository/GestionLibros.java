package repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import data.Libro;
import utilidadesTeclado.Teclado;

public class GestionLibros {

	private static int op = -1;

	public static void menu() throws InterruptedException, IOException {

		do {
			System.out.println("Elige una opcion: ");
			System.out.println("   1. Añadir libro");
			System.out.println("   2. Eliminar libro");
			System.out.println("   3. Buscar por libro o categoria");
			System.out.println("   4. Listar libros");
			System.out.println("0. Salir al menu principal\n");

			System.out.print("-> ");

			try {
				op = Teclado.leerEntero();
			} catch (Exception e) {
				System.out.println("Opcion no valida\n");
				op = -1;
			}

			switch (op) {
			case 1:
				try {
					System.out.print("Introduce nombre: ");
					String nombre = Teclado.leerCadena();
					System.out.print("Introduce descripcion: ");
					String desc = Teclado.leerCadena();
					System.out.print("Introduce categoria: ");
					String categoria = Teclado.leerCadena();
					System.out.print("Introduce numero de copias: ");
					int numCopias = Teclado.leerEntero();
					Ops.addBook(nombre, desc, categoria, numCopias);
					Ops.saveBooks(Ops.getLibros());
					System.out.println("Libro añadido. \n");
				} catch (Exception e) {
					System.out.println(e.toString() + "\n");
				}

				break;
			case 2:
				try {
					System.out.print("Introduce nombre: ");
					String nombre = Teclado.leerCadena();
					Ops.removeBook(nombre);
					Ops.saveBooks(Ops.getLibros());
					System.out.println("Libro eliminado");
				} catch (Exception e) {
					System.out.println(e.toString() + "\n");
				}
				break;

			case 3:
				try {
					System.out.print("Introduce opcion (1 nombre, 2 categoria): ");
					int op = Teclado.leerEntero();
					if (op == 1) {
						System.out.print("Introduce nombre: ");
						String nombre = Teclado.leerCadena();
						System.out.print(Ops.searchByName(nombre).toString());
					} else if (op == 2) {
						System.out.print("Introduce categoria: ");
						String categoria = Teclado.leerCadena();
						for (Libro l : Ops.searchByCategory(categoria))
							System.out.println(l.toString());
					}
				} catch (Exception e) {
					System.out.println(e.toString() + "\n");
				}
				break;
			case 4:
				System.out.print("Introduce criterio (Nombre,Descripcion,Categoria o Copias): ");
				String criterio = Teclado.leerCadena();
				try {
					Ops.listBooks(criterio);
				} catch (Exception e) {
					System.out.println("El criterio introducido no es valido.");
				}

				break;
			case 0:
				try {
					Ops.saveBooks(Ops.getLibros());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			}

		} while (op != 0);

	}

}