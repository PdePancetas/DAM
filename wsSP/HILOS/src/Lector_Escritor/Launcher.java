package Lector_Escritor;

import java.util.ArrayList;

public class Launcher {

	public static void main(String[] args) {

		Recurso r = new Recurso();
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		hilos.add(new Thread(new Escritor(r), "Escritor - "));
		for (int i = 0; i < 3; i++) {
			hilos.add(new Thread(new Lector(r), "Lector " + i + " - "));
		}

		for (Thread t : hilos)
			t.start();
	}

}
