package clasesLeo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Servidor {

	public static void main(String[] args) {

		InetSocketAddress isa = new InetSocketAddress("localhost", 7777);

		try {
			ServerSocket ss = new ServerSocket();
			ss.bind(isa);

			System.out.println("esperando cliente");
			Socket so=ss.accept();
			System.out.println("cliente conectado");
			
			
			
			InputStream is = so.getInputStream();
			OutputStream os = so.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			ObjectInputStream ois = new ObjectInputStream(is);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			//GENERO LAS CLAVES
			
			KeyPair keyPair = generoClaves();
			
			//OBTENGO LAS CLAVES PUBLICAS Y PRIVADAS
			
			PublicKey clavePublicaServidor = keyPair.getPublic();
			
			PrivateKey calvePrivadaServidor = keyPair.getPrivate();
			
			//ESPERO CALVE SIMETRICA
			//SecretKey key = (SecretKey)ois.readObject();
			//System.out.println("recibiendo clave simetrica");
			
			
			//SERVIDOR ESPERA LA CLAVE PUBLICA
			System.out.println("servidor espera clave publica de cliente");
			PublicKey calvePublicaCliente = (PublicKey) ois.readObject();
			System.out.println("clave publica recibida!");
			
			//ENVIO CLAVE PUBLICA A CLIENTE
			oos.writeObject(clavePublicaServidor);
			oos.flush();
			
			System.out.println("clave publica de servidor enviada!");
			
			//ESPERO QUE ME ENVIE LA LONGITUD DEL ARRAY
			System.out.println("esperando longitud del array");
			int longitud = is.read();
			
			
			//ESPERO QUE ME EVNIE EL MESAJE CIFRADO Y FIRMADo
			System.out.println("esperando mensaje cifrado y firmado");
			byte [] msgFirmadoCifrado = dis.readNBytes(longitud);
			
			
			System.out.println("MENSAJE CIFRADO Y FRIAMDO: "+ new String(msgFirmadoCifrado));
			//PRIMERO DECIFRO EL MENSAJE 
			byte [] msgDecifrado = deCifraMensaje(msgFirmadoCifrado,calvePrivadaServidor);
			
			
			System.out.println("MENSAJE  FIRMADO: "+ new String(msgDecifrado));
			
			//SEGUNDO LO DEFIRMO CON LA CLAVE PUBLICA DE CLIENTE
			byte [] msgDeFirmadoDeCifrado = deFirmarMensajeDeCifrado(msgDecifrado,calvePublicaCliente);
			
			
			System.out.println("MENSAJE: "+ new String(msgDeFirmadoDeCifrado));
			
			
			Scanner sc = new Scanner(System.in);
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static byte[] deCifraMensaje(byte[] msgDeFirmadoCifrado, PrivateKey calvePrivadaServidor) {
		
		byte [] msgDecifrado = null;
		
		try {
			Cipher c = Cipher.getInstance("RSA");
			c.init(Cipher.DECRYPT_MODE,calvePrivadaServidor );
			
			msgDecifrado = c.doFinal(msgDeFirmadoCifrado);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msgDecifrado;
	}

	private static byte[] deFirmarMensajeDeCifrado(byte[] msgCifradoFirmado, PublicKey calvePublicaCliente) {

		// DEFIRMO EL MESAJE CIFRADO CON LA CLAVE PUBLIC DE CLIENTE
		byte[] msgDeFirmadoCifrado = null;
		try {
			Cipher c = Cipher.getInstance("RSA");

			c.init(Cipher.DECRYPT_MODE, calvePublicaCliente);

			msgDeFirmadoCifrado = c.doFinal(msgCifradoFirmado);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msgDeFirmadoCifrado;
	}

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

}
