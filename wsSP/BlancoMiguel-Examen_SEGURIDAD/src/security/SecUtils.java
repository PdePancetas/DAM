package security;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecUtils {

	/**
	 * Genera las claves RSA para el servidor
	 * 
	 * @return claves RSA
	 */
	public static KeyPair genKeyPairRSA() {
		KeyPair claves = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			claves = keyGen.genKeyPair();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return claves;
	}

	/**
	 * El envio de la clave publica del servidor al cliente
	 * 
	 * @param clave puplica del servidor
	 * @param oos   ObjectStream usado por conveniencia para enviar la clave
	 */
	public static void enviarClavePublica(PublicKey clave, ObjectOutputStream oos) {

		try {
			oos.writeObject(clave);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * El recibo de la clave publica del servidor por parte del cliente
	 * 
	 * @param ois ObjectStream usado por conveniencia para recibir la clave
	 * @return la clave publica del servidor
	 */
	public static PublicKey recibirClavePublica(ObjectInputStream ois) {

		PublicKey clavePublica = null;

		try {
			clavePublica = (PublicKey) ois.readObject();
			return clavePublica;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Método utilizado para crear la clave de sesion que se utilizará para la
	 * comunicación
	 * 
	 * @return la clave de sesión
	 */
	public static SecretKey crearClaveSesion() {
		try {
			return KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Envio de la clave de sesion del cliente al servidor de manera segura,
	 * cifrandola con la clave publica del servidor
	 * 
	 * @param dos                 Flujo utilizado para enviar la clave
	 * @param claveSesion         la clave que se va a enviar
	 * @param clavePublicaCliente la clave con la que se cifra la de sesión
	 */
	public static void enviarClaveSesion(DataOutputStream dos, SecretKey claveSesion, PublicKey clavePublicaCliente) {

		try {
			Cipher c = Cipher.getInstance("RSA");

			c.init(Cipher.ENCRYPT_MODE, clavePublicaCliente);

			byte[] datosClaveSesionCifrados = c.doFinal(claveSesion.getEncoded());

			dos.writeInt(datosClaveSesionCifrados.length);
			dos.flush();
			dos.write(datosClaveSesionCifrados);
			dos.flush();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recibo de la clave de sesion cifrada enviada del cliente al servidor,
	 * descifrada usando la clave privada del servidor
	 * 
	 * @param dis                  Flujo utilizado para recibir la clave
	 * @param clavePrivadaServidor clave utilizada para descifrar los datos
	 *                             recibidos
	 * @return la clave de sesión
	 */
	public static SecretKey recibirClaveSesion(DataInputStream dis, PrivateKey clavePrivadaServidor) {

		try {
			int longitud = dis.readInt();
			byte[] claveCifrada = new byte[longitud];

			dis.readFully(claveCifrada);

			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.DECRYPT_MODE, clavePrivadaServidor);

			byte[] clave = c.doFinal(claveCifrada);

			SecretKey claveSesion = new SecretKeySpec(clave, "AES");

			return claveSesion;
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Recibo de un mensaje utilizando la clave de sesion para descifrar los datos
	 * 
	 * @param dis         Flujo utilizado para recibir el mensaje
	 * @param claveSesion clave utilizada para descifrar los datos
	 * @return el mensaje descifrado
	 */
	public static String recibirMensaje(DataInputStream dis, SecretKey claveSesion) {

		try {
			int longitud = dis.readInt();
			byte[] datosCifrados = new byte[longitud];

			dis.readFully(datosCifrados);

			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, claveSesion);

			byte[] datos = c.doFinal(datosCifrados);

			return new String(datos);
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo utilizado por el servidor para recibir los datos recibidos del
	 * cliente, pero cifrados, de tal forma que el servidor no puede acceder a ellos
	 * 
	 * @param dis Flujo utilizado para recibir el mensaje cifrado
	 * @return los datos cifrados
	 */
	public static byte[] recibirMensajeCifrado(DataInputStream dis) {

		try {
			int longitud = dis.readInt();
			byte[] datosCifrados = new byte[longitud];

			dis.readFully(datosCifrados);

			return datosCifrados;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Envio de un mensaje utilizando la clave de sesion para cifrar los datos
	 * 
	 * @param dis         Flujo utilizado para enviar el mensaje
	 * @param claveSesion clave utilizada para cifrar los datos
	 */
	public static void enviarMensaje(DataOutputStream dos, SecretKey claveSesion, String datos) {

		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, claveSesion);

			byte[] datosCifrados = c.doFinal(datos.getBytes());

			dos.writeInt(datosCifrados.length);
			dos.flush();
			dos.write(datosCifrados);
			dos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Envio de los datos cifrados de un mensaje por parte del servidor hacia el
	 * cliente. De esta forma el servidor envia los datos cifrados sin saber qué
	 * contienen.
	 * 
	 * @param dos   Flujo utilizado para recibir el mensaje
	 * @param datos los datos asociados al identificador especificado por el cliente
	 */
	public static void enviarBytes(DataOutputStream dos, byte[] datos) {

		try {

			dos.writeInt(datos.length);
			dos.flush();
			dos.write(datos);
			dos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Recibo de los datos cifrados enviados del servidor al cliente. Así es el
	 * cliente el que descifra los datos
	 * 
	 * @param dis Flujo utilizado para recibir el mensaje
	 * @return los datos
	 */
	public static byte[] recibirBytes(DataInputStream dis) {

		try {
			int longitud = dis.readInt();
			byte[] datos = new byte[longitud];

			dis.readFully(datos);

			return datos;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Si los datos recibidos del servidor al llamar a GET son el mensaje
	 * :NOMESSAGE:, indicando que no se ha encontrado el mensaje asociado al
	 * identificador que el cliente previamente envió al servidor, no se descifran
	 * porque no hace falta, sin embargo , si no son :NOMESSAGE:, se entiende que
	 * son datos cifrados, por lo que se descifran y se devuelve el mensaje completo
	 * descifrado
	 * 
	 * 
	 * @param mensajeCifrado datos cifrados obtenidos
	 * @param claveSesion    clave utilizada para descifrar los datos
	 * @return :NOMESSAGE: si no se encontro el identificador, o el mensaje
	 *         descifrado en caso de que sí existiera.
	 */
	public static String descifrar(byte[] mensajeCifrado, SecretKey claveSesion) {

		try {

			if (new String(mensajeCifrado).equals(":NOMESSAGE:")) {

				return new String(mensajeCifrado);
			} else {

				Cipher c = Cipher.getInstance("AES");

				c.init(Cipher.DECRYPT_MODE, claveSesion);

				return new String(c.doFinal(mensajeCifrado));
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
