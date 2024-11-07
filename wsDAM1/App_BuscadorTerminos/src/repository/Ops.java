package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ops {

	public static boolean buscarTermino(File f, String termino) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea;
		while((linea = br.readLine())!=null)
			if (linea.contains(termino))
				return true;
		return false;

	}

	public static String readFile(File nomFich) throws IOException {
		FileReader fr = new FileReader(nomFich);
		int c;
		String texto = "";
		while ((c = fr.read()) != -1) {
			texto += (char) c;
		}
		fr.close();

		return texto;
	}

	public static String writeContent(File f) throws IOException {
		return readFile(f);
	}

	
	
}
