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

class ManejadorClienteCriptoAsimetricaHibrido implements Runnable {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	public ManejadorClienteCriptoAsimetricaHibrido(Socket socket) throws IOException {
		this.socket = socket;
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.dis = new DataInputStream(socket.getInputStream());
	}

	@Override
	public void run() {

		try {

			KeyPair claves = generarClaves();
			enviarClavePublica(dos, claves);
			SecretKeySpec claveSesion = recibirClaveSesion(dis, claves);
			
			
			String mensaje = recibirMensaje(dis, claveSesion);
			System.out.println("Mensaje descifrado: " + mensaje);
			enviarMensaje(dos, claveSesion, "Mensaje CIFRADO de servidor a cliente");
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

	}

	private SecretKeySpec recibirClaveSesion(DataInputStream dis, KeyPair claves) {
		
		SecretKeySpec claveSesion = null;
		try {
			int longitudClave = dis.readInt();
			byte[] claveSesionCifrada = new byte[longitudClave];
			dis.readFully(claveSesionCifrada);
			
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.DECRYPT_MODE, claves.getPrivate());
			
			claveSesion = new SecretKeySpec(c.doFinal(claveSesionCifrada), "AES");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return claveSesion;
	}

	private static KeyPair generarClaves() {

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

	public static void enviarMensaje(DataOutputStream dos, SecretKey sessionKey, String mensaje)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, sessionKey);
		byte[] msgCifrado = c.doFinal(mensaje.getBytes());

		dos.writeInt(msgCifrado.length);
		dos.write(msgCifrado);
		dos.flush();
	}

	public static String recibirMensaje(DataInputStream dis, SecretKeySpec sessionKey)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		int longitud = dis.readInt();

		byte[] msgCifrado = new byte[longitud];
		dis.readFully(msgCifrado);

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, sessionKey);
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
}