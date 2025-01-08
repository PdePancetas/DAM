package gestionPalabra;

import utilidadesTeclado.Teclado;

public class PidePalabra implements Runnable {

	String palabra;

	@Override
	public void run() {

		while (true) {
			pidePalabra();
			
		}

	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public PidePalabra(String palabra) {
		this.palabra = palabra;
	}

	public PidePalabra() {

	}

	private void pidePalabra() {
		palabra = Teclado.leerCadena();

	}

}
