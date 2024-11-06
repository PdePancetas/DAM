package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumWorker {

	/*
	 * Crea un programa SumWorker que lee un número desde su entrada estándar, le
	 * suma uno y lo escribe en su salida estándar. El proceso SumWorker no termina
	 * nunca. Una vez hecho su trabajo vuelve a hacerlo otra vez. z
	 */

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String line;
			while ((line = br.readLine()) != null)
				System.out.println(Integer.parseInt(line) + 1);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
