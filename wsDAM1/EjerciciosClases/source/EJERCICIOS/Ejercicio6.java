package EJERCICIOS;
import java.util.Scanner;

public class Ejercicio6 {

	public static void main(String[] args) {
		
		Scanner Teclado = new Scanner(System.in);
		
		String persona1;
		String persona2;
		String persona3;
		
		int edad1;
		int edad2;
		int edad3;
		
		
		
		System.out.println("Introduce el nombre de la primera persona: ");
		persona1 = Teclado.next();
		
		System.out.print("Edad de " + persona1 + ": ");
		edad1 = Teclado.nextInt();
		
		System.out.println("Introduce el nombre de la segunda persona: ");
		persona2 = Teclado.next();
		
		System.out.print("Edad de " + persona2 + ": ");
		edad2 = Teclado.nextInt();
		
		System.out.println("Introduce el nombre de la tercera persona: ");
		persona3 = Teclado.next();
		
		System.out.print("Edad de " + persona3 + ": ");
		edad3 = Teclado.nextInt();
		
		double edadMedia = (double)(edad1 + edad2 + edad3)/3;
		
		System.out.println("La edad media de " + persona1 + ", " + persona2 + " y " + persona3 + " es: " + edadMedia );
		
		
		

	}

}
