package Ejer4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAnillo implements Runnable {

	Socket socket;
	Mensaje m;

	public ClienteAnillo(Socket s, Mensaje m) {
		this.socket = s;
		this.m = m;
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		Socket sc = new Socket();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = null;
			synchronized (m) {
				while (m.getPuerto() == -1) {
					try {
						System.out.println("ANILLO:puerto -1 espero...");
						m.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			while (true) {
				System.out.println("ANILLO:while");
				String leido = "";

				System.out.println("ANILLO:ME paro a leer");
				try {
				leido = IOUtility.leer(br);
				}catch(Exception e) {
				
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					leido = IOUtility.leer(br);
					
				}
				
				leido = "" + (Integer.parseInt(leido) + 1);

				System.out.println("ANILLO : espero ");
				Thread.sleep(10);
				System.out.println("ANILLO : sigo");

				if (m.isChange()) {
					System.out.println("ANILLO : puerto destino cambiado");
					m.setChange(false);

					if (m.primero) {
						m.primero = false;
						sc=m.s;
						bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));

					} else {
						InetSocketAddress isa = new InetSocketAddress("localhost", m.getPuerto());
						try {
							System.out.println("ANILLO : Creo nuevo socket hacia next nodo "+m.getPuerto());
							sc = new Socket();
							sc.connect(isa);
							bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
						} catch (Exception e) {
							System.out.println("ERROR");
						}
					}
				}
				System.out.println("ANILLO : envio mensaje");
				IOUtility.escribir(bw, leido);
				System.out.println("ANILLO : enviado");
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
