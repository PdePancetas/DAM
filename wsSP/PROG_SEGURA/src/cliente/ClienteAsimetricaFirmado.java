package cliente;

import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class ClienteAsimetricaFirmado {
	private final static String SERVIDOR_IP = "localhost";
	private final static int PUERTO = 12345;
	private Socket socket;

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		try {
			Socket socket = new Socket(SERVIDOR_IP, PUERTO);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			KeyPair claves = generarClave();
			PublicKey clavePublicaServidor = recibirClavePublica(dis);
			enviarClavePublica(dos, claves);

			enviarMensaje(dos, claves, "Mensaje FIRMADO de cliente a servidor");

			String respuesta = recibirMensaje(dis, clavePublicaServidor);
			System.out.println(respuesta);
			socket.close();
		} catch (IOException e) {
			System.err.println("Error al iniciar el cliente: " + e.getMessage());
		}
	}

	private static KeyPair generarClave() {

		KeyPairGenerator gen;
		KeyPair claves = null;
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			claves = gen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return claves;
	}

	public static void enviarMensaje(DataOutputStream dos, KeyPair keyPair, String mensaje)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
		byte[] msgCifrado = c.doFinal(mensaje.getBytes());

		dos.writeInt(msgCifrado.length);

		dos.write(msgCifrado);
		dos.flush();
	}

	public static String recibirMensaje(DataInputStream dis, PublicKey publicKeyRec)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		int longitud = dis.readInt();

		byte[] msgCifrado = new byte[longitud];
		dis.readFully(msgCifrado);

		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, publicKeyRec);
		byte[] mensajeDescifrado = c.doFinal(msgCifrado);

		return new String(mensajeDescifrado);
	}


	public static void enviarClavePublica(DataOutputStream dos, KeyPair claves) {

		try {
			dos.writeInt(claves.getPublic().getEncoded().length);
			dos.write(claves.getPublic().getEncoded());
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PublicKey recibirClavePublica(DataInputStream dis) {

		PublicKey publicKey = null;
		try {
			int longitud = 0;
			longitud = dis.readInt();
			byte[] publicKeyBytes = new byte[longitud];

			dis.readFully(publicKeyBytes);

			KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
			publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

		} catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return publicKey;
	}

}