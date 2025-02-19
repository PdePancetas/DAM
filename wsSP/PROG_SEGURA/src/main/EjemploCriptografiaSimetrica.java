package main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EjemploCriptografiaSimetrica {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		 System.out.println("Generador de claves AES");
		 KeyGenerator keygen = KeyGenerator.getInstance("AES");
		 
		 System.out.println("Gener una clave");
		 SecretKey key = keygen.generateKey();
		 
		 System.out.println("Objeto Cipher con cifrado AES");
		 Cipher c = Cipher.getInstance("AES");
		 
		 System.out.println("Configuro c para que encripte usando la clave ");
		 c.init(Cipher.ENCRYPT_MODE, key);
		 
		 String msg = "Mensaje supersecreto";
		 
		 System.out.println("Cifro el mensaje: "+msg);
		 byte[] msgCifrado = c.doFinal(msg.getBytes());
		 
		 System.out.println("Mensaje cifrado: "+new String(msgCifrado));
		 
		 ///COMUNICACION CLI-SERV
		 
		 ///
		 
		 System.out.println("Configuro c para que decriptar usando la clave");
		 c.init(Cipher.DECRYPT_MODE, key);
		 
		 String msgDescifrado = new String(c.doFinal(msgCifrado));
		 
		 System.out.println("Mensaje descifado: "+msgDescifrado);
		 
		 
		 
		 
		 
		 
	}

}
