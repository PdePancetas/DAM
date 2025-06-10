package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Extractor {
	
	private static final File ocurrencias = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\OcurrenciasTotalesBD.txt");
	private static final File ocurrencias_sin_espacio = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\ocurrencias_sin_espacio.txt");
	private static final File ocurrencias_1_espacio = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\ocurrencias_1_espacio.txt");
	private static final File ocurrencias_web = new File("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\ocurrencias_web.txt");
	

	public static void main(String[] args) throws IOException {
		main2(args);
		
/*
		BufferedReader br = new BufferedReader(new FileReader(ocurrencias));
//		BufferedWriter bw = new BufferedWriter(new FileWriter(ocurrencias_sin_espacio));
		
//		bw.write("Ocurrencias de 'SUBSTR('");
//		bw.newLine();
//		bw.write("----------------------------");
		
		String linea;
		String clase;
		while((linea = br.readLine()) != null) {
			if(linea.startsWith("Find 'substr' in ")) {
				clase = linea.substring(64, linea.length()-45);
				System.out.println(clase);
//				bw.newLine();
//				bw.write(clase);
			}
		}
		
		br.close();
//		bw.close();
		*/
	}
	
	public static void main2(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(ocurrencias));
//		BufferedWriter bw = new BufferedWriter(new FileWriter(ocurrencias_sin_espacio));
		
//		bw.write("Ocurrencias de 'SUBSTR('");
//		bw.newLine();
//		bw.write("----------------------------");
		
		String linea;
		String clase;
		int contTrig=0;
		int contFunc=0;
		int contVistas=0;
		int contProc=0;
		
		while((linea = br.readLine()) != null) {
			
			if(linea.startsWith("funciones")) {
				contFunc++;
			} else if(linea.startsWith("triggers"))
				contTrig++;
			else if (linea.startsWith("vistas")) {
				contVistas++;
			}else if(linea.startsWith("procedimientos"))
				contProc++;
		}
		
		System.out.println("Funciones: "+contFunc);
		System.out.println("Triggers: "+contTrig);
		System.out.println("Vistas: "+contVistas);
		System.out.println("Procedimientos: "+contProc);
		
		
		br.close();
//		bw.close();
		
	}
}
