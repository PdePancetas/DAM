package Cuenta_Bancaria;

import utilidadesTeclado.Teclado;

public class Launcher {

	public static void main(String[] args) {

		CuentaBancaria cuenta = new CuentaBancaria(500);
		System.out.println("Cuantas personas van a operar? ");
		int numPers = Teclado.leerEntero();
		for (int i = 0; i < numPers; i++) {
			Thread persona = new Thread(new Persona(cuenta));
			persona.start();
		}
	}
}
