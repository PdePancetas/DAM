package gestionChat;


import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import utilidadesTeclado.Teclado;

public class EntradaPuntos implements Runnable {

	String palabra;
	Socket s;
	BufferedWriter bw;

	@Override
	public void run() {
		try {
			while (true) {
				pidePalabra();
				mandaPalabra(bw);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public EntradaPuntos() {

	}

	public EntradaPuntos(Socket s, BufferedWriter bw) {
		this.s = s;
		this.bw = bw;
	}

	private void pidePalabra() {
		palabra = Teclado.leerCadena();
	}

	private void mandaPalabra(BufferedWriter br) throws IOException {
		br.write(palabra);
		br.newLine();
		br.flush();

	}

}
