package servidor;

import java.io.*;
import java.net.*;
import java.security.KeyPair;
import java.util.*;

import security.ComSegura;

public class Servidor {
	private static final int PUERTO = 12345;

	public static void main(String[] args) throws IOException {
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			System.out.println("Servidor iniciado en el puerto " + PUERTO);
			while (true) {
				try {
					Socket clienteSocket = servidor.accept();
					System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());
//					new Thread(new ManejadorClienteCriptoAsimetrica(clienteSocket)).start();
//					new Thread(new ManejadorClienteCriptoAsimetricaFirmado(clienteSocket)).start();
//					new Thread(new ManejadorClienteCriptoAsimetricaCifrado(clienteSocket)).start();
					new Thread(new ManejadorClienteCriptoAsimetricaHibridoFirmado(clienteSocket, ComSegura.generarClavesRSA())).start();
				} catch (IOException e) {
					System.err.println("Error al aceptar la conexión: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}
