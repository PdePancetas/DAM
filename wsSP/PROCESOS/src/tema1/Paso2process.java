package tema1;

import java.io.File;
import java.io.IOException;

public class Paso2process {

	public static void main(String[] args) {

		ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".\\bin", "tema1.Ejercicio1");
		try {
			pb.redirectOutput(new File("resultado.txt"));
			pb.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
