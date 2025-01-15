package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import gestionChat.SalidaPalabra;

public class ServerWorker implements Runnable {
	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	HashMap<Integer, ArrayList<Socket>> grupos;

	public ServerWorker(Socket cliente, HashMap<Integer, ArrayList<Socket>> grupos) {

		this.s = cliente;
		try {
			br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
			this.grupos = grupos;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {

			int grupo = Integer.parseInt(br.readLine());
			if (grupos.containsKey(grupo)) {
				grupos.get(grupo).add(s);
			} else {
				grupos.put(grupo, new ArrayList<Socket>());
				grupos.get(grupo).add(s);
			}

			SalidaPalabra salida = new SalidaPalabra(s, grupo, grupos);
			new Thread(salida).start();
			try {
				while (true)
					actualizarPalabra(br, salida);

			} catch (IOException e) {
				System.out.println(s.getPort() + " ha cerrado la conexión");
			}
		} catch (Exception e) {
			
		}
	}

	private void actualizarPalabra(BufferedReader br, SalidaPalabra salida) throws IOException {

		salida.mensaje = br.readLine();
		System.out.println("Nueva palabra de " + s.getLocalPort() + ": " + salida.mensaje);

	}
}