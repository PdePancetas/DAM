package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import gestionPalabra.PidePalabra;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket s = new Socket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 3333);
			
			s.connect(isa);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			System.out.println("S: conexión recibida");
			
			PidePalabra p = new PidePalabra();
			new Thread(p).start();
			
			while (true) {
				System.out.println("S-Palabra: " +br.readLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
