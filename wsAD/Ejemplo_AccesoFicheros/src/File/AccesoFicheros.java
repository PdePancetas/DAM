package File;

import java.io.File;

public class AccesoFicheros {

	public static void main(String[] args) {

		/* CLASES RELACIONADAS */
		// FILE - Los objetos File guardan referencias a una ruta de nuestro sistema de
		// archivos
		// Ruta absoluta
		File ruta = new File("C:\\Users\\Alumno\\Desktop\\Prueba Ficheros\\Fichero.txt");

		if (ruta.exists())
			System.out.println("LA ruta existe y es " + ruta.getAbsolutePath());
		if (ruta.isDirectory())
			System.out.println("La ruta es un directorio");
		else
			System.out.println("La ruta es un fichero");
		System.out.println("Tamaño: " + ruta.length());

		// Ruta relativa a la raiz del proyecto:
		File ruta2 = new File("ficheros\\fichero.txt");
		System.out.println(ruta2.getParent());
		if (ruta2.exists())
			System.out.println("LA ruta existe y es " + ruta2.getAbsolutePath());
		if (ruta2.isDirectory())
			System.out.println("La ruta es un directorio");
		else
			System.out.println("La ruta es un fichero");
		System.out.println("Tamaño: " + ruta2.length());

		/**
		 * Recibe un array de ficheros y devuelve el que tenga mas tamaño.
		 */
		File f = FicheroMayor((new File("C:\\Users\\Alumno")).listFiles());
		System.out.println(f.getName() + " en " + f.getParent());

		listarDirectorio(new File("."));

		System.out.println("*****************************");

		listarDirectorioRecursivo("",new File("C:\\Users\\Alumno"));
	}

	/**
	 * Recibe un array de ficheros y devuelve el que tenga mas tamaño.
	 */
	private static File FicheroMayor(File[] files) {
		File fichero = files[0];
		for (File f : files)
			if (f.length() > fichero.length())
				fichero = f;
		return fichero;
	}

	/**
	 * Listar directorio
	 * 
	 * @param directorio
	 */
	private static void listarDirectorio(File dir) {
		File[] contenido = dir.listFiles();
		for (File f : contenido)
			System.out.println(f.getName() + (f.isDirectory() ? ", D" : ", F"));
	}

	/**
	 * Muestra todo el arbol de ese directorio
	 * @param dir
	 */
	private static void listarDirectorioRecursivo(String s, File dir) {
		File[] contenido = dir.listFiles();
		for(File f: contenido)
			if(f.isFile())
				System.out.println(s+f.getName());
			else if(f.isDirectory()) {
				System.out.println(s+f.getName());
				listarDirectorioRecursivo(s+"\t",f);
			}
				
		
	}
}
