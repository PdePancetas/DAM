package ejemplosTeoria;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EjemploCriptografiaAsimetrica {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		
		KeyPair parClaves = generator.generateKeyPair();
		
		Cipher c = Cipher.getInstance("RSA");
		 
	}

}
