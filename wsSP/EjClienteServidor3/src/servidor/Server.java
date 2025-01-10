package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			System.out.println("S: Creando Socket Servidor");
			try (ServerSocket ss = new ServerSocket()) {
				System.out.println("S: Bind");
				InetSocketAddress addr = new InetSocketAddress("localhost", 3333);
				ss.bind(addr);
				while (true) {
					Socket nsc = ss.accept();
					System.out.println("Conexión establecida, esperando nueva conexión...");
					Thread worker = new Thread(new ServerWorker(nsc));
					worker.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
