package EJERCICIOS;
import java.util.Scanner;

public class ejercicio13 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introducir un numero entero: ");
		int x=teclado.nextInt();
		System.out.println("Introducir otro numero entero: ");
		int y=teclado.nextInt();
		
		int res=x>y?x:y;
		System.out.println("El mayor es: "+res);
		
		String res2=x>y?"Mayor o igual":"Menor o igual";
		
		System.out.println(x+ " es "+res2+" que "+y);
		
		
		
	}

}
