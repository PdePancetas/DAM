package Ejercicio1;

import java.util.LinkedList;
import java.util.List;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		List<Integer> numeros = new LinkedList<Integer>();

		System.out.println("Introduce numeros: ");
		String num;

		while (esNumero(num = Teclado.leerCadena()))
			numeros.add(Integer.parseInt(num));

		numeros.sort(new OrdenNum());
		System.out.println(numeros.toString());

		System.out.print("Introduce un número: ");
		int divisor = Teclado.leerEntero();

		borrarDiv(numeros, divisor);

		System.out.println(numeros.toString());

	}

	private static void borrarDiv(List<Integer> numeros, int divisor) {
		List<Integer> divisores = new LinkedList<>();
		for (int i = 1; i <= divisor; i++)
			if (divisor % i == 0)
				divisores.add(i);

		numeros.removeAll(divisores);
		
		divisores = null; //	Para que esta lista ya inservible sea borrada por el recolector de basura

	}

	private static boolean esNumero(String num) {

		for (int i = 0; i < num.length(); i++)
			if (num.charAt(i) < 48 || num.charAt(i) > 57)
				return false;

		return true;

	}

}
