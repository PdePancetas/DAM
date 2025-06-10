package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExtractorBD {
	
	private static final File ocurrencias = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\Ocurrencias.txt");
	private static final File ocurrencias_sin_espacio = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\ocurrencias_sin_espacio.txt");
	private static final File ocurrencias_1_espacio = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\ocurrencias_1_espacio.txt");

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(ocurrencias));
		BufferedWriter bw = new BufferedWriter(new FileWriter(ocurrencias_sin_espacio));
		
		bw.write("Ocurrencias de 'SUBSTR('");
		bw.newLine();
		bw.write("----------------------------");
		
		String linea;
		String clase;
		while((linea = br.readLine()) != null) {
			if(linea.startsWith("Buscar 'SUBSTR(' en ")) {
				clase = linea.substring(44, linea.length()-2);
				System.out.println(clase);
				bw.newLine();
				bw.write(clase);
			}
		}
		
		br.close();
		bw.close();
		
	}
}
