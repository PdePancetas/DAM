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

import security.ComSegura;

public class ClienteAsimetricaHibridoFirmado {
	private final static String SERVIDOR_IP = "localhost";
	private final static int PUERTO = 12345;
	private Socket socket;

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		try {
			Socket socket = new Socket(SERVIDOR_IP, PUERTO);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			
			KeyPair claves = ComSegura.generarClavesRSA();

			ComSegura.enviarClavePublica(dos, claves);
			PublicKey clavePublicaServidor = ComSegura.recibirClavePublica(dis);
			SecretKey claveSesion = ComSegura.crearClaveSesion();
			ComSegura.enviarClaveSesion(clavePublicaServidor, claveSesion, dos);
			
			System.out.println(ComSegura.recibirMensaje(dis, claveSesion, clavePublicaServidor));
			
			ComSegura.enviarMensaje(dos, claveSesion, claves, "Mensaje recibido de cliente");

			System.out.println(ComSegura.recibirMensaje(dis, claveSesion, clavePublicaServidor));
			
			socket.close();
		} catch (IOException e) {
			System.err.println("Error al iniciar el cliente: " + e.getMessage());
		}
	}
}