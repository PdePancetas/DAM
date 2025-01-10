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
		
		try {
		while(true)
				actualizarPalabra(br, salida);
			
		} catch (IOException e) {
			System.out.println(s.getPort()+" ha cerrado la conexión");
		}
	}

	private void actualizarPalabra(BufferedReader br, SalidaPalabra salida) throws IOException {
		
			salida.palabra = br.readLine();
			System.out.println("Nueva palabra de "+s.getLocalPort()+": "+salida.palabra);
		
			
			
		
	}

}
