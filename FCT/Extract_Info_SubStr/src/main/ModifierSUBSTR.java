package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifierSUBSTR {
//	 regex = "(?<!\\.)substr(?!I)"; 

	private static String input_clases;
	private static String output_Clase;
	private static String directorio_Busqueda;
	private static String directorio_Escritura;
	private static String regex;
	private static String sustitute;
	private static Pattern pattern;
	private static List<String> codUTF8 = new ArrayList<String>();
	private static List<String> codISO_8859_1 = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		parseArgs(args);

		List<String> clases = Files.readAllLines(Paths.get(input_clases));
		Entry<Integer, Boolean> resultado;
		for (String nombreClase : clases) {
			resultado = refactorClass(nombreClase);
			if (resultado.getValue())
				System.out.println(
						"Clase procesada: " + nombreClase + ", modificadas " + resultado.getKey() + " lineas.");
			else
				System.out.println("No se han encontrado ocurrencias en " + nombreClase);
		}

		System.out.println("\n*****************************UTF8**************************");
		codUTF8.stream().sorted().forEach(System.out::println);
		System.out.println("***********************************************************");
		System.out.println("\n***************************ISO_8859_1****************************");
		codISO_8859_1.stream().sorted().forEach(System.out::println);
		System.out.println("*****************************************************************");
	}

	private static void parseArgs(String[] args) {
		int posRegex = 0;
		for (int i = 0; i < args.length; i++) {

			if (args[i].equalsIgnoreCase("-inputFile"))
				input_clases = args[i + 1];

			if (args[i].equalsIgnoreCase("-searchDir"))
				directorio_Busqueda = args[i + 1];

			if (args[i].equalsIgnoreCase("-writeDir"))
				directorio_Escritura = args[i + 1];

			if (args[i].equalsIgnoreCase("-regex")) {
				regex = args[i + 1];
				posRegex = i;
			}
			
			if(args[i].equalsIgnoreCase("-sustitute")) {
				sustitute = args[i+1];
			}

			if (regex != null && args[i].equalsIgnoreCase("-case")) {
				if (args[i + 1].equalsIgnoreCase("noSensitive"))
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
				else if (args[i + 1].equalsIgnoreCase("sensitive"))
					pattern = Pattern.compile(regex);
			}
		}
		
		if (pattern == null) {
			pattern = Pattern.compile(args[posRegex+1], Pattern.CASE_INSENSITIVE);
		}

	}

	private static Entry<Integer, Boolean> refactorClass(String nombreClase) throws IOException {
		Entry<Integer, Boolean> resultado;
		List<String> lineasClase;
		output_Clase = directorio_Escritura + nombreClase;
		File outFile = new File(output_Clase);
		if (!outFile.getParentFile().exists()) {
			if (!outFile.getParentFile().mkdirs()) {
				throw new IOException("No se pudo crear el directorio: " + outFile.getParentFile().getAbsolutePath());
			}
		}

		outFile.createNewFile();
		BufferedWriter bw;
		try {
			lineasClase = Files.readAllLines(Paths.get(directorio_Busqueda + nombreClase), StandardCharsets.ISO_8859_1);
			bw = new BufferedWriter(new FileWriter(directorio_Escritura + nombreClase, StandardCharsets.ISO_8859_1));
			codISO_8859_1.add(nombreClase);
		} catch (IOException e) {
			lineasClase = Files.readAllLines(Paths.get(directorio_Busqueda + nombreClase), StandardCharsets.UTF_8);
			bw = new BufferedWriter(new FileWriter(directorio_Escritura + nombreClase, StandardCharsets.UTF_8));
			codUTF8.add(nombreClase);
		}

		int numOcurrencias = 0;
		Matcher matcher;
		for (String linea : lineasClase) {
			matcher = pattern.matcher(linea);
			if (matcher.find()) {
				linea = matcher.replaceAll(sustitute);
				numOcurrencias++;
			}
			bw.write(linea);
			bw.newLine();
		}
		bw.close();

		if (numOcurrencias != 0)
			resultado = Map.entry(numOcurrencias, true);
		else {
			resultado = Map.entry(numOcurrencias, false);
			outFile.delete();
		}
		return resultado;
	}
}
