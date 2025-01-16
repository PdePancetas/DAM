package gestionChat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import io.IOUtility;

public class SalidaPalabra implements Runnable {

	public String mensaje;
	private int grupo;
	private HashMap<Integer, ArrayList<Socket>> grupos;
	private Socket s;

	@Override
	public void run() {

		BufferedWriter bw;
		try {

			while (true) {
				synchronized (s) {
					s.wait();
				}
				long pid = 0;
				for (Socket socket : grupos.get(grupo)) {
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//					if(pid== socket.)
					IOUtility.escribir(bw, mensaje);

				}

			}
		} catch (IOException e) {
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SalidaPalabra(Socket s, int grupo, HashMap<Integer, ArrayList<Socket>> grupos) {
		this.grupo = grupo;
		this.grupos = grupos;
		this.s = s;
	}

}
