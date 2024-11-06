package Ejercicio7;

import java.util.Arrays;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {
		
		/*Clase7: Crea una clase con un método main que pide 5 números positivos al usuario. 
		 * Los guarda en un array. Escribe el contenido del array varias veces. 
		 * Cada vez que lo escribe resta 1 a todas las casillas. 
		 * Cuando todas las casillas llegan a cero, se para.
		 */
		
		
		System.out.println("Introduce 5 numeros positivos: ");
		boolean completo = false;
		int x = 1;
		int[] numeros = new int[5];

		while (x > 0 && !completo) {
			for (int i = 0; i < 5; i++) {
				System.out.print("Numero " + i + ": ");
				x = Teclado.leerEntero();

				if (x > 0)
					numeros[i] = x;
				else {
					System.out.println("Numero no valido");
					i = i - 1;
				}
			}

			completo = true;
		}
		
		boolean casillasSonCero=false;
		while(!casillasSonCero){
			System.out.println(Arrays.toString(numeros));
			for(int i=0;i<numeros.length;i++) {
				if(numeros[i]!=0)
					numeros[i]=numeros[i]-1;
				
			}
			int cont=0;
			for(int i=0;i<numeros.length&&cont!=numeros.length;i++) {
				if(numeros[i]==0)
					cont++;
			}
			if(cont==numeros.length) {
				System.out.println(Arrays.toString(numeros));				
				casillasSonCero=true;
			}
		}
		
		
		
	}

}
