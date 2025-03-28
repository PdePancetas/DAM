package Ejemplo_StreamSocket_SolucionThreads;

import java.io.IOException;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("S: Creando Socket Servidor");
			try (ServerSocket ss = new ServerSocket()) {
				System.out.println("S: Bind");
				InetSocketAddress addr = new InetSocketAddress("localhost", 3333);
				ss.bind(addr);
				while (true) {
					System.out.println("S: accept");
					Socket nsc = ss.accept();
					GestorPeticion gp = new GestorPeticion(nsc);
					new Thread(gp).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}