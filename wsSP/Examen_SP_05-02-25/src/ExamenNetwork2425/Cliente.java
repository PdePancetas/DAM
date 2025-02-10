package ExamenNetwork2425;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

	private static Socket socket;
	private static final int PUERTO = 7777;
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static ObjectInputStream ois;

	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner(System.in);

			socket = new Socket("localhost", PUERTO);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			ois = new ObjectInputStream(socket.getInputStream());

			List<String> ops = new ArrayList<>();
			String linea = "";
			while (!(linea = br.readLine()).equals("")) {
				ops.add(linea);

			}

			List<String> comandos = ops.stream().map(s -> s = s.split("-")[0].trim()).toList();
			String opcionCliente = "";
			while (!opcionCliente.equalsIgnoreCase("SALIR")) {
				System.out.println();
				ops.stream().forEach(System.out::println);
				System.out.print("\n-> ");

				opcionCliente = teclado.nextLine();
				bw.write(opcionCliente);
				bw.newLine();
				bw.flush();

				if (opcionCliente.split(",")[0].equalsIgnoreCase(comandos.get(0))) {
					String respuesta = br.readLine();
					System.out.println(respuesta);
				} else if (opcionCliente.split(" ")[0].equalsIgnoreCase(comandos.get(1).split(" ")[0])) {
					String respuesta = br.readLine();
					System.out.println(respuesta);
				} else if (opcionCliente.equalsIgnoreCase(comandos.get(2).split(" ")[0])) {

					ArrayList<Tarea> tareas = (ArrayList<Tarea>) ois.readObject();
					System.out.println("Mis tareas: \n");
					tareas.forEach(System.out::println);

				} else if (opcionCliente.split(",")[0].equalsIgnoreCase(comandos.get(3).split(" ")[0])) {
					String respuesta = br.readLine();
					System.out.println(respuesta);
					
				} else if (opcionCliente.split(",")[0].equalsIgnoreCase(comandos.get(4))) {
					System.out.println("Desconectandose");
					teclado.close();
					br.close();
					bw.close();
				} else {
					String respuesta = br.readLine();
					System.out.println(respuesta);
				}

			}

			System.out.println("Te has desconectado");
			teclado.close();
			br.close();
			bw.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
