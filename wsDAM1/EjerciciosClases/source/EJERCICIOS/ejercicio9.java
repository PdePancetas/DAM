package EJERCICIOS;
import java.util.Scanner;

public class ejercicio9 {
	
	public static void main(String[] args) {
		
		Scanner teclado=new Scanner(System.in);
		
		System.out.print("Introduce los grados en Celsius: ");
		int n1=teclado.nextInt();
		
		double farenheit  = ((double)n1*9/5)+32;
		
		System.out.println("El resultado en Farenheit es: " + farenheit);
		
		
		
	}
}
