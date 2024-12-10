package Ejemplo_Server_Client;
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
			BufferedWriter osw = new BufferedWriter( new OutputStreamWriter(s.getOutputStream()));
			BufferedReader isr = new BufferedReader( new InputStreamReader(s.getInputStream()));
			osw.write("C: Hooola. desde el puerto "+isa.getPort()+"\n");
			osw.flush();
			String data = isr.readLine();
			System.out.println("C: recibido: " + new String(data));
			
			isr.close();
			osw.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}