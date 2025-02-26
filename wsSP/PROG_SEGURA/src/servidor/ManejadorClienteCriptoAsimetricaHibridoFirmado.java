package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
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
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import security.ComSegura;

class ManejadorClienteCriptoAsimetricaHibridoFirmado implements Runnable {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	KeyPair claves;

	public ManejadorClienteCriptoAsimetricaHibridoFirmado(Socket socket, KeyPair claves) throws IOException {
		this.socket = socket;
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.dis = new DataInputStream(socket.getInputStream());
		this.claves = claves;
	}

	@Override
	public void run() {

		try {
			PublicKey clavePublicacliente = ComSegura.recibirClavePublica(dis);
			ComSegura.enviarClavePublica(dos, claves);
			SecretKeySpec claveSesion = ComSegura.recibirClaveSesion(dis, claves);
			
			
			ComSegura.enviarMensaje(dos, claveSesion, claves, "Sesion iniciada");
			
			System.out.println(ComSegura.recibirMensaje(dis, claveSesion, clavePublicacliente));
			
			ComSegura.enviarMensaje(dos, claveSesion, claves, "Mensaje recibido de servidor");
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

	}
}