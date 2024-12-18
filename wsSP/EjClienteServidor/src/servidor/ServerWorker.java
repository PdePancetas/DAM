package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class ServerWorker implements Runnable {
	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	DataOutputStream dos;
	
	
	public ServerWorker(Socket nsc) {

		this.s = nsc;
		try {
			br = new BufferedReader(new InputStreamReader(nsc.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(nsc.getOutputStream()));
			dos = new DataOutputStream(nsc.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			boolean clienteActivo = true;
			bw.write("crear (nombre_archivo)");
			bw.newLine();
			bw.write("bajar (nombre_archivo)");
			bw.newLine();
			bw.write("salir ");
			bw.newLine();
			bw.flush();

			while (clienteActivo)
				try {
					String opcionRespuesta = br.readLine();
					switch (opcionRespuesta.split(" ")[0]) {
					case "crear":

						crearArchivo(opcionRespuesta.split(" ")[1], bw);
						break;
					case "bajar":
						enviarCopia(opcionRespuesta.split(" ")[1], dos, bw);
						break;
					case "salir":
						bw.write("Conexion finalizada");
						bw.newLine();
						bw.flush();
						clienteActivo = false;
						break;
					}
				} catch (Exception e) {

				}
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void crearArchivo(String nombre, BufferedWriter bw) {

		LocalDateTime fecha = LocalDateTime.now();

		File fichero = new File("datos/" + nombre);
		try {
			fichero.createNewFile();
			FileWriter fr = new FileWriter(fichero);
			fr.write("Creado en: " + fecha);
			fr.close();
			bw.write("Fichero creado con exito");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			try {
				bw.write("No se pudo crear el archivo: " + e.getMessage());
				bw.newLine();
				bw.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	private static void enviarCopia(String nombre, DataOutputStream dos, BufferedWriter bw)
			throws IOException {
		File archivo = new File("datos/"+nombre);
		if (archivo.exists()) {
			
			bw.write("Enviando archivo...");
			bw.newLine();
			bw.flush();

			
			dos.writeLong(archivo.length());
			dos.flush();

			
			try (FileInputStream fis = new FileInputStream(archivo)) {
				byte[] buffer = new byte[(int) archivo.length()];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					dos.write(buffer, 0, bytesRead);
				}
				dos.flush();
			}
			System.out.println("Archivo enviado: " + nombre);
		} else {
			bw.write("Error: El archivo '" + nombre + "' no existe.");
			bw.newLine();
			bw.flush();
		}
	}


}
