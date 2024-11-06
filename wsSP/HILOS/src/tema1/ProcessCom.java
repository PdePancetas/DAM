package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessCom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProcessBuilder pb = new ProcessBuilder("ping", "www.google.com");
		
		try {
			long initTime = System.currentTimeMillis();
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("Datos del ping:\n______________________________________");
			String l;
			while ((l = br.readLine()) != null)
				System.out.println(l);
			
			
			long finTime = System.currentTimeMillis();
			
			System.out.println("______________________________________\nTiempo transcurrido: "+ (finTime-initTime));
		} catch (IOException e) {
			System.out.println("Error I/O: " + e);
			e.printStackTrace();
		}
	}
}
