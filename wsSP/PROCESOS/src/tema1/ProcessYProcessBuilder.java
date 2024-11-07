package tema1;
import java.io.IOException;

public class ProcessYProcessBuilder {
	
	void main() throws IOException {
		
		ProcessBuilder pb = new ProcessBuilder();
		Process p = pb.start();
		
		/*	PARA COMPROBAR
		 
		File datos = new File("datos.txt");
		File errores = new File("error.txt");
		
		pb.redirectOutput(datos); //Redirecciona lo que haga el proceso en el archivo datos
		pb.redirectError(errores); //Redirecciona los errores que ocurran en el proceso en errores
		*/
	}
	
}
