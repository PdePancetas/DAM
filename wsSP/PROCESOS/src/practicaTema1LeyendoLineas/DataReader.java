package practicaTema1LeyendoLineas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class DataReader {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(
				new FileReader(new File(".//src//practicaTema1LeyendoLineas//data.dat")));

		String linea;
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		br.close();

	}

	public static int conteoLineas(File f) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(f));

		String linea;

		int cont = 0;
		while ((linea = br.readLine()) != null) {
			cont++;

		}
		return cont;
	}

}
