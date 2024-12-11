package Ejemplo_DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Random;

public class Client2 {

	static int puntuacion = 0;

	public static void main(String[] args) {
		while (puntuacion > 5) {
			try {
				InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
				DatagramSocket ds = new DatagramSocket(addr);

				byte[] msg = new byte[100];

				DatagramPacket dpRecibo = new DatagramPacket(msg, 100);
				ds.receive(dpRecibo);

				System.out.println("Recibido: " + new String(msg));
				String msgenvio = "";
				InetAddress addr2 = InetAddress.getByName("localhost");

				Random r = new Random();
				if (r.nextInt(1, 10) != 10) {
					msgenvio = "pong";
					DatagramPacket dp1 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2,
							8889);
					ds.send(dp1);
				} else {
					msgenvio = "FIN";
					DatagramPacket dp1 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2,
							8889);
					ds.send(dp1);
				}

				ds.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}