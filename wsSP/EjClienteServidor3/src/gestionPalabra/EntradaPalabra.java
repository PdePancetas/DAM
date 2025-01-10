package gestionPalabra;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import utilidadesTeclado.Teclado;

public class EntradaPalabra implements Runnable {

	String palabra;
	Socket s;

	@Override
	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
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

	public EntradaPalabra() {

	}

	public EntradaPalabra(Socket s) {
		this.s = s;
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
