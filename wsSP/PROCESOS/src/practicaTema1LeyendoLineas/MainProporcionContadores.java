package practicaTema1LeyendoLineas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Arrays;

import utilidadesTeclado.Teclado;

public class MainProporcionContadores {

	/*
	 * Main: se encarga de lanzar los otros procesos, comunicar con ellos y de
	 * comunicarse con el usuario.
	 * 
	 * DataReader: se encarga de leer un fichero grande con una secuencia muy grande
	 * de datos dentro (data.dat)
	 * 
	 * NumberCounter: recibe una secuencia de datos y cuenta cuantos unos, cuantos
	 * dos, cuantos tres… hay en esa secuencia. Después los envías al Main en algún
	 * formato.
	 * 
	 * El objetivo es que el Main ejecute un DataReader y varios NumberCounter. El
	 * DataReader leerá una secuencia (grande) de datos, se la pasará al Main y este
	 * la repartirá entre los NumberCounter.
	 * 
	 * Al final el Main leerá la respuesta de los varios NumberCounter y sacará por
	 * pantalla la cantidad de unos, dos, tres… del fichero.
	 * 
	 */

	public static void main(String[] args) {

		ProcessBuilder DataReaderBuilder = new ProcessBuilder("java", "-cp", "./bin",
				"practicaTema1LeyendoLineas.DataReader");
		int[] conteoTotal = new int[10];
		try {

			int lineas = DataReader.conteoLineas(new File(".//src//practicaTema1LeyendoLineas//data.dat"));

//			int lineas = 328160;

			Process dataReader = DataReaderBuilder.start();
			System.out.println("En cuantos contadores quiere dividir el trabajo?: ");
			int contadores = Teclado.leerEntero();
			long initTime = System.currentTimeMillis();
			
			for (int i = 0; i < contadores; i++) {
				int start = (i * (lineas / contadores)) + 1;
				int end = (i + 1) * (lineas / contadores);
				
				int[] nums = lanzarCounter(dataReader, start, end);
				System.out.println("Trabajador " + (i + 1) + " ha terminado");

				for (int j = 0; j < nums.length; j++) {
					conteoTotal[j] += nums[j];
				}

			}

			// Array final con la suma de todos los arrays
//			System.out.println("Array final sumado: " + Arrays.toString(conteoTotal));
			for (int i = 0; i < 10; i++) {
				System.out.println(i + ": " + conteoTotal[i] + " veces");
			}

			System.out.println("Tardo " + (System.currentTimeMillis() - initTime) + " ms");
			// Entre 90 y 100 trabajadores es lo optimo.

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int[] lanzarCounter(Process dataReader, long init, long end)
			throws IOException, ClassNotFoundException {

		// Lectura de datos de dataReader
		BufferedReader brDataReader = new BufferedReader(new InputStreamReader(dataReader.getInputStream()));

		String datos = "";
		String linea;
		for (long i = init; i < end; i++)
			if ((linea = brDataReader.readLine()) != null)
				datos += linea;

		// Creacion, Escritura y lectura para numberCounter
		ProcessBuilder NumberCounterBuilder = new ProcessBuilder("java", "-cp", "./bin",
				"practicaTema1LeyendoLineas.NumberCounter");
		Process numberCounter = NumberCounterBuilder.start();

		BufferedReader brNumberCounter = new BufferedReader(new InputStreamReader(numberCounter.getInputStream()));
		PrintStream psNumberCounter = new PrintStream(numberCounter.getOutputStream(), true);

		psNumberCounter.println(datos);
		String Counter = brNumberCounter.readLine();
		int[] numeros = new int[10];

		for (int i = 0; i < 10; i++) {
			numeros[i] = Integer.parseInt(Counter.split(";")[i]);
		}
		numberCounter.destroy();
		return numeros;
	}

}
