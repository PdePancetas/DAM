package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.SecretKey;

import security.SecUtils;

public class Cliente {

	private static final int PUERTO = 12345;
	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static Scanner scanner = new Scanner(System.in);
	private static PublicKey clavePublicaServidor;

	public static void main(String[] args) throws UnknownHostException, IOException {
		socket = new Socket("localhost", PUERTO);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());

		clavePublicaServidor = SecUtils.recibirClavePublica(ois);
		SecretKey claveSesion = SecUtils.crearClaveSesion();
		SecUtils.enviarClaveSesion(dos, claveSesion, clavePublicaServidor);

		String command = "";

		while (!command.equals("EXIT")) {

			System.out.print("""

					Opciones:
						PUT
						GET
						EXIT
						->""");

			command = scanner.nextLine();
			SecUtils.enviarMensaje(dos, claveSesion, command);
			switch (command) {

			case "PUT":
				System.out.print("Identificador: ");
				String id = scanner.nextLine();
				System.out.print("Mensaje: ");
				String mensaje = scanner.nextLine();

				SecUtils.enviarMensaje(dos, claveSesion, id);
				SecUtils.enviarMensaje(dos, claveSesion, mensaje);

				break;
			case "GET":

				System.out.print("Identificador: ");
				id = scanner.nextLine();
				SecUtils.enviarMensaje(dos, claveSesion, id);
				byte[] mensajeCifrado = SecUtils.recibirBytes(dis);

				String mensajeDescifrado = SecUtils.descifrar(mensajeCifrado, claveSesion);
				System.out.println("Mensaje recibido: " + mensajeDescifrado);

				break;
			case "EXIT":
				System.out.println("Desconectandose");
				break;

			default:
				System.out.println("Opcion no valida");

			}

		}

		System.out.println("Cliente desconectado");
		try {
			scanner.close();
			dis.close();
			dos.close();
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
		}

	}

}
