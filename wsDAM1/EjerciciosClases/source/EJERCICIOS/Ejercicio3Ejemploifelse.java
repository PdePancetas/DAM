package EJERCICIOS;
import java.util.Scanner;

public class Ejercicio3Ejemploifelse {

	public static void main(String[] args) {
		
		//Programa que solicite y lea 2 numeros enteros
		//Y a continuaci�n muestre el mayor de los 2:
		
		Scanner numero = new Scanner(System.in);
		
		int n1,n2,n3;
		
		System.out.println("Introduzca el primer numero:");
		n1 = numero.nextInt();
		System.out.print("El primer numero es:");
		System.out.println(n1);
				
		System.out.println("Introduzca el segundo numero:");
		n2 = numero.nextInt();
		System.out.print("El segundo numero es:");
		System.out.println(n2);
				
		boolean mayor = n1 > n2;
						
		n3 = (mayor)?n1:n2;
				
		if (n1==n2) {
			System.out.println("Son iguales.");
		}
			else {
				System.out.println("El numero mayor es:");
				System.out.println(n3);
		
		}
		
		numero.close();
	}

}
