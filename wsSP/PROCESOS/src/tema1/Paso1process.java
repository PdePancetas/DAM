package tema1;

public class Paso1process {

	public static void main(String[] args) {

//		System.out.print("Numero 1: ");
		int a = 3;
//		System.out.print("Numero 2: ");
		int b = 20;

		int suma = 0;
		for (int i = a; i <= b; i++) {
			suma += i;
		}
		System.out.println("El resultado de sumar de " + a + " a " + b + " es: " + suma);
		
	}

}
