package Ejercicio5;

import java.util.Arrays;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Clase5: Crea una clase con un método main que pide 5 números positivos al
		 * usuario. Los guarda en un array. Escribe el contenido del array al revés.
		 */

		System.out.println("Introduce 5 números positivos: ");
		int x = 1;
		int[] numeros = new int[5];

		boolean completo = false;
		while (x > 0 && !completo) {
			for (int i = 0; i < 5; i++) {
				System.out.print("Numero " + i + ": ");
				x = Teclado.leerEntero();
				if (x > 0)
					numeros[i] = x;
				else {
					System.out.println("Numero no valido");
					i = i - 1;
				}
			}

			completo = true;
		}

		System.out.println("Array Numeros: " + Arrays.toString(numeros));

		int[] numrev = new int[numeros.length];

		for (int i = 0; i < numeros.length; i++) {
			numrev[numeros.length - i - 1] = numeros[i];
			;
		}

		System.out.println("Array numeros invertido: " + Arrays.toString(numrev));
	}

}
