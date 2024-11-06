package EJERCICIOS;

import java.util.Scanner;

public class Ejercicio8_SentenciasSelectivas {

	public static void main(String[] args) {
		
		//Realizar un programa que lea un número entero de 3 cifras,
		//y forme el mayor número posible con las cifras del número ingresado.
		//El número formado debe tener el mismo signo que el número ingresado.
		
		 Scanner t = new Scanner(System.in);
	        System.out.print("Ingresa un número entero de 3 cifras: ");
	        int n = t.nextInt();
	        
	        if (n>=100&&n<=999) {
	        	
	            int signo = (n<0) ? -1 : 1;

	            int n1 =  n/100;
	            int n2 = (n/10)%10;
	            int n3 =  n%10;

	            int primero, segundo, tercero;

	            if (n1>=n2&&n1>=n3) {
	                primero = n1;
	                segundo = n2>=n3?n2:n3;
	                tercero = n2>=n3 ?n3:n2;
	            } else if (n2>=n1&&n2>=n3) {
	                primero = n2;
	                segundo = n1>=n3?n1:n3;
	                tercero = n1>=n3?n3:n1;
	            } else {
	                primero = n3;
	                segundo = n1>=n2?n1:n2;
	                tercero = n1>=n2?n2:n1;
	            }

	            // Construir el número resultante
	            int numeroResultante = (primero* 100 + segundo * 10 + tercero) * signo;

	            System.out.print("El mayor número posible con estas cifras es: " + numeroResultante);
	        } else {
	            System.out.print("El número no tiene 3 cifras.");
	        }

	       

	}

}
