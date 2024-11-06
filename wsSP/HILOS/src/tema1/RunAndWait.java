package tema1;

import java.io.IOException;
import java.util.Arrays;

public class RunAndWait {

	public static void main(String[] args) {
		
		try {
			Process p = new ProcessBuilder(args).start();
			p.waitFor();
			
			System.out.println("Comando utilizado: "+Arrays.toString(args));
			System.out.println("Valor de retorno del hijo: "+p.exitValue());
		} catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
