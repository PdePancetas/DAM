package ejExtra3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import utilidadesTeclado.Teclado;

public class Principal {

	/*
	 * Haz un programa que reciba un comando (ping 127.0.0.1, ipconfig…) lo pase a
	 * un subproceso (otra clase java) que se encarga de ejecutar el comando. El
	 * resultado del comando tiene que escribirse en consola.
	 */

	public static void main(String[] args) {

		System.out.print("Escribe el comando a ejecutar: \n ->");

		String comando = Teclado.leerCadena();

		ArrayList<String> comm = new ArrayList<>();
		
		comm.add("java");
		comm.add("-cp");
		comm.add("./bin");
		comm.add("ejExtra3.Ejecutor");
		for (String s : comando.split(" "))
			comm.add(s);
		try {
			Process ejecutor = new ProcessBuilder(comm).start();
			BufferedReader br = new BufferedReader(new InputStreamReader(ejecutor.getInputStream()));
			String linea;
			while ((linea = br.readLine()) != null)
				System.out.println(linea);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
