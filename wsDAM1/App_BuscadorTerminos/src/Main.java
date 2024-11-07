import java.io.File;
import java.io.IOException;

import repository.Ops;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		
		File f = new File("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Kerbal Space Program\\GameData");
		listarDirectorioRecursivo("hud", f);
	}

	
	private static void listarDirectorioRecursivo(String s, File dir) throws IOException {
		File[] contenido = dir.listFiles();
		for(File f: contenido)
			if(f.isFile())
				if(Ops.buscarTermino(f, s))
					System.out.println(s+f.getAbsolutePath());
			else if(f.isDirectory()) {
				listarDirectorioRecursivo(s+"\t",f);
			}
				
		
	}
}
