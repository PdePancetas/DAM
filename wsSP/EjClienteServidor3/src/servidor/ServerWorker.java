package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gestionPalabra.SalidaPalabra;

public class ServerWorker implements Runnable {
	Socket s;
	BufferedReader br;

	public ServerWorker(Socket nsc) {

		this.s = nsc;
		try {
			br = new BufferedReader(new InputStreamReader(nsc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		
		SalidaPalabra salida = new SalidaPalabra(s);
		new Thread(salida).start();
		
		while(true) {
			actualizarPalabra(br, salida);
		}
	}

	private void actualizarPalabra(BufferedReader br, SalidaPalabra salida) {
		try {
			salida.palabra = br.readLine();
			System.out.println(salida.palabra);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
