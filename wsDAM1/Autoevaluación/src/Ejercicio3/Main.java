package Ejercicio3;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Clase3: Crea una clase con un método main. El método main crea dos variables,
		 * una int y otra String. Escribe en pantalla el contenido del String tantas
		 * veces como se indique en el int.
		 */

		System.out.print("Introduce entero: ");
		int x = Teclado.leerEntero();

		System.out.print("Introduce cadena: ");
		String cad = Teclado.leerCadena();

		System.out.println("Se escribe en pantalla " + "'" + cad + "' " + x + " veces");
		for (int i = 0; i < x; i++)
			System.out.println(cad);

	}

}
