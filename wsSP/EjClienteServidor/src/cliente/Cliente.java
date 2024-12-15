package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import utilidadesTeclado.Teclado;

public class Cliente {

	public static void main(String[] args) {
		Socket s = new Socket();
		InetSocketAddress isa = new InetSocketAddress("localhost", 3333);

		try {
			s.connect(isa);
			System.out.println("S: conexión recibida");
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			DataInputStream dis = new DataInputStream(s.getInputStream());

			// Opciones dadas por el servidor
			ArrayList<String> opciones = new ArrayList<>();
			System.out.println("Opciones:");
			String linea = "";

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);

				opciones.add(linea.split(" ")[0]);
				if (linea.split(" ")[0].equals("salir")) { // Detectar fin de bloque
					break; // Sale del bucle
				}
			}
			// Se le pide al cliente que introduzca una opcion hasta que elija salir
			String opcion = "";
			do {
				System.out.print("-> ");
				opcion = Teclado.leerCadena();

				if (opciones.contains(opcion.split(" ")[0])) {

					bw.write(opcion);
					bw.newLine();
					bw.flush();
					String respuesta = "";
					if (opcion.split(" ")[0].equalsIgnoreCase("bajar"))
						recibirCopiaArchivo(opcion.split(" ")[1], dis, br);
					else
						respuesta = br.readLine();

					System.out.println("S: " + respuesta);
				} else
					System.out.println("Opcion no valida");
			} while (!opcion.split(" ")[0].equals("salir"));

			br.close();
			bw.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void recibirCopiaArchivo(String nombre, DataInputStream dis, BufferedReader br) {
		try {
			String mensaje = br.readLine();
			System.out.println("Servidor: " + mensaje);
			if (mensaje.equalsIgnoreCase("Enviando archivo...")) {
				// Leer tamaño del archivo
				long tamañoArchivo = dis.readLong();
				System.out.println("Tamaño del archivo: " + tamañoArchivo + " bytes");

				// Crear copia local del archivo
				try (FileOutputStream fileOutputStream = new FileOutputStream("downloads/" + nombre)) {
					byte[] buffer = new byte[4096];
					int bytesRead;
					long totalBytesRead = 0;

					while (totalBytesRead < tamañoArchivo && (bytesRead = dis.read(buffer)) != -1) {
						fileOutputStream.write(buffer, 0, bytesRead);
						totalBytesRead += bytesRead;
					}
					System.out.println("Archivo recibido y guardado: downloads/" + nombre);
				}
			} else {
				System.out.println("Servidor: " + mensaje);
			}
		} catch (IOException e) {

		}
	}
}
