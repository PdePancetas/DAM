package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProcessComReverse {

	public static void main(String[] args) {

		 
		try {
			Process p = new ProcessBuilder("java", "-cp", ".\\bin", "tema1.ProcessComSuma").start();
			
			PrintStream ps = new PrintStream(p.getOutputStream(),true);
			ps.println(20);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String l;
			while ((l = br.readLine()) != null)
				System.out.println(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
