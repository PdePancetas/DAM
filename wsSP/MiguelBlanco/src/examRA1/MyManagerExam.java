package examRA1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyManagerExam {

	public static void main(String[] args) throws InterruptedException {
		String op = "";
		Scanner scan = new Scanner(System.in);
		ArrayList<Process> procesosCreados = new ArrayList<>();

		// inserir tu nombre y apellidos en la siguiente variable
		String autor = "Miguel Blanco";

		// 3 colecciones de datos
		int[][] datos = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ 2, 2, 424, 234, 42, 423, 423, 4234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 23, 423, 423,
						423, 423, 423, 23, 423, 4, 234, 234, 234, 23, 423, 42, 35, 23, 54, 6543, 6, 576, 456, 34, 6534,
						5324, 67, 454, 754, 73, 453, 453, 476, 45, 745, 6 },
				{ 11, 22, 33, 44, 55, 66, 77, 88, 99, 111, 222, 333, 444, 555, 666, 777, 888, 999 } };
		boolean sigue = true;
		while (sigue) {
			System.out.println("Bienvenido a MyManager de " + autor);
			System.out.println("Indica la operacion (escribe \"menu\" para ver el menu)");
			System.out.print("MyManager>");
			op = scan.next();
			if (op.equals("menu")) {
				System.out.println("operaciones permitidas:");
				System.out.println("menu  -> abre este menu");
				System.out.println("input   -> recibe datos y crea MyProcessors");
				System.out.println("list  -> indica la lista de los MyProcessors activos");
				System.out.println("reset  -> elimina todos los MyWriter activos");
				System.out.println(
						"connect -> lee lo que está escribiendo el MyProcessor con un determinado identificador");
				System.out.println("exit -> sale del programa");

			}

			if (op.equals("input")) {
				/*
				 * implementa la funcionalidad create: Esta funcion solicita al usuario un
				 * numero para saber con cuál coleccion de datos va a trabajar (Las 3
				 * colecciones de datos disponibles estan en la variable datos) En base a
				 * cuantos numeros hay en la colecion seleccionada , el programa crea
				 * MyProcessors. Habrá un MyProcessor por cada 5 numeros que se tienen que
				 * procesar. Por ejemplo, si el usuario ha elegido la primera coleccion, habrá 2
				 * MyProcessors. My manager luego repartirá los numeros por procesar entre los
				 * MyProcessors. Nota: si se vuelve a ejecutar la operación input, los
				 * MyProcessor existente deberán desaparecer y se crearán nuevos.
				 */

				for (int i = 0; i < procesosCreados.size(); i++)
					procesosCreados.get(i).destroy();

				System.out.print("indica los datos que vas a usar (1 a 3): ");
				int numDatos = scan.nextInt();
				switch (numDatos) {
				case 1:
					int[] datos1 = datos[0];
					try {
						procesosCreados = crearProcesos(datos1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					int[] datos2 = datos[1];
					try {
						procesosCreados = crearProcesos(datos2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					int[] datos3 = datos[2];
					try {
						procesosCreados = crearProcesos(datos3);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}

			}

			if (op.equals("list")) {
				/*
				 * implementa la funcionalidad list: Esta funcionalidad escribe una lista de los
				 * MyProcessor que se están actualmente ejecutando
				 */
				for (int i = 0; i < procesosCreados.size(); i++) {
					System.out.println("Proceso pid: " + procesosCreados.get(i).pid() + ", esta ejecutandose: "
							+ procesosCreados.get(i).isAlive());
				}
			}

			if (op.equals("reset")) {
				/*
				 * implementa la funcionalidad reset: esta funcionalidad devuelve el programa al
				 * mismo estado en el que estaba al princio de su ejecución es decir que no
				 * deberá haber ningún MyProcessor ejecutandose
				 */

				for (int i = 0; i < procesosCreados.size(); i++)
					procesosCreados.get(i).destroy();

				procesosCreados = new ArrayList<>();
			}

			if (op.equals("connect")) {
				/*
				 * implementa la funcionalidad connect: Esta funcionalidad solicita al usuario
				 * un número de identificador e imprime en pantalla todo lo que ha escrito el
				 * MyProcessor que tiene dicho identificador
				 */
				try {
					System.out.print("Introduce numero pid: ");
					long pid = scan.nextLong();

					for (int i = 0; i < procesosCreados.size(); i++)
						if (procesosCreados.get(i).pid() == pid) {
							BufferedReader br = new BufferedReader(
									new InputStreamReader(procesosCreados.get(i).getInputStream()));
							String linea;
							if ((linea = br.readLine()) != null)
								System.out.println(linea);
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (op.equals("exit")) {
				/*
				 * implementa la funcionalidad exit: limpia los recursos y sale del programa
				 */

				for (int i = 0; i < procesosCreados.size(); i++)
					procesosCreados.get(i).destroy();

				System.out.println("Gracias por haber usado MyManager de " + autor);
				sigue = false;
			}
		}

		scan.close();
	}

	private static ArrayList<Process> crearProcesos(int[] datos) throws IOException {
		int numProcessors = datos.length / 5 + 1;
		if (datos.length % 5 == 0)
			numProcessors--;

		System.out.println("creo " + numProcessors + " MyProcessor porque hay " + datos.length + " numeros");
		ArrayList<Process> procesos = new ArrayList<>();
		int cont = 0;
		for (int i = 0; i < numProcessors; i++) {

			Process proceso = new ProcessBuilder("java", "-cp", "./bin", "examRA1.MyProcessor", i + "").start();
			procesos.add(proceso);

			BufferedWriter br = new BufferedWriter(new OutputStreamWriter (proceso.getOutputStream()));

			for (int k = 0; k < 5; k++) {
				if(cont < datos.length) {
					int num = datos[cont];
					br.write(num);
					cont++;
				}
			}
			br.newLine();

		}
		return procesos;
	}

}
