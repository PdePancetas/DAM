package Ejemplo_DatagramSocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Client2 {

	public static void main(String[] args) {
		try {
			int puntuacion = 0;
			boolean acaba = false;
			DatagramSocket ds = new DatagramSocket(8888);
			InetAddress addr2 = InetAddress.getByName("localhost");
			while (!acaba) {
				Thread.sleep(1000);
				byte[] msg = new byte[100];
				DatagramPacket dpRecibo = new DatagramPacket(msg, 100);
				ds.receive(dpRecibo);

				if (new String(msg).equals("FIN")) {
					System.out.println("Recibido " + new String(msg));
				} else if (new String(msg).equals("WIN")) {
					System.out.println("Client 2 pierde, 1 gana");
					acaba = true;
				} else {
					System.out.println("Recibido: " + new String(msg));
					String msgenvio = "";

					DatagramPacket dp1;
					Random r = new Random();
					if (r.nextInt(1, 11) != 10) {
						msgenvio = "pong";
						dp1 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2, 8889);
						ds.send(dp1);
					} else {
						msgenvio = "FIN";
						dp1 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2, 8889);
						System.out.println("Enviado FIN");
						ds.send(dp1);
						puntuacion++;
						System.out.println("p: " + puntuacion);
						if (puntuacion == 5) {
							msgenvio = "WIN";
							System.out.println("Client 1 gana");
							dp1 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2, 8889);
							ds.send(dp1);
							acaba = true;
						}
					}
				}

			}
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}