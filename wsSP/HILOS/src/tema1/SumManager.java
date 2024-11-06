package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import utilidadesTeclado.Teclado;

public class SumManager {
	/*
	 * Crea un programa SumManager, que lanza un proceso SumWorker y luego pide un
	 * numero al usuario. El numero recibido se envía al SumWorker y la salida se
	 * lee en la consola de SumManager. Si el usuario escribe un numero negativo, el
	 * programa termina.
	 */

	public static void main(String[] args) {

		try {
			Process sumWorker = new ProcessBuilder("java", "-cp", ".//bin", "tema1.SumWorker").start();
			PrintStream ps = new PrintStream(sumWorker.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(sumWorker.getInputStream()));

			int num = 0;
			while (num >= 0) {
				System.out.print("Escribe un número: ");
				try {
					num = Teclado.leerEntero();
					if (num >= 0) {
						ps.println(num);
						System.out.println("El hijo devolvió: " + br.readLine());
					} else {
						System.out.println("Se introdujo un número negativo, el programa finaliza");
						sumWorker.destroy();
					}
				} catch (NumberFormatException e) {
					System.err.println("No es un numero valido");
				}
			}

		} catch (IOException e) {
			System.err.println("Error I/O: " + e.getMessage());
		}
	}
}