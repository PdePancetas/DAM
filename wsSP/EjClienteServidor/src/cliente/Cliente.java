package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			// Opciones dadas por el servidor

			String linea = "";
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
//			do {
//				op = Teclado.leerEntero();
//
//				// El servidor obtendra una linea de la cual leerá la opcion
//				// y ejecutara las tareas correspondientes
//				switch (op) {
//				case 1:// Opcion 1: Manda una orden al servidor para crear un fichero
//					LocalDateTime ldt = LocalDateTime.now();
//					String nombre = Teclado.leerCadena();
//					bw.write(op + "|" + nombre + "-" + ldt.format(DateTimeFormatter.ofPattern("hh-mm-ss")));
//					bw.newLine();
//					bw.flush();
//					System.out.println("S: " + br.readLine());
//					break;
//				case 2:// Opcion 1: Manda una orden al servidor para bajar un fichero
//					ldt = LocalDateTime.now();
//					bw.write(op + "|" + "fichero-" + ldt.format(DateTimeFormatter.ofPattern("hh-mm-ss")));
//					bw.newLine();
//					bw.flush();
//					System.out.println("S: " + br.readLine());
//					try {
//						File f = (File) ois.readObject();
//						System.out.println("Recibido el fichero: " + f.getName());
//					} catch (ClassNotFoundException e) {
//						e.printStackTrace();
//					}
//
//					break;
//				case 0: // Opcion 0: Desconectarse del servidor
//					System.out.println("Desconectando");
//					s.close();
//					break;
//				default: // Si la opcion no es valida, vuelve a mostrar el menu
//					System.out.println("Opcion incorrecta\n");
//					break;
//				}
//
//			} while (op != 0);
			bw.write(Teclado.leerCadena());

			String respuesta = br.readLine();

			System.out.println("S: " + respuesta);

			br.close();
			bw.close();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
