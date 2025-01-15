package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

	private static HashMap<Integer, ArrayList<Socket>> grupos = new HashMap<>();
	
	public static void main(String[] args) {
		try {
			try (ServerSocket ss = new ServerSocket(3333)) {
				System.out.println("S: escuchando...");
				while (true) {
					Socket cliente = ss.accept();
					System.out.println("Conexión establecida, esperando nueva conexión...");
					Thread worker = new Thread(new ServerWorker(cliente, grupos));
					worker.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
