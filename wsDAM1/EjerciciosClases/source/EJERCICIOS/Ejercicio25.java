package EJERCICIOS;
import java.util.Scanner;

public class Ejercicio25 {	
	
	public static void main(String[] args) {
		/*Escribe un programa que lea por teclado dos variables de tipo double para representar las notas
		de un estudiante en dos evaluaciones. Muestra en pantalla el mensaje “Muy bien” si el alumno
		ha aprobado las dos asignatura y la suma de las dos notas es al menos 14.*/
		
		Scanner teclado= new Scanner(System.in);
		
		System.out.println("Introduce dos numeros correspondientes a notas de evaluaciones (0-10): ");
		double n1=teclado.nextDouble();
		double n2=teclado.nextDouble();
		
		String res= (n1>=5)&&(n2>=5)?(n1+n2>=14)?"Muy bien.":"No esta mal.":"Hace falta mejorar.";
		
		System.out.println(res);
		
		teclado.close();
	}

}
