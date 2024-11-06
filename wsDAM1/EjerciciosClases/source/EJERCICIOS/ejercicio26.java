package EJERCICIOS;
import java.util.Scanner;

public class ejercicio26 {
	
	public static void main(String[] args) {
		
		/*Escribe un programa que lea de teclado tres variables de tipo double para representar las
		longitudes de los lados de un triángulo. Muestra en pantalla si el triángulo es equilátero,
		isósceles o escaleno*/
		
		
		Scanner teclado=new Scanner(System.in);

		System.out.print("Introduce el valor del primer lado: ");
		double lado1=teclado.nextDouble();
		
		System.out.print("Introduce el valor del segundo lado: ");
		double lado2=teclado.nextDouble();
		
		System.out.print("Introduce el valor del tercer lado: ");
		double lado3=teclado.nextDouble();
		
		
		
		String tipoTri = (lado1==lado2)&&(lado2==lado3)?"El triángulo es equilatero."
				:((lado1==lado2)&&(lado2!=lado3)?"El triangulo es isosceles."
				:((lado1!=lado2)&&(lado2==lado3)?"El triángulo es isosceles."
				:(lado1==lado3)&&(lado1!=lado2)?"El triangulo es isosceles"
				:"El triángulo es escaleno."));
		
		
		
		System.out.println(tipoTri);
		
		

	}

}
