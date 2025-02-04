package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
	private static final int PUERTO = 12345;
	static Map<Integer, List<ManejadorCliente>> grupos = new HashMap<>();
	static ArrayList<Thread> threadsGrupo = new ArrayList<>();
	static int numConexiones = 0;
	static int numGrupo = 1;
	static int numAleatorio;

	public static void main(String[] args) throws IOException {
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			System.out.println("Servidor iniciado en el puerto " + PUERTO);
			Random r = new Random();
			numAleatorio = r.nextInt(1,1001);
			while (true) {
				try {
					Socket clienteSocket = servidor.accept();
					numConexiones++;

					System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());
					ManejadorCliente mc = new ManejadorCliente(clienteSocket, numGrupo, numAleatorio);
					Thread manejadorThread = new Thread(mc);
					threadsGrupo.add(manejadorThread);

					synchronized (Servidor.grupos) {
						Servidor.grupos.computeIfAbsent(numGrupo, _ -> new ArrayList<>()).add(mc);
					}

					if (numConexiones == 2) {
						for (Thread cliente : threadsGrupo)
							cliente.start();
 
						threadsGrupo = new ArrayList<>(); 
						
						System.out.println("2 Clientes conectados en el grupo " + numGrupo + "\nadivinando el numero "
								+ numAleatorio);

						numGrupo++;
						numConexiones = 0;
						numAleatorio = r.nextInt(1,1001);
					}
					
				} catch (IOException e) {
					System.err.println("Error al aceptar la conexión: " + e.getMessage());
				}
			}
		}
	}
}
