package ExamenNetwork2425;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private static final int PUERTO = 7777;
	
	public static void main(String[] args) throws IOException {
		
		try(ServerSocket ss = new ServerSocket(PUERTO)){
			System.out.println("Servidor conectado y a la escucha en el puerto "+ss.getLocalPort()+" ...");
			while(true) {
				Socket socket = ss.accept();
				System.out.println("Cliente conectado desde "+socket.getInetAddress());
				Thread gestor = new Thread(new GestorCliente(socket));
				gestor.start();
			}
			
			
		}		
	}
}
