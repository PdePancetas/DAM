package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashSet;

import gestionChat.SalidaPuntos;

public class ServerWorker implements Runnable {
	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	HashSet<Socket> clientes = new HashSet<>();

	public ServerWorker(Socket cliente, HashSet<Socket> clientes) {

		this.s = cliente;
		this.clientes = clientes;
		try {
			br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {

			SalidaPuntos salida = new SalidaPuntos();
			new Thread(salida).start();
			try {
				while (true)
					actualizarPuntos(br, salida);

			} catch (IOException e) {
				System.out.println(s.getInetAddress() + " ha cerrado la conexión"); //OBTENER NOMBRE
			}
		} catch (Exception e) {
			
		}
	}

	private void actualizarPuntos(BufferedReader br, SalidaPuntos salida) throws IOException {

		salida.mensaje = br.readLine();
		synchronized (s) {
			s.notifyAll();
		}

	}
}