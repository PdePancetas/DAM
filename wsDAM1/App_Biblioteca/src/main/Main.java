package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import repository.Ops;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		try {
			Ops.inputBooks();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int op = -1;

		do {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor(); // Clear (cls)
			System.out.println("Elige una opcion: ");
			System.out.println("   1. Gestion de Libros");
			System.out.println("   2. Devoluciones y Prestamos");
			System.out.println("   3. Generar informes\n");
			System.out.println("0. Salir\n");

			System.out.print("-> ");

			try {
				op = Teclado.leerEntero();
			} catch (Exception e) {
				System.out.println("Opcion no valida\n");
				op = -1;
			}

			switch (op) {
			case 1:
				repository.GestionLibros.menu();
				break;
			case 2:
				repository.GestionDevPrest.menu();
				break;
			case 3:
				repository.GestionInformes.menu();
				break;
			case 0:
				System.out.println("\nSaliendo...");
				new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();

				try {
					Ops.saveBooks(Ops.getLibros());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} while (op != 0);

	}

}
