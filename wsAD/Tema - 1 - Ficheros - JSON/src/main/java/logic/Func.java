package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Func {

	public static JSONArray leerFicheroJSON(File f) throws IOException {

		String texto = "";
		String linea;
		InputStream is = Main.class.getClassLoader().getResourceAsStream("notas.json");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((linea = br.readLine()) != null)
				texto += linea;
		}

		JSONArray notas = new JSONArray(texto);

		return notas;
	}

	public static double mostrarNotaMedia(String nombreAlumno) throws IOException {
		double sumaNotas = 0;
		int cont=0;
		JSONArray notas = Func.leerFicheroJSON(new File("notas.json"));
		
		for(int i=0;i<notas.length();i++) {
			JSONObject alumno = notas.getJSONObject(i);
			JSONArray notasAlumno = alumno.getJSONArray("notas");
			if(alumno.get("nombre").equals(nombreAlumno)) {
				for(int j=0;j<notasAlumno.length();j++) {
					JSONObject nota = notasAlumno.getJSONObject(j);
					sumaNotas+= nota.getInt("nota");
					cont++;
				}
			}

		}
		
		
		return sumaNotas/cont;
	}
}
