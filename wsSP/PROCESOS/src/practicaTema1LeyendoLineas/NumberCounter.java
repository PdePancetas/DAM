package practicaTema1LeyendoLineas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberCounter {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String linea;

		String numerosContados = "";

		if ((linea = br.readLine()) != null) {
			for (int numero = 0; numero < 10; numero++) {
				long conteo = 0l;
				for (int i = 0; i < linea.length(); i++) {
					if (linea.charAt(i) == numero + '0') {
						conteo++;
					}
				}

				numerosContados += conteo + (!(numero == 9) ? ";" : "");
			}

		}

		System.out.println(numerosContados);
		br.close();

	}
}
