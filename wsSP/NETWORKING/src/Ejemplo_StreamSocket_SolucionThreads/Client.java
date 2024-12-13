package Ejemplo_StreamSocket_SolucionThreads;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket s = new Socket();
		InetSocketAddress isa = new InetSocketAddress("localhost", 3333);
		try {
			s.connect(isa);
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(s.getOutputStream()));
			BufferedReader br = new BufferedReader( new InputStreamReader(s.getInputStream()));
			bw.write("C: Hooola. desde el puerto "+s.getLocalPort());
			bw.newLine();
			bw.flush();
			String data = br.readLine();
			System.out.println("C: recibido: " + new String(data));
			
			br.close();
			bw.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}