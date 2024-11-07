package contarVocales;

import java.io.IOException;
import java.util.ArrayList;

public class ContarVocales implements Runnable {

	private ContadorVocal cv;
	private char caracter;

	public static void main(String[] args) throws IOException, InterruptedException {

//		String texto = new String(Files.readAllBytes(new File("Texto.txt").toPath()));

		String texto = "aaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaei"
				+ "uoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuo"
				+ "iaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoia"
				+ "oiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoi"
				+ "uaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuae"
				+ "iuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuo"
				+ "iaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoiaoiuaeiuoi";

		ArrayList<Thread> threads = new ArrayList<>();

		ContadorVocal v = new ContadorVocal(texto);
		threads.add(new Thread(new ContarVocales('a', v)));
		threads.add(new Thread(new ContarVocales('e', v)));
		threads.add(new Thread(new ContarVocales('i', v)));
		threads.add(new Thread(new ContarVocales('o', v)));
		threads.add(new Thread(new ContarVocales('u', v)));

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();
		System.out.println("hay " + v.getVocales() + " vocales");
	}

	public ContarVocales(char caracter, ContadorVocal cv) {
		super();
		this.caracter = caracter;
		this.cv = cv;
	}

	@Override
	public void run() {

		for (int i = 0; i < cv.getTexto().length(); i++) {
			if (cv.getTexto().charAt(i) == caracter)
				cv.addVocal();
		}

	}

}
