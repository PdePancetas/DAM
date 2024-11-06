package Ejercicio1;

import java.util.Random;
import java.util.Scanner;

public class Ruleta {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("¿Casillas de la ruleta?");
		int numCasillas = scanner.nextInt();
		while (numCasillas < 16 || numCasillas > 36 || numCasillas % 4 != 0) {
			System.out.println("Error!! Debe ser múltiplo de 4 y estar entre 16 y 36. Introduzca el número de nuevo");
			numCasillas = scanner.nextInt();
		}
		System.out.println("¿Dinero inicial del jugador?");
		int dineroJ1 = scanner.nextInt();
		while (dineroJ1 < 1000) {
			System.out.println("Debe ser mayor de 1000. Ingresa de nuevo más dinero.");
			dineroJ1 = scanner.nextInt();
		}
		int dineroRuleta = 100 * dineroJ1;
		int limite = 50 * dineroJ1;
		while (dineroJ1 > 0 && dineroJ1 < limite) {
			System.out.println("¿Cuanto desea apostar?");
			int apuestaJ1 = scanner.nextInt();
			while (apuestaJ1 <= 0 || apuestaJ1 > dineroJ1) {
				System.out.println("Cantidad no válida. ¿Cuanto desea apostar?");
				apuestaJ1 = scanner.nextInt();
			}

			System.out.println("¿A qué número desea apostar?");
			int numeroJ1 = scanner.nextInt();
			while (numeroJ1 < 0 || numeroJ1 > numCasillas) {
				System.out.println("Número no válido. ¿A qué número desea apostar?");
				numeroJ1 = scanner.nextInt();
			}
			Random random = new Random();
			int numRuleta = random.nextInt(numCasillas);
			int gananciaJ1 = 0;
			if (numeroJ1 == numRuleta) {
				gananciaJ1 = 16 * apuestaJ1;
				System.out.println("¡¡¡¡Has acertado el número de la Ruleta!!!!!");
			} else if (numRuleta != 0 && numeroJ1 != 0 && numeroJ1 % 2 == numRuleta % 2) {
				System.out
						.println("Has acertado el color del número de la ruleta. Tu ganancia es 1.8 veces lo apostado");
				gananciaJ1 = (int) (1.8 * apuestaJ1);

			} else {
//gananciaJ1=gananciaJ1-apuestaJ1;
				gananciaJ1 -= apuestaJ1;
				System.out.println("¡Sin Premio!");

			}
			System.out.println("el número de la ruleta fue " + numRuleta);
			System.out.println("Jugador1 apostó " + apuestaJ1);
			System.out.println("Ganancia del jugador " + gananciaJ1);
			dineroRuleta = dineroRuleta - gananciaJ1;
			dineroJ1 = dineroJ1 + gananciaJ1;
			System.out.println("Dinero de la ruleta " + dineroRuleta);
			System.out.println("Dinero del jugador1: " + dineroJ1);
			System.out.println("Límite de dinero para ganar hoy en el casino" + limite);
			if (dineroJ1 > 0) {
				System.out.println("El jugador es el ganador del día" + dineroJ1);
			} else {
				System.out.println("El Casino ha ganado. El jugador se quedó sin dinero");
			}

		}

	}
}
