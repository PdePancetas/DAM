package Ejemplo_Server_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("Creando Socket Servidor");
			ServerSocket ss = new ServerSocket();
			System.out.println("Bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 3333);
			ss.bind(addr);
			while (true) {
				System.out.println("accept");
				Socket nsc = ss.accept();
				System.out.println("conexión recibida");
				InputStream is = nsc.getInputStream();
				OutputStream os = nsc.getOutputStream();
				byte[] mensaje = new byte[25];
				is.read(mensaje);
				System.out.println("recibido:" + new String(mensaje));
				System.out.println("Esperando nueva conexion...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}