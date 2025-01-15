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
				for (Socket socket : grupos.get(grupo)) {
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					IOUtility.escribir(bw, mensaje);
					
				}
				
			
				
			}
		} catch (IOException e) {
		}

	}

	public SalidaPalabra(Socket s, int grupo, HashMap<Integer, ArrayList<Socket>> grupos) {
		this.grupo = grupo;
		this.grupos = grupos;
		this.s = s;
	}

}
