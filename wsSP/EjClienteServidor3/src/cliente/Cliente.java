package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

import gestionPalabra.EntradaPalabra;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket s = new Socket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 3333);

			s.connect(isa);

			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

			System.out.println("S: conexión recibida");

			EntradaPalabra p = new EntradaPalabra(s);
			new Thread(p).start();

			while (true) {
				System.out.println(br.readLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
