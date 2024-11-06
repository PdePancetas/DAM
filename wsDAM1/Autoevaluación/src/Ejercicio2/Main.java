package Ejercicio2;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		/*
		 * Clase2: Crea una clase con un método main. El método main crea una variable
		 * de tipo String. Si esta variable contiene el valor “Gato” entonces escribe
		 * “es un felino” si no “no es un felino”.
		 */
		System.out.print("Introduce cadena: ");
		String cad = Teclado.leerCadena();

		if (cad.equals("Gato") || cad.equals("gato"))
			System.out.println("es un felino");
		else
			System.out.println("no es un felino");
	}

}
