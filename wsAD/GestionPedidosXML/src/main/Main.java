package main;

import utilidadesTeclado.Teclado;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import logica.Funcionalidades;

public class Main {

	public static void main(String[] args) {

		/*
		  Las funcionalidades de la aplicación serán:
		-	Insertar pedido (si el archivo no existe se creará)
		-	Eliminar pedidos de un cliente dado su nif
		-	Mostrar pedidos de un cliente dado su nif
		-	Mostrar el gasto de un cliente dado su nif

		 */
		
		/*
		 * <pedidos>
				<pedido>
					<cliente>
						<nombre>xx</nombre>
						<nif>xxx</nif>
					</cliente>
					<producto>
						<descripcion>xx</descripcion>
						<precio>xxx</precio>
						<cantidad>xxx</cantidad>
					</producto>
				</pedido>
			</pedidos>

		 */
		
		int op = -1;
		do {
			System.out.println("\nElige una opcion: ");
			System.out.println("1. Insertar pedido");
			System.out.println("2. Eliminar pedidos de un cliente");
			System.out.println("3. Mostrar pedidos de un cliente");
			System.out.println("4. Mostrar el gasto de un cliente");
			System.out.println("0. Salir");
			System.out.print("  -> ");

			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				System.out.println("Introduce los datos del cliente");
				System.out.print("Nombre: ");
				String nombre = Teclado.leerCadena();
				System.out.print("Nif: ");
				String nif = Teclado.leerCadena();

				System.out.println("\nIntroduce los datos del producto");
				System.out.print("Descripcion: ");
				String descripcion = Teclado.leerCadena();
				System.out.print("Precio: ");
				double precio = Teclado.leerDecimal();
				System.out.print("Cantidad: ");
				int cantidad = Teclado.leerEntero();

				try {
					Funcionalidades.insertarPedido(nombre, nif, descripcion, precio, cantidad);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}

				break;
			case 2:
				System.out.print("Introduce nif: ");
				nif = Teclado.leerCadena();
				
				try {
					Funcionalidades.deletePedido(nif);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				
				break;
			case 3:
				System.out.print("Introduce nif: ");
				nif = Teclado.leerCadena();
				
				try {
					System.out.println(Funcionalidades.showPedidos(nif));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}

				break;
			case 4:
				System.out.print("Introduce nif: ");
				nif = Teclado.leerCadena();
				
				try {
					double precioTotal = Funcionalidades.calcPrecioPedidos(nif);
					System.out.println("Gasto total de los pedidos con nif "+nif+": "+precioTotal);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
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
		} while (op != 0);

	}

}
