package ficheros;

import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Ficheros {

	public static HashMap<String, String> ficheros = new HashMap<>();

	public static void newFichero(String nombreArchivo, String text) {
		ficheros.put(nombreArchivo, text);
	}

	public static void saveFichero(String nombreArchivo, String text) {
		ficheros.replace(nombreArchivo, text);
	}

	public static boolean existsFichero(String nombreArchivo) {
		if (ficheros.containsKey(nombreArchivo)) {
			return true;
		}
		return false;
	}

	public static void saveAsFichero(String nombreAnt, String nombreNuevo, String text) {
		ficheros.remove(nombreAnt);
		Ficheros.newFichero(nombreNuevo, text);
	}

	public static String openFichero(String nombreArchivo) {

		if (ficheros.containsKey(nombreArchivo))
			return ficheros.get(nombreArchivo);

		return null;
	}

	public static int calcularTamaño(String fichero) {
		return Ficheros.openFichero(fichero).replace("\r\n|\r|\n|\t","").length();
	}

	public static int calcularNumLineas(String fichero) {
		int numLineas = Ficheros.openFichero(fichero).split("\r\n|\r|\n").length;
		return numLineas;
	}

	public static String selecMay(String fichero, String selectedText) {
		return selectedText.toUpperCase();
	}

	public static String selecMin(String fichero, String selectedText) {
		return selectedText.toLowerCase();
	}
}
