package ejemplosFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.DataFormatException;

import utilidadesTeclado.Teclado;

public class AccesoFicherosTexto {

	public static void main(String[] args) {

//		System.out.print("Nombre del directorio: ");
//		String directorio = Teclado.leerCadena();// Ruta absoluta
//		File f = new File(directorio);
//		System.out.print("Nombre del fichero: ");
//		String nombre = Teclado.leerCadena();// Ruta absoluta
//		showProperties(f);
//		System.out.println("-------------------------");
//		try {
//			System.out.println(listarDirectorio(f, ""));
//			System.out.println("Existe?:" + existeArchivo(f, nombre));
//		} catch (NoDirectoryException e) {
//			e.printStackTrace();
//		}

//		ejemploJavaNIO();

//		System.out.print("Caracter a eliminar: ");
//		String c = Teclado.leerCadena();
//		char caracter = c.charAt(0);
//
//		try {
//			File f = eliminarCaracter(new File("ficheroPrueba"), caracter);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Caracter eliminado");

		System.out.print("Usuario a eliminar: ");
		String n = Teclado.leerCadena();

		try {
			deleteUsuario(new File("usuarios"), n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		System.out.println("Usuario eliminado");
	}

	/**
	 * Metodo que dado un fichero muestre sus propiedades, si no existe lo indicará.
	 */

	private static void showProperties(File f) {
		if (!f.exists())
			System.out.println("El fichero no existe");
		else {
			System.out.println(f.canExecute() ? "Puede ejecutarse" : "No puede ejecutarse");
			System.out.println("El fichero existe");
			System.out.println("El fichero pesa " + f.length() + " bytes");
		}
	}

	/**
	 * 
	 * @param directorio Ruta del sistema de archivos absoluta
	 * @return Una cadena con el listado de todo el contenido
	 * @throws DataFormatException si el parámetro no es un directorio
	 */
	private static String listarDirectorio(File directorio, String tab) throws NoDirectoryException {
		String s = "";
		if (!directorio.isDirectory())
			throw new NoDirectoryException();

		for (File f : directorio.listFiles())

			if (f.isDirectory())
				s += tab + "-" + f.getName() + "\n" + listarDirectorio(f, tab + "\t");
			else
				s += tab + "-" + f.getName() + "\n";

		return s;

	}

	/**
	 * 
	 * @param directorio La ruta de un directorio
	 * @param nomArchivo Nombre del archivo a buscar
	 * @return true si nomArchivo esta en directorio (incluyendo subcarpetas), false
	 *         si no
	 * @throws NoDirectoryException
	 */
	
	private static void ejemploJavaNIO() {
		// clases Path, Paths y Files
		Path p = Paths.get("ficheroPrueba");
		List<String> lineas = null;
		try {
			lineas = Files.readAllLines(p);
		} catch (IOException e) {
			System.out.println("No se pudieron obtener los datos");
			e.printStackTrace();
		}

		if (lineas != null)
			for (String s : lineas)
				System.out.println(s);
	}

	/**
	 * Método que elimina un caracter de un archivo de texto
	 * 
	 * @param nomArchivo nombre archivo
	 * @param caracter   Caracter a eliminar
	 * @return f El archivo modificado
	 * @throws IOException
	 */
	private static File eliminarCaracter(File nomArchivo, char caracter) throws IOException {

		File f = new File("bbb");

		FileReader fr = new FileReader(nomArchivo);
		FileWriter fw = new FileWriter(f);

		int l;
		while ((l = fr.read()) != -1)
			if ((char) l != caracter)
				fw.write(l);

		fr.close();
		fw.close();

		nomArchivo.delete();
		f.renameTo(nomArchivo);

		return f;
	}

	private static void deleteUsuario(File usuarios, String nombre) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(usuarios));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("temp")));

		String l;
		while ((l = br.readLine()) != null)
			for (String s : l.split(";"))
				if (s.equals(nombre))
					bw.write(l + "\n");

		br.close();
		bw.close();

		usuarios.delete();
		new File("temp").renameTo(usuarios);

	}

}
