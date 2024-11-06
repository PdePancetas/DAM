package EJERCICIOS;

import java.util.Scanner;

public class Ejercicio5 {

	public static void main(String[] args) {
		
		Scanner Teclado = new Scanner(System.in);
		
		int BaseTriangulo;
		int AlturaTriangulo;
		
		System.out.print("Introduce la base del triangulo: ");
		BaseTriangulo = Teclado.nextInt();
		
		System.out.print("Introduce la altura del triangulo: ");
		AlturaTriangulo = Teclado.nextInt();
		
		double Resultado = (double)(BaseTriangulo*AlturaTriangulo)/2;
		
		System.out.println("El área de el triangulo por esta base y altura es: " + Resultado + " unidades cuadradas.");
	}

}
