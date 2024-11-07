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

			while (true)
				System.out.println(Integer.parseInt(br.readLine()) + 1);

			//int num = Integer.parseInt(br.readLine())+1; Asi solo lee una vez, a la siguiente devolverá null
			//System.out.println(num);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
