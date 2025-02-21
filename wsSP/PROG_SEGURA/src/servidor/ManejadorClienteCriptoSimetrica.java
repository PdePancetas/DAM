package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class ManejadorClienteCriptoSimetrica implements Runnable {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	public ManejadorClienteCriptoSimetrica(Socket socket) throws IOException {
		this.socket = socket;
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.dis = new DataInputStream(socket.getInputStream());
	}

	@Override
	public void run() {

		try {

			SecretKeySpec clave = recibirClave(dis);

			String mensaje = recibirMensaje(dis, clave);
			System.out.println("Mensaje descifrado: " + mensaje);

			enviarMensaje(dos, clave, "Mensaje de servidor a cliente");
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

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

	public static SecretKeySpec recibirClave(DataInputStream dis) throws IOException {

		byte[] clave = recibirBytes(dis);

		return new SecretKeySpec(clave, "AES");
	}

	private static byte[] recibirBytes(DataInputStream dis) throws IOException {
		int longitud = dis.readInt();
		byte[] datos = new byte[longitud];
		dis.readFully(datos);
		return datos;
	}

}