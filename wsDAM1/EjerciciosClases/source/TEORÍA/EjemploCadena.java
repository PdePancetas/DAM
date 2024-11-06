package TEORÍA;
import java.util.Scanner;

public class EjemploCadena {

	public static void main(String[] args) {
		
		Scanner lectorTeclado = new Scanner(System.in);
				
		String cadena1, cadena2;
		
		cadena1 = "Hola, soy una cadena";
		
		System.out.println(cadena1);
		
		cadena2 = " y yo también";
		
		cadena1 = cadena1 + cadena2;
		
		System.out.println(cadena1);
		
		System.out.println("Introduzca valor entero:");
		int n = lectorTeclado.nextInt();
		
		cadena1 = "El valor leido es: " + n;
		System.out.println(cadena1);

	}

}