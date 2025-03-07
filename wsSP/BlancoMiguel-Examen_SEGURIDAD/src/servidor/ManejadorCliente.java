package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.SecretKey;

import security.SecUtils;

public class ManejadorCliente implements Runnable {

	private Socket socket;
	private Map<String, byte[]> mensajes;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private KeyPair claves;

	public ManejadorCliente(Socket s, KeyPair claves) {
		try {
			this.socket = s;
			this.mensajes = new HashMap<>();
			this.dis = new DataInputStream(s.getInputStream());
			this.dos = new DataOutputStream(s.getOutputStream());
			this.oos = new ObjectOutputStream(s.getOutputStream());
			this.ois = new ObjectInputStream(s.getInputStream());
			this.claves = claves;
		} catch (IOException e) {
			System.out.println("Error al conectarse al cliente");
		}

	}

	@Override
	public void run() {

		SecUtils.enviarClavePublica(claves.getPublic(), oos);

		SecretKey claveSesion = SecUtils.recibirClaveSesion(dis, claves.getPrivate());
		boolean clienteDesconectado = false;
		while (!clienteDesconectado) {
			String command = SecUtils.recibirMensaje(dis, claveSesion);

			switch (command) {

			case "PUT":
				String id = SecUtils.recibirMensaje(dis, claveSesion);
				byte[] mensaje = SecUtils.recibirMensajeCifrado(dis);

				if (mensajes.containsKey(id)) {
					for(Entry<String, byte[]> e: mensajes.entrySet()) {
						if(e.getKey().equals(id))
							e.setValue(mensaje);
					}
				} else
					mensajes.put(id, mensaje);

				break;
			case "GET":

				id = SecUtils.recibirMensaje(dis, claveSesion);
				if (mensajes.containsKey(id)) {
					SecUtils.enviarBytes(dos, mensajes.get(id));
				} else
					SecUtils.enviarBytes(dos, ":NOMESSAGE:".getBytes());

				break;
			case "EXIT":
				clienteDesconectado = true;
				break;
			default:

			}
		}
		System.out.println("Cliente desconectado");
		try {
			this.dis.close();
			this.dos.close();
			this.ois.close();
			this.oos.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
