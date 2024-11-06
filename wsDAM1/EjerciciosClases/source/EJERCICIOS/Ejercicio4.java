package EJERCICIOS;

import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {
		
		Scanner Teclado = new Scanner(System.in);
		
		double ladoCuadrado;
		
		System.out.print("Introduce el lado del cuadrado: ");
		ladoCuadrado = Teclado.nextDouble();
		
		System.out.print("El área total del cuadrado es: " + (ladoCuadrado*ladoCuadrado) + " unidades cuadradas.");
	}

}
