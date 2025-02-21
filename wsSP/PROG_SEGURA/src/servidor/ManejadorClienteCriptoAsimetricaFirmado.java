package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
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
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class ManejadorClienteCriptoAsimetricaFirmado implements Runnable {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	public ManejadorClienteCriptoAsimetricaFirmado(Socket socket) throws IOException {
		this.socket = socket;
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.dis = new DataInputStream(socket.getInputStream());
	}

	@Override
	public void run() {

		try {

			KeyPair claves = generarClave();
			enviarClavePublica(dos, claves);
			PublicKey clavePublicaCliente = recibirClavePublica(dis);
			
			
			String mensaje = recibirMensaje(dis, clavePublicaCliente);
			System.out.println("Mensaje descifrado: " + mensaje);

			enviarMensaje(dos, claves, "Mensaje FIRMADO de servidor a cliente");
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

	}

	private static KeyPair generarClave() {

		KeyPairGenerator gen;
		KeyPair claves = null;
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			claves = gen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
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