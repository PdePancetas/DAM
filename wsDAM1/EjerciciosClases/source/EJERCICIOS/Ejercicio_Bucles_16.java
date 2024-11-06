package EJERCICIOS;
import java.util.Scanner;

public class Ejercicio_Bucles_16 {

	public static void main(String[] args) {
		
		/*Programar un juego de adivinanza.
		  El programa pedirá al usuario dos números, max y min, y un número de intentos, n.
		  El programa obtendrá a continuación un número aleatorio entre max y min, 
		  y el usuario deberá adivinarlo utilizando como mucho n intentos. 
		  Cada vez que el usuario introduce un número, 
		  el programa le dice si es mayor o menor.
		  Al final el programa indica si se ha ganado o no. 
		  (para generar un número aleatorio se puede utilizar la clase Random).*/
		
		Scanner t=new Scanner(System.in);
		
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE,intentos;
		System.out.println("Introduce un numero maximo, un numero minimo y un numero de intentos: ");
		System.out.print("Intentos: ");
		intentos=t.nextInt();
		System.out.print("Minimo: ");
		min=t.nextInt();
		System.out.print("Maximo: ");
		max=t.nextInt();
	
		int random= (int)(Math.random()* (max - min +1) + min);
		int num=0;
		boolean esElNumero= false;
		for (int i=1;i<=intentos;i++) {
			
			System.out.print("Intento "+i+": " );
			num=t.nextInt();
			
			if(num>random)
				System.out.println("Es menor");
			else if (num<random)
				System.out.println("Es mayor");
			else {
				esElNumero=true;
				i=intentos;
			}
			
			
		}
		
		if (esElNumero)
			System.out.println("Bien, lo adivinaste!");
		else 
			System.out.println("La proxima vez sera...");
		
		
		
		
		
		
		
	}

}