package Ejercicio4;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Clase4: Crea una clase con un método main. Pide un número al usuario. Si el
		 * número es menor o igual a 0 escribe 10 veces “hola”, si no escribe 3 veces
		 * “adios”.
		 */

		System.out.println("Introduce un numero: ");
		double x = Teclado.leerDecimal();

		if (x <= 0)
			for (int i = 0; i < 10; i++)
				System.out.println("Hola");
		else
			for (int i = 0; i < 3; i++)
				System.out.println("adios");

	}

}
