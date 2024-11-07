package ejExtra3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejecutor {

	public static void main(String[] args) {

		try {
			Process comando = new ProcessBuilder(args).start();
			
			BufferedReader brComando = new BufferedReader(new InputStreamReader(comando.getInputStream()));
			String lineaComando;
			while ((lineaComando = brComando.readLine()) != null)
				System.out.println(lineaComando);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
