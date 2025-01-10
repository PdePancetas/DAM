package gestionPalabra;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SalidaPalabra implements Runnable {

	public String palabra;
	Socket s;

	@Override
	public void run() {

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			long actual = System.nanoTime();
			while (true) {
				while (System.nanoTime() - actual < 5000000000l) {
				}
				
				bw.write("S: " + palabra);
				bw.newLine();
				bw.flush();
				
				actual = System.nanoTime();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public SalidaPalabra(Socket s) {
		super();
		this.s = s;
	}

}
