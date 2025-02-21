package cliente;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
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
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ClienteAsimetricaHibrido {
	private final static String SERVIDOR_IP = "localhost";
	private final static int PUERTO = 12345;
	private Socket socket;

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		try {
			Socket socket = new Socket(SERVIDOR_IP, PUERTO);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			PublicKey clavePublicaServidor = recibirClavePublica(dis);
			SecretKey claveSesion = crearClaveSesion();
			
			enviarClaveSesion(clavePublicaServidor, claveSesion, dos);
			
			enviarMensaje(dos, claveSesion, "Mensaje CIFRADO de cliente a servidor");

			String mensajeRecibido = recibirMensaje(dis, claveSesion);
			System.out.println(mensajeRecibido);
			
			socket.close();
		} catch (IOException e) {
			System.err.println("Error al iniciar el cliente: " + e.getMessage());
		}
	}

	private static void enviarClaveSesion(PublicKey clavePublicaServidor, SecretKey claveSesion, DataOutputStream dos) {
		try {
	        Cipher c = Cipher.getInstance("RSA");
	        c.init(Cipher.ENCRYPT_MODE, clavePublicaServidor);            
	        byte[] claveSesionCifrada = c.doFinal(claveSesion.getEncoded());

	        dos.writeInt(claveSesionCifrada.length);
	        dos.write(claveSesionCifrada);
	        dos.flush();

	    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private static SecretKey crearClaveSesion() {
		
		SecretKey claveSesion = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			claveSesion = keyGen.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claveSesion;
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

	public static String recibirMensaje(DataInputStream dis, SecretKey sessionKey)
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