package Ejemplo_StreamSocket_SolucionThreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GestorPeticion implements Runnable {

	private Socket nsc;

	public GestorPeticion(Socket nsc) {
		super();
		this.nsc = nsc;
	}

	@Override
	public void run() {
		try {
			System.out.println("S: conexión recibida");
			BufferedReader br = new BufferedReader(new InputStreamReader(nsc.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(nsc.getOutputStream()));
			String data;
			
				data = br.readLine();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("S: recibido: " + new String(data));
			bw.write("S: Adioss");
			bw.newLine();
			bw.flush();
			System.out.println("S: Esperando nueva conexion...");

			nsc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
