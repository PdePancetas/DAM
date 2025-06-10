package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Modifier {

	private static final File output_file = new File(
			"C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\resultadoPorPrioridades.txt");

	public static void main(String[] args) throws IOException {

		List<String> clasesMT = Files.readAllLines(
				Paths.get("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\resultadoMTTareas.txt"));

		List<String> clases = Files.readAllLines(
				Paths.get("C:\\Users\\miguel_blanco\\Desktop\\CAMBIOS_FUNCION_SUBSTR\\resultado.txt"));
				
		List<String> nombresClases = clases.stream().
				filter(line -> line.startsWith("cil"))
				.map(l -> l = l.split(",")[0]).collect(Collectors.toList());
		
		
		
		for(int i=0;i<clasesMT.size();i++) {
			for(int j=0;j<nombresClases.size();j++)
				if(clasesMT.get(i).equals(nombresClases.get(j)))
					nombresClases.remove(j);
		}
		List<String> clasesNoMT = new ArrayList<>(nombresClases);
		System.out.println("Clases en MT_Tareas : "+clasesMT.size());
		System.out.println("Clases : "+clases.size());
		System.out.println("Clases no en MT_Tareas : "+clasesNoMT.size());
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(output_file))) {
			bw.write("CLASES A REVISAR");
			bw.newLine();
			bw.newLine();
			
			clasesMT.stream().sorted().forEach(l -> {
				try {
					bw.write(l);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			bw.newLine();
			clasesNoMT.stream().sorted().forEach(l -> {
				try {
					bw.write(l);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		

	}
}
