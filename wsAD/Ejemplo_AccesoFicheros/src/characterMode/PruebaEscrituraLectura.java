package characterMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utilidadesTeclado.Teclado;

public class PruebaEscrituraLectura {

	public static void main(String[] args) throws IOException {

		/*
		 * Se puede escribir un archivo con modo Byte (FileInputStream para leer y FileOutputStream para escribir) o modo Caracter (File Reader para
		 * leer y File Writer para escribir)
		 * 
		 */
		File f = new File(".\\ficheros");
		if ((f = writeFile()) != null)
			System.out.println("Se ha creado archivo de nombre " + f.getName());
		System.out.println(readFile(f));

		System.out.println("Numero de lineas en " + f.getName() + ": " + contarLineas(f));
		File f2 = new File(".\\ficheros");
		if ((f2 = writeFile()) != null)
			System.out.println("Se ha creado archivo de nombre " + f2.getName());
		System.out.println(readFile(f2));

		File f3 = new File(".\\ficheros");
		f3 = concatenar(f, f2);
		System.out.println(f3.getName() + "\n" + readFile(f3));

		File f4 = new File(".\\ficheros");
		f4 = intercalarLineas(f, f2);
		System.out.println(f4.getName());
	}

	/**
	 * 
	 * @param cad
	 * @return Devuelve un objeto file con el texto q se solicita al usuario
	 * @throws IOException
	 */
	private static File writeFile() throws IOException {
		System.out.println("Introduce nombre del fichero: ");
		String nombre = Teclado.leerCadena();
		FileWriter fw = new FileWriter(nombre, true);
		System.out.println("Introduce texto (fin para terminar): ");
		String l = "";
		while (!l.equals("fin")) {
			l = Teclado.leerCadena();
			fw.write((l.equals("fin") ? "" : l + " \n"));
		}

		fw.close();

		return new File(nombre);
	}

	private static String readFile(File nomFich) throws IOException {
		FileReader fr = new FileReader(nomFich);
		int c;
		String texto = "";
		while ((c = fr.read()) != -1) {
			texto += (char) c;
		}
		fr.close();

		return texto;
	}

	/**
	 * Devuelve el numero de lineas del fichero
	 * 
	 * @param f
	 * @return Devuelve el numero de lineas del fichero
	 * @throws IOException
	 */
	private static Integer contarLineas(File f) throws IOException {

		int numLineas = readFile(f).split("\r\n|\r|\n").length;

		return numLineas;
	}

	/**
	 * 
	 * @param f1 Primer fichero
	 * @param f2 Segundo fichero
	 * @return un fichero cuyo contenido es la concantenacion de los anteriores
	 * @throws IOException
	 */
	private static File concatenar(File f1, File f2) throws IOException {

		String t1 = readFile(f1);
		String t2 = readFile(f2);

		FileWriter fw = new FileWriter(f1.getName() + "&" + f2.getName(), true);

		fw.write(t1 + t2);

		fw.close();

		return new File(f1.getName() + "&" + f2.getName());
	}

	/**
	 * 
	 * @param f1 Primer fichero
	 * @param f2 Segundo fichero
	 * @return un fichero cuyo contenido es la concantenacion de los anteriores
	 *         intercalando sus lineas
	 * @throws IOException
	 */
	private static File intercalarLineas(File f1, File f2) throws IOException {

		FileReader fr1 = new FileReader(f1);
		FileReader fr2 = new FileReader(f2);
		FileWriter fw = new FileWriter(f1.getName() + " intercalado con " + f2.getName());

		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);
		String l1;
		String l2;
		while ((l1 = br1.readLine())!=null && (l2 = br2.readLine())!=null) {
			fw.write(l1 + "\n" + l2 + "\n");
		}
		
		if(l1==null)
			while((l2 = br2.readLine())!=null)
				fw.write(l2);
		else
			while ((l1 = br1.readLine())!=null)
				fw.write(l1);
			
		

		fw.close();
		br1.close();
		br2.close();

		return new File(f1.getName() + " intercalado con " + f2.getName());
	}

}
