package Ejemplo_Server_Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("S: Creando Socket Servidor");
			ServerSocket ss = new ServerSocket();
			System.out.println("S: Bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 3333);
			ss.bind(addr);
			while (true) {
				System.out.println("S: accept");
				Socket nsc = ss.accept();
				System.out.println("S: conexión recibida");
				BufferedReader br = new BufferedReader(new InputStreamReader(nsc.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(nsc.getOutputStream()));
				String data = br.readLine();
				System.out.println("S: recibido: " + new String(data));
				bw.write("S: Adioss \n");
				bw.flush();
				System.out.println("S: Esperando nueva conexion...");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}