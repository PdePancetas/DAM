package logic;


import java.util.InputMismatchException;

import jakarta.xml.bind.JAXBException;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int op = -1;
		do {
			System.out.println("\nElige una opcion: ");
			System.out.println("1. Insertar producto");
			System.out.println("2. Mostrar cliente con mayor factura");
			System.out.println("0. Salir");
			System.out.print("  -> ");

			try {
				op = Teclado.leerEntero();

				switch (op) {
				case 1:
					System.out.print("Introduce un id nuevo o existente: ");
					int id = Teclado.leerEntero();
					try {
						if (Func.pedidoExiste(id)) {
							System.out.print("Descripcion del producto: ");
							String descripcion = Teclado.leerCadena();
							System.out.print("Precio del producto: ");
							double precio = Teclado.leerDecimal();
							System.out.print("Num unidades del producto: ");
							int cantidad = Teclado.leerEntero();

							Func.insertarProducto(id, descripcion, precio, cantidad);

						} else {
							System.out.print("Nombre del nuevo cliente: ");
							String nombre = Teclado.leerCadena();
							System.out.print("Nif del nuevo cliente: ");
							String nif = Teclado.leerCadena();
							System.out.print("Descripcion del producto: ");
							String descripcion = Teclado.leerCadena();
							System.out.print("Precio del producto: ");
							double precio = Teclado.leerDecimal();
							System.out.print("Num unidades del producto: ");
							int cantidad = Teclado.leerEntero();

							Func.insertarProducto(id, nombre, nif, descripcion, precio, cantidad);

						}
					} catch (JAXBException e) {
						e.printStackTrace();
					}

					break;
				case 2:
					
					try {
						System.out.println("El cliente con la factura mas cara es "+Func.ClienteMayorFactura().toString());
					} catch (JAXBException e) {
						e.printStackTrace();
					}

					break;

				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opcion no valida");
					break;

				}

			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("Formato no valido");
			}
		} while (op != 0);

	}

}
