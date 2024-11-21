package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import utilidadesTeclado.Teclado;

public class GestionInformes {

	private static int op = -1;

	public static void menu() throws InterruptedException, IOException {

		do {
			System.out.println("Elige una opcion: ");
			System.out.println("   1. Generar informe de libros");
			System.out.println("   2. Generar informe de prestamos");
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
					Ops.genInfoBooks();
					System.out.println("Informe creado en ./Biblioteca/Informes \n");
				} catch (FileNotFoundException e) {
					System.out.println(e.toString()+"\n");
					Ops.getStock().delete();
				} catch (ClassNotFoundException e) {
				} catch (IOException e) {
				}
				break;
			case 2:
				try {
					System.out.print("Introduce fecha inicial: ");
					String fechaInicial = Teclado.leerCadena();
					System.out.print("Introduce fecha final: ");
					String fechaFinal = Teclado.leerCadena();
					Ops.genInfoLoans(fechaInicial, fechaFinal);
					System.out.println("Informe creado en ./Biblioteca/Informes/Prestamos \n");
				} catch (FileNotFoundException e) {
					System.out.println(e.toString()+"\n");
					for(File f: new File(".\\Biblioteca\\Informes\\Prestamos").listFiles())
						f.delete();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println(e.toString()+"\n");
					for(File f: new File(".\\Biblioteca\\Informes\\Prestamos").listFiles())
						f.delete();
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