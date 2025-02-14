package cliente;

import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cliente {
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

			enviarBytes(dos, key.getEncoded());
			System.out.println("Clave enviada al servidor");

			Cipher c = Cipher.getInstance("AES");

			c.init(Cipher.ENCRYPT_MODE, key);

			String msg = "Mensaje de cliente a servidor";

			byte[] msgCifrado = c.doFinal(msg.getBytes());

			enviarBytes(dos, msgCifrado);
			System.out.println("Mensaje enviado al servidor");

//    		 byte[] msgRecibidoCifrado = dis.readAllBytes();
//    		 
//    		 c.init(Cipher.DECRYPT_MODE, key);
//    		 
//    		 String msgDescifrado = new String(c.doFinal(msgRecibidoCifrado));
//    		 
//    		 System.out.println("Mensaje descifado: "+msgDescifrado);

		} catch (IOException e) {
			System.err.println("Error al iniciar el cliente: " + e.getMessage());
		}
	}

	private static void enviarBytes(DataOutputStream dos, byte[] bytes) throws IOException {
		dos.write(bytes);
		dos.flush();
	}

}