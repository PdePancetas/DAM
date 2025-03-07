package ejemplos;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class S02Simetrica2 {

	public static void main(String[] args) {
		try {
			System.out.println("Creo generador de claves AES");
			KeyGenerator keygen= KeyGenerator.getInstance("AES");
			
			System.out.println("Genero una clave");
			SecretKey key=keygen.generateKey();
			
			
			
			System.out.println("Objeto Cipher con cifrado AES");
			Cipher c=Cipher.getInstance("AES");
			
			System.out.println("Configuro c para que encripte usando la clave ");
			c.init(Cipher.ENCRYPT_MODE,key);
			
			String msg="Mensaje supersecreto que quiero encriptar";
			
			System.out.println("cifro el mensaje:"+msg);
			byte [] msgCifrado=c.doFinal(msg.getBytes());
			
			System.out.println("mensaje cifrado:"+new String(msgCifrado));
			
			
			//key.getEncoded me da una representaci�n en byte[] de la clave.
			// si este array se env�a por socket a otro ordenador, se puede reconstruir 
			// la llave usando SecretKeySpec.
			
			//nota: el byte[] que representa la clave, se podr�a enviar cifrado, usando 
			//criptograf�a asimetrica...
			
			SecretKeySpec key2=new SecretKeySpec(key.getEncoded(),"AES");
			
			System.out.println("Configuro c para decriptar usando la misma clave ");
			c.init(Cipher.DECRYPT_MODE,key2);
			
			String msgDescifrado=new String(c.doFinal(msgCifrado));
			
			System.out.println("mensaje descifrado:"+msgDescifrado);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
