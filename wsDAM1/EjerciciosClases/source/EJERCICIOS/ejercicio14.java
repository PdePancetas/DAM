package EJERCICIOS;
import java.util.Scanner;

public class ejercicio14 {

	public static void main(String[] args) {
		
		/*Escribe un programa que lea por teclado una variable de tipo entero y diga si ese número es par
		o impar.*/
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce un numero:");
		int n1=teclado.nextInt();
		
		String res1= n1%2==0?"Par":"impar";
		System.out.println(n1+" es " + res1);
		
		String res2= n1/2==n1/2.0 ?" es par":" es impar";
		System.out.println(n1+res2);
	}

}
