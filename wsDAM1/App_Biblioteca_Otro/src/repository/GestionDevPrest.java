package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import data.Movimiento;
import utilidadesTeclado.Teclado;

public class GestionDevPrest {

	private static int op = -1;

	public static void menu() throws InterruptedException, IOException {

		do {
			System.out.println("Elige una opcion: ");
			System.out.println("   1. Registrar devolucion o prestamo");
			System.out.println("   2. Consultar historial de movimientos");
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
					System.out.print("Introduce nombre del libro: ");
					String nombre = Teclado.leerCadena();
					System.out.print("Introduce fecha(dd-mm-yy): ");
					String fechaMov = Teclado.leerCadena();
					System.out.print("Introduce tipo (Devolucion o Prestamo): ");
					String tipo = Teclado.leerCadena();
					System.out.print("Introduce numero de copias: ");
					int numCopias = Teclado.leerEntero();
					Ops.returnOrLoanBook(nombre, fechaMov, tipo, numCopias);
					Ops.saveBooks(Ops.getLibros());
					System.out.println("Movimiento guardado y realizado \n");
				} catch (FileNotFoundException e) {
					System.out.println(e.toString()+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("La fecha introducida no tiene un formato valido"+"\n");
				} catch (NumberFormatException e) {
					System.out.println(e.toString()+"\n");
				}
				break;
			case 2:
				try {
					System.out.print("Introduce nombre: ");
					String nombre = Teclado.leerCadena();
					for (Movimiento m : Ops.checkMovesByBook(nombre))
						System.out.println(m.toString());
				} catch (FileNotFoundException e) {
					System.out.println(e.toString()+"\n");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
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