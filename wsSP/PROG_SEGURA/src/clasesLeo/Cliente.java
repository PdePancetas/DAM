package clasesLeo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cliente {

	public static void main(String[] args) {

		
		//EL SERVIDOR ES EL QUE CREA LAS PRIMERAS PARES DE CLAVES, CUANDO SE CONECTA EL CLIENTE
		
		//EL CLEINTE CREA LA CLAVE DE SESION, Y LA CODIFICA CON LA CLAVE PUBLICA DE SERVIDOR
		
		//LUEGO DE TENER LAS CLAVE SIMETRICA EN CLIENTE Y SERVIDOR, LA COMUNICACION SE REALIZA CON SIMETRICA
		
		
		InetSocketAddress isa = new InetSocketAddress("localhost", 7777);

		Socket so = new Socket();

		try {

			so.connect(isa);
			System.out.println("Cliente conectado!");

			InputStream is = so.getInputStream();
			OutputStream os = so.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);
			// GENERO LAS CLAVES

			KeyPair keyPair = generoClaves();

			// OBTENGO LA PUBLICA Y PRIVADA

			PublicKey clavePublicaCliente = keyPair.getPublic();

			PrivateKey clavePrivadaCliente = keyPair.getPrivate();

			// ENVIO LA CLAVE PUBLICA AL SERVIDOR
			oos.writeObject(clavePublicaCliente);
			oos.flush();
			System.out.println("clave publica enviada al servidor!");
			System.out.println();
			
			//System.out.println("creo la clave simetrica y la envio al servidor");
			//SecretKey keySimetrica = generoClavesSimetrica();
			
			//oos.writeObject(keySimetrica);
			//oos.flush();
			//System.out.println("calve simetrica enviada");
			

			// ESPERO QUE EL SERVIDOR ME ENVIA SU CLAVE PULICA
			System.out.println("esperando clave publica de servidor");
			PublicKey clavePublicaServidor = (PublicKey) ois.readObject();
			System.out.println("clave publica recibida!");

			// CLIENTE CREA EL MENSAJE-----

			String mensaje = "h";

			// CLIENTE FIRMA EL MENSAJE
			byte[] msgFirmado = firmarMensaje(mensaje.getBytes(), clavePrivadaCliente);

			System.out.println("MENSAJE FIRMADO " + new String(msgFirmado));

			// CLIENTE CIFRA EL MENSAJE
			
			System.out.println(msgFirmado.length);
			byte[] msgCifradoFirmado = cifrarMensaje(msgFirmado, clavePublicaServidor);

			System.out.println("MENSAJE CIFRADO Y FIRMADO: " + new String(msgCifradoFirmado));
			

			// ENVIO LA LONGITUD DEL MENSAJE
			os.write(msgCifradoFirmado.length);
			os.flush();
			System.out.println("logitud enviado");

			// ENVIO EL MENSAJE CIFRADO Y FIRMADO
			dos.write(msgCifradoFirmado);
			dos.flush();

			System.out.println("mensaje cifrado y firmado enviado!");

			Scanner sc = new Scanner(System.in);

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// GENERADOR DE CLAVES
	public static KeyPair generoClaves() {

		KeyPair keyPair = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(4096);
			keyPair = keyGen.generateKeyPair();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return keyPair;

	}
	
	//HIBRIDA
	//SERVIDOR MANDA LA CLAVE PUBLICA-CLAVE DE SESION, SIMETRICA
	//CREO LAS CLAVES RSA EN AMBOS
	//INTERCMABIO

	
	
	//UTILIZA READFULLY PAARA LEER EL ARRAY
	public static byte[] cifrarMensaje(byte[] msgCifradoPara, PublicKey clavePublicaServidor) {

		/*
		byte[] array = new byte[msgCifradoPara.length];
		//VOY A DIVIDIR EL ARRAY EN VARIOS
		int bloqueMaximo = 245;
		int numBloque = (int) Math.ceil((double) msgCifradoPara.length / bloqueMaximo);
		byte[][] arrayBloques = new byte[numBloque][];

		
		 for (int i = 0; i < numBloque; i++) {
		        int inicio = i * bloqueMaximo;// me indica donde empieza en el siguiente bloque
		        int length = Math.min(bloqueMaximo, msgCifradoPara.length - inicio);
		        arrayBloques[i] = Arrays.copyOfRange(msgCifradoPara, inicio, inicio + length);
		    }
		
		
		 int pos = 0;
		 
		 for (int i = 0; i < arrayBloques.length; i++) {
			
			 for (int j = 0; j < arrayBloques[j].length; j++) {
				 array[pos] = arrayBloques[i][j];
				 pos++;
			}
			 
		}
		 
		 */
		 
		
		
		byte[] msgCifrado = null;

		try {
			Cipher c = Cipher.getInstance("RSA");

			// UTILIZO LA CLAVE PUBLIC DE SERVIDOR PARA CIFRAR EL MENSAJE
			c.init(Cipher.ENCRYPT_MODE, clavePublicaServidor);

			msgCifrado = c.doFinal(msgCifradoPara);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msgCifrado;

	}

	public static byte[] firmarMensaje(byte[] mensajeCifrado, PrivateKey clavePrivadaCliente) {

		byte[] msgCifradoFirmado = null;

		try {
			Cipher c = Cipher.getInstance("RSA");

			c.init(Cipher.ENCRYPT_MODE, clavePrivadaCliente);

			msgCifradoFirmado = c.doFinal(mensajeCifrado);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msgCifradoFirmado;
	}
	
	public static SecretKey generoClavesSimetrica() {

		SecretKey key = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			key = keyGen.generateKey();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return key;

	}

	
	
}
