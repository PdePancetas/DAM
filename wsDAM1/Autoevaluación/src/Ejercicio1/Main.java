package Ejercicio1;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Clase1: Crea una clase con un método main. En el método main se crea una
		 * variable de tipo int y una de tipo double. Se suman las variables. Se imprime
		 * en pantalla el resultado
		 */
		System.out.print("Introduce entero: ");
		int a = Teclado.leerEntero();

		System.out.print("Introduce decimal: ");
		double b = Teclado.leerDecimal();

		System.out.println(a * b);

	}

}
