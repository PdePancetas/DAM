package logic;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		JSONArray notas = Func.leerFicheroJSON(new File("notas.json"));
		System.out.println(notas);
		
//		MOSTRAR DATOS DE NOTAS
		for(int i=0;i<notas.length();i++) {
			JSONObject alumno = notas.getJSONObject(i);
			String nombre  = alumno.getString("nombre");
			System.out.println("Alumno: "+nombre);
			JSONArray notasAlumno = alumno.getJSONArray("notas");
			for(int j=0;j<notasAlumno.length();j++) {
				JSONObject nota = notasAlumno.getJSONObject(j);
				System.out.println("   "+nota.get("materia")+": "+nota.getInt("nota"));
			}
				
		}
		System.out.print("Nombre de alumno: ");
		String nombre = Teclado.leerCadena();
		System.out.println("Nota media de "+nombre+": "+Func.mostrarNotaMedia(nombre));
	}

	
	
}
