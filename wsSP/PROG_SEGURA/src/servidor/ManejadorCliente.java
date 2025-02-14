package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

class ManejadorCliente implements Runnable {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	ObjectInputStream ois;

	public ManejadorCliente(Socket socket) throws IOException {
		this.socket = socket;
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.dis = new DataInputStream(socket.getInputStream());
		this.ois = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {

		try {
			Cipher c = Cipher.getInstance("AES");
			
			byte[] claveRecibida = dis.readNBytes(2064);
			SecretKeySpec clave = new SecretKeySpec(claveRecibida, "AES");

			c.init(Cipher.DECRYPT_MODE, clave);

			byte[] msgCifrado = dis.readNBytes(2064);
			System.out.println("Clave recibida");
			String msgDescifrado = new String(c.doFinal(msgCifrado));

			System.out.println("mensaje descifrado:" + msgDescifrado);

		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

	}
}