package practicaTema1LeyendoLineas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import utilidadesTeclado.Teclado;

public class MainBufferParaContador {

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
			System.out.println("Tamaño de lineas para cada contador?: ");
			int buffer = Teclado.leerEntero();
			
			
			
			BufferedReader brDataReader = new BufferedReader(dataReader.inputReader());
			String datos="";
			String linea;
			long initTime = System.currentTimeMillis();
			int contadores=0;
			while ((linea = brDataReader.readLine()) != null) {
				
				int[] nums = lanzarCounter(brDataReader, dataReader, buffer);
				contadores++;
				for (int j = 0; j < nums.length; j++) {
					conteoTotal[j] += nums[j];
				}
				System.out.println("Contador "+contadores+" lanzado");
				
			}
			
			for (int i = 0; i < 10; i++) {
				System.out.println(i + ": " + conteoTotal[i] + " veces");
			}

			

			

			// Array final con la suma de todos los arrays
//			System.out.println("Array final sumado: " + Arrays.toString(conteoTotal));
			System.out.println("Se han usado "+contadores+" contadores.");
			System.out.println("Tardo " + (System.currentTimeMillis() - initTime) + " ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int[] lanzarCounter(BufferedReader brDataReader, Process dataReader, int buffer) throws IOException {
		// Lectura de datos de dataReader
				
				String datos = "";
				String linea;
				for (long i = 0; i < buffer; i++)
					if ((linea = brDataReader.readLine()) != null)
						datos += linea;
				
				// Creacion, Escritura y lectura para numberCounter
				ProcessBuilder NumberCounterBuilder = new ProcessBuilder("java", "-cp", "./bin",
						"practicaTema1LeyendoLineas.NumberCounter");
				Process numberCounter = NumberCounterBuilder.start();

				BufferedReader brNumberCounter = new BufferedReader(new InputStreamReader(numberCounter.getInputStream()));
				BufferedWriter bwNumberCounter = new BufferedWriter(new OutputStreamWriter(numberCounter.getOutputStream()));
				
				bwNumberCounter.write(datos);
				bwNumberCounter.newLine();
				bwNumberCounter.flush();
				
				String Counter = brNumberCounter.readLine();
				int[] numeros = new int[10];

				for (int i = 0; i < 10; i++) {
					numeros[i] = Integer.parseInt(Counter.split(";")[i]);
				}
				numberCounter.destroy();
				return numeros;
	}

}