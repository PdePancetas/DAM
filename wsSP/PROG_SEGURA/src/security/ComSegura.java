package security;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
import javax.crypto.spec.SecretKeySpec;

public class ComSegura {

	public static KeyPair generarClavesRSA() {

		KeyPairGenerator gen;
		KeyPair claves = null;
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(4096);
			claves = gen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return claves;
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

	public static SecretKey crearClaveSesion() {

		SecretKey claveSesion = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			claveSesion = keyGen.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claveSesion;
	}

	public static void enviarMensaje(DataOutputStream dos, SecretKey sessionKey, KeyPair claves, String mensaje)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, sessionKey);
		byte[] msgCifrado = c.doFinal(mensaje.getBytes()); // Primero se obtiene el mensaje cifrado con la sessionKey

		byte[] msgCifradoFirmado = firmarDatos(msgCifrado, claves); /// Luego se firma ese mensaje cifrado con la
																	/// privada
																	/// del
																	/// emisor
																	///
		dos.writeInt(msgCifradoFirmado.length); // Se envia un mensaje cifrado y firmado
		dos.write(msgCifradoFirmado);
		dos.flush();
	}

	public static String recibirMensaje(DataInputStream dis, SecretKey sessionKey, PublicKey clave)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		int longitud = dis.readInt();

		byte[] msgCifradoFirmado = new byte[longitud];
		dis.readFully(msgCifradoFirmado);
		
		byte[] msgCifrado = desfirmarDatos(msgCifradoFirmado, clave);

		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, sessionKey);
		byte[] mensajeDescifrado = c.doFinal(msgCifrado);

		return new String(mensajeDescifrado);
	}

	public static SecretKeySpec recibirClaveSesion(DataInputStream dis, KeyPair claves) {

		SecretKeySpec claveSesion = null;
		try {
			int longitudClave = dis.readInt();
			byte[] claveSesionCifrada = new byte[longitudClave];
			dis.readFully(claveSesionCifrada);

			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.DECRYPT_MODE, claves.getPrivate());

			claveSesion = new SecretKeySpec(c.doFinal(claveSesionCifrada), "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return claveSesion;
	}

	public static void enviarClaveSesion(PublicKey clavePublicaServidor, SecretKey claveSesion, DataOutputStream dos) {
		try {
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.ENCRYPT_MODE, clavePublicaServidor);
			byte[] claveSesionCifrada = c.doFinal(claveSesion.getEncoded());

			dos.writeInt(claveSesionCifrada.length);
			dos.write(claveSesionCifrada);
			dos.flush();

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static byte[] firmarDatos(byte[] datos, KeyPair claves) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, claves.getPrivate());

		return c.doFinal(datos);

	}

	public static byte[] desfirmarDatos(byte[] datos, PublicKey clave) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.DECRYPT_MODE, clave);

		return c.doFinal(datos);

	}

}
