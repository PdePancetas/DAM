package Ejemplo_DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Random;

public class Client1 {

	static int puntuacion = 0;

	public static void main(String[] args) {
		while (puntuacion > 5) {
			try {

				DatagramSocket ds = new DatagramSocket();

				String mensaje = "ping";
				InetAddress addr = InetAddress.getByName("localhost");
				DatagramPacket dp1 = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, addr, 8888);
				ds.send(dp1);
				ds.close();
				InetSocketAddress addr2 = new InetSocketAddress("localhost", 8889);
				ds = new DatagramSocket(addr2);
				byte[] msg = new byte[100];
				DatagramPacket dpRecibo = new DatagramPacket(msg, 100);
				ds.receive(dpRecibo);

				Random r = new Random();
				if (r.nextInt(1, 10) != 10) {
					System.out.println("Recibido: " + new String(msg));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}