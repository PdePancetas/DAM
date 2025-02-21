package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ClienteSimetrica {
	private final static String SERVIDOR_IP = "localhost";
	private final static int PUERTO = 12345;
	private Socket socket;

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		try {
			Socket socket = new Socket(SERVIDOR_IP, PUERTO);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecretKey key = keygen.generateKey();

			enviarClave(dos, key);
			System.out.println("Clave enviada al servidor");

			enviarMensaje(dos, key, "Mensaje de cliente a servidor");

			String respuesta = recibirMensaje(dis, key);
			System.out.println(respuesta);
			socket.close();
		} catch (IOException e) {
			System.err.println("Error al iniciar el cliente: " + e.getMessage());
	}
	}

	private static void enviarBytes(DataOutputStream dos, byte[] bytes) throws IOException {
		dos.writeInt(bytes.length); // Enviar la longitud del array primero
		dos.write(bytes); // Luego enviar los datos reales
		dos.flush();
	}

	private static byte[] recibirBytes(DataInputStream dis) throws IOException {
		int longitud = dis.readInt();
		byte[] datos = new byte[longitud];
		dis.readFully(datos);
		return datos;
	}

	public static void enviarMensaje(DataOutputStream dos, SecretKey key, String mensaje)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] msgCifrado = c.doFinal(mensaje.getBytes());

		dos.writeInt(msgCifrado.length);

		dos.write(msgCifrado);
		dos.flush();
	}

	public static String recibirMensaje(DataInputStream dis, SecretKey key)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		int longitud = dis.readInt();

		byte[] msgCifrado = new byte[longitud];
		dis.readFully(msgCifrado);

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] mensajeDescifrado = c.doFinal(msgCifrado);

		return new String(mensajeDescifrado);
	}

	public static void enviarClave(DataOutputStream dos, SecretKey key) throws IOException {
		byte[] clave = key.getEncoded();
		dos.writeInt(clave.length);
		dos.write(clave);
		dos.flush();
	}

}