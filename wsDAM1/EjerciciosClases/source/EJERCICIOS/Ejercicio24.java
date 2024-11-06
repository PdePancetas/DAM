package EJERCICIOS;
import java.util.Scanner;

public class Ejercicio24 {

	public static void main(String[] args) {
		
		/*Crea un programa que lea por teclado dos variables enteras para representar las edades de dos
		personas. Muestra en pantalla un mensaje que indique “Pueden acceder” o no. El acceso es
		permitido a personas de mayor de 18 o a personas mayores de 21 que acompañen a un menor
		de edad.*/
		
		
		Scanner tecla= new Scanner(System.in);
		
		
		System.out.println( "El acceso esta permitido a personas de mayor de 18 o a personas mayores de 21 que acompañen a un menor de edad.");
		
		System.out.println("Escribe la edad de la primera persona: ");
		int edad1= tecla.nextInt();
		
		
		boolean edad = edad1<18;
		
		String acceso1 = (edad)?"No puede acceder a menos que venga con un mayor de 21 años.":"Adelante, puede acceder.";
		
		
		System.out.println(acceso1);
		
		System.out.println("Escribe la edad de la segunda persona: ");
		int edad2= tecla.nextInt();
		
		edad = edad2<18;
		
		String acceso2 = (edad)?"No puede acceder a menos que venga con un mayor de 21 años.":"Adelante, puede acceder.";		
		
		System.out.println(acceso2);
		
		tecla.close();
		
	}

}
