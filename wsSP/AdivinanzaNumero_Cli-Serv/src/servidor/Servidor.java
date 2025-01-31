package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
	private static final int PUERTO = 12345;
	static Map<Integer, List<ManejadorCliente>> grupos = new HashMap<>();
	
	static int numConexiones = 0;
	static int numGrupo = 1;
	
	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado en el puerto " + PUERTO);

		while (true) {
			try {
				Socket clienteSocket = servidor.accept();
				numConexiones++;

				System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());

				Thread manejadorThread = new Thread(new ManejadorCliente(clienteSocket, numGrupo));
				manejadorThread.start();
				
				if (numConexiones == 2) {
					numGrupo++;
					
					numConexiones = 0;
					
					
				}
			} catch (IOException e) {
				System.err.println("Error al aceptar la conexión: " + e.getMessage());
			}
		}
	}
}
