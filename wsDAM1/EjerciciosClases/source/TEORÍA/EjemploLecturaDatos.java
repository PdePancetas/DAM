package TEORÍA;
import java.util.Scanner;

public class EjemploLecturaDatos {

	public static void main(String[] args) {
		
		Scanner lectorTeclado = new Scanner(System.in);
		int n;
		
		
		//Leer n�mero entero:
		System.out.println("Introduzca número entero:");
		n = lectorTeclado.nextInt();
		System.out.print("El número leído es: ");
		System.out.println(n);
		
		System.out.println("Introduzca numero entero (puede ser long):");
		long n2 = lectorTeclado.nextLong();
		System.out.print("El número leído es: ");
		System.out.println(n2);
		
		lectorTeclado.close();
	}

}
