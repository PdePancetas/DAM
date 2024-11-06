package EJERCICIOS;

import java.util.Scanner;

public class Ejercicio_Bucles_11 {

	public static void main(String[] args) {
		
		//Programa que dado un número entero, muestre en líneas consecutivas cada una de sus cifras.
		
		Scanner t= new Scanner(System.in);
		
		System.out.println("Introduce un numero entero: ");
		
		String num=t.next();
		System.out.println("Numero de digitos: "+ num.length());
		
		
		int a=0;
		
		
		while(a<num.length()||a!=num.length()) {
			System.out.println(num.charAt(a));
			a++;
			
		
		}

	}

}
