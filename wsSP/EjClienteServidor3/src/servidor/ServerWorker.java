package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ServerWorker implements Runnable {
	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	

	public ServerWorker(Socket nsc) {

		this.s = nsc;
		try {
			br = new BufferedReader(new InputStreamReader(nsc.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(nsc.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			long actual = System.nanoTime();
			while (true) {
				while (System.nanoTime() - actual < 2000000000l) {
				}
				
					bw.write("palabra: " );
					bw.newLine();
					bw.flush();
				
				actual = System.nanoTime();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
