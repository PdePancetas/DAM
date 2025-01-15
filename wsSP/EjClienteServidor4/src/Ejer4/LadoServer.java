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

public class LadoServer {
	static Socket[] sockets = new Socket[10];
	static int[] socketsPort = new int[10];
	static int clients = 0;

	public static void main(String[] args) {
		try {
			// creo socket
			ServerSocket ss = new ServerSocket();
			// bind
			InetSocketAddress addr = new InetSocketAddress("localhost", 55555);
			ss.bind(addr);
			System.out.println("Server: Creado y bindado el socket, voy a esperar conexiones");
			while (true) {
				Socket s = ss.accept();
				
				sockets[clients] = s;
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				socketsPort[clients] = Integer.parseInt(IOUtility.leer(br));
				clients++;
				if (clients == 3) {
					for (int i = 0; i < clients; i++) {
						if (i == 0) {

							Socket socket = sockets[i];
							BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
							IOUtility.escribir(bw, "" + (socketsPort[(i + 1) % clients] * (-1)));
						} else {
							Socket socket = sockets[i];
							BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
							IOUtility.escribir(bw, "" + socketsPort[(i + 1) % clients]);
						}
					}
				}

				if (clients > 3) {
					Socket socket = sockets[clients-2];
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					IOUtility.escribir(bw, "" + (socketsPort[clients-1]));
					
					Socket socket2 = sockets[clients-1];
					BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
					IOUtility.escribir(bw2, "" + (socketsPort[0]));
					
					
					
					
				}

			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
