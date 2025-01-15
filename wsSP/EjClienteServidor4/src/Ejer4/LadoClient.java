package Ejer4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LadoClient {

	public static void main(String[] args) {
		Mensaje m=new Mensaje(-1);
		Scanner scan=new Scanner(System.in);
		System.out.println("inserta un puerto");
		int puerto=scan.nextInt();
		Socket sc= new Socket();
		InetSocketAddress isa=new InetSocketAddress("localhost",55555);
		try {
			
			sc.connect(isa);
			
			
			Thread cp= new Thread(new ClienteManager(sc,m,puerto));
			cp.start();
			
			try {
				// creo socket
				ServerSocket ss = new ServerSocket();
				// bind
				InetSocketAddress addr = new InetSocketAddress("localhost", puerto);
				ss.bind(addr);
				System.out.println("CLIENTE: Creado y bindado el socket, voy a esperar conexiones");
				Thread t=null;
				ClienteAnillo ca=null;
				Socket old=null;
				while (true) {
					Socket s = ss.accept();
					System.out.println("CLIENTE: creo nuevo Anillo");
					if(t==null) {
						System.out.println("CLIENTE: t NULL");
						ca=new ClienteAnillo(s,m);
					t=new Thread(ca);
					t.start();
					}else
					{
						Socket x=ca.socket;
						ca.socket=s;
						System.out.println("CIERRO SOCKET");
						x.close();
						//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ca.socket.getOutputStream()));
						//IOUtility.escribir(bw, "switch");
						
					}
				
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			

		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
