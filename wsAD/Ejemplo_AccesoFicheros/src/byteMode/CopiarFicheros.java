package byteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import utilidadesTeclado.Teclado;

public class CopiarFicheros {

	public static void main(String[] args) {

		System.out.print("Fichero origen: ");
		String s1 = Teclado.leerCadena();
		System.out.println("Fichero destino: ");
		String s2 = Teclado.leerCadena();

		try {
			copyFile(s1, s2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void copyFile(String s1, String s2) throws IOException {
		File f2 = new File(s2);

		FileInputStream fis = new FileInputStream(s1);
		FileOutputStream fos = new FileOutputStream(s2, true);
		

		if (f2.exists()) {
			System.out.print("El fichero existe.Desea sobrescribir?(y/n) ");
			if (Teclado.leerCadena().equals("n")) {
				fis.close();
				fos.close();
				return;
			}
		}
		int n;
		while ((n = fis.read()) != -1)
			fos.write(n);
		fis.close();
		fos.close(); 
	}

}
