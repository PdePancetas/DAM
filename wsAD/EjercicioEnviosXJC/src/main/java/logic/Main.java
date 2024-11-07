package logic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;

import jakarta.xml.bind.JAXBException;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Con el esquema que se adjunta, realizar una aplicación que permita añadir un
		 * item de pedido, así como modificar el precio de un item de pedido ya
		 * existente.
		 */

		int op = -1;
		do {
			System.out.println("\nElige una opcion: ");
			System.out.println("1. Añadir item a pedido");
			System.out.println("2. Modificar item de pedido");
			System.out.println("0. Salir");
			System.out.print("  -> ");

			try {
				op = Teclado.leerEntero();

				switch (op) {
				case 1:
					System.out.print("Id de pedido: ");
					String id = Teclado.leerCadena();
					try {
						if (Func.existePedido(id)) {
							System.out.print("Nombre de item: ");
							String title = Teclado.leerCadena();
							System.out.print("Descripcion del item: ");
							String note = Teclado.leerCadena();
							System.out.print("Cantidad del item: ");
							BigInteger quantity = BigInteger.valueOf(Teclado.leerEntero());
							System.out.print("Precio del item: ");
							BigDecimal price = BigDecimal.valueOf(Teclado.leerDecimal());

							Func.addItem(id, title, note, quantity, price);
						} else
							System.err.println("No existe ese pedido");
					} catch (JAXBException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.print("Id de pedido: ");
					id = Teclado.leerCadena();
					try {
						if (Func.existePedido(id)) {
							System.out.print("Nombre de item: ");
							String title = Teclado.leerCadena();
							if (Func.existeItem(id, title)) {
								System.out.print("Nuevo precio del item: ");
								BigDecimal newPrice = BigDecimal.valueOf(Teclado.leerDecimal());
								Func.changeItemPrice(id, title, newPrice);
							} else
								System.err.println("No existe ese item");

						} else
							System.err.println("No existe ese pedido");
					} catch (JAXBException e) {
						e.printStackTrace();
					}
					break;

				}

			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("Formato no valido");
			}
		} while (op != 0);

	}


}
