package Ejemplo_DatagramSocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Random;

public class Client1 {

	public static void main(String[] args) {
		try {
			int puntuacion = 0;
			boolean acaba = false;
			DatagramSocket ds;
			DatagramSocket ds2;
			ds = new DatagramSocket();
			ds2 = new DatagramSocket(8889);
			InetSocketAddress addr2 = new InetSocketAddress("localhost", 8888);
			while (!acaba) {
				Thread.sleep(1000);
				String mensaje = "ping";
				InetAddress addr = InetAddress.getByName("localhost");
				DatagramPacket dp1 = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, addr, 8888);
				ds.send(dp1);

				byte[] msg = new byte[100];
				DatagramPacket dpRecibo = new DatagramPacket(msg, 100);
				ds2.receive(dpRecibo);
				System.out.println("Recibido: " + new String(msg));
				String msgenvio = "";
				Random r = new Random();
				if (new String(msg).equals("FIN")) {
					System.out.println("Recibido FIN");
				} else if (new String(msg).equals("WIN")) {
					System.out.println("Client 1 pierde, 2 gana");
					acaba = true;
				} else {
					DatagramPacket dp2;
					if (r.nextInt(1, 11) != 10) {
						msgenvio = "ping";
						dp2 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2);
						ds.send(dp2);
					} else {
						msgenvio = "FIN";
						dp2 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2);
						ds.send(dp2);
						System.out.println("Enviado FIN");
						puntuacion++;
						System.out.println("p: " + puntuacion);
						if (puntuacion == 5) {
							msgenvio = "WIN";
							System.out.println("Client 1 gana");
							dp2 = new DatagramPacket(msgenvio.getBytes(), msgenvio.getBytes().length, addr2);
							ds.send(dp2);
							acaba = true;
						}
					}
				}

			}

			ds.close();
			ds2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}