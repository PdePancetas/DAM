package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import gestionChat.EntradaPalabra;
import io.IOUtility;

public class Cliente{

	public static void main(String[] args) {

		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress("localhost",3333));
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
			Thread t = new Thread(new EntradaPalabra(s,bw));
			t.start();
			
			while(true) {
				IOUtility.leer(br);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
