package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;

import security.SecUtils;

public class Servidor {
	
	/**
	 ESTRUCTURA: 
	 
	 Se utiliza la criptografía híbrida
	 El servidor crea un par de claves RSA, y envia la publica al cliente
	 El cliente recibe esa clave, y envia una clave de sesión AES generada por él, cifrada con la publica del servidor 
	 El servidor recibe la clave de sesión AES cifrada y la descifra con su clave privada
	 
	 He usado esta filosofía ya que al utilizar la clave de sesion AES, la comunicacion es mucho más rapida que con RSA
	 Ademas, el envio y recibo de la clave AES se hace de manera segura, por lo que una vez cliente y servidor tienen esta clave, 
	 no hace falta usar RSA, haciendo que el programa funcione fluidamente
	 
	 A partir de este punto, las comunicaciones se harán de manera segura cifrando los datos con la clave de sesión
	 
	 En la clase SecUtils, cada metodo lleva una descripción de su uso
	 
	 */

	private static final int PUERTO = 12345;
	private static final KeyPair claves = SecUtils.genKeyPairRSA();
	
	public static void main(String[] args) throws IOException {
		try(ServerSocket socket = new ServerSocket(PUERTO)){

			System.out.println("Esperando clientes...");
			while(true) {
				Socket s = socket.accept();
				System.out.println("Cliente conectado");
				
				new Thread(new ManejadorCliente(s, claves)).start();
				
			}
		}
		
	}
	
	
	
	
}
