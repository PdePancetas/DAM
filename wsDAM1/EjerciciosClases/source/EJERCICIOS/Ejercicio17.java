package EJERCICIOS;

import java.util.Scanner;

public class Ejercicio17 {

	public static void main(String[] args) {
		
		/*Escribe un programa que lea por teclado tres variables enteras y muestre en pantalla la suma de
dichos números, pero si la suma es mayor que 100, muestra un mensaje indicando que es
demasiado grande*/
		
		
		Scanner teclado=new Scanner(System.in);
		
		System.out.print("Introduce un numero: ");
		int n1=teclado.nextInt();
		
		System.out.print("Introduce un segundo numero: ");
		int n2=teclado.nextInt();
		
		System.out.print("Introduce un tercer numero: ");
		int n3=teclado.nextInt();
		
		int resSuma=n1+n2+n3;
		
		
		String resFinal = ((resSuma)<100)?("La suma de estos 3 numeros es: "+ resSuma):"Es demasiado grande";
		
		System.out.println(resFinal);
		
	}

}
