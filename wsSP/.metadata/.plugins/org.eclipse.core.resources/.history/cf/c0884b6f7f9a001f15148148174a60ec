package Consumidor_Productor;

import java.util.ArrayList;

public class Lanzador {

	public static void main(String[] args) throws InterruptedException {

		Productos productos = new Productos();
		Productor productor = new Productor(productos);
		ArrayList<Thread> consumidores = new ArrayList<>();
		Consumidor c = new Consumidor(productos);
		Thread thread = null;
		for (int i = 1; i <= 10; i++) {
			thread = new Thread(c, "Consumer: " + i);
			consumidores.add(thread);
		}

		Thread p = new Thread(productor, "Producer");
		p.start();

		for (Thread t : consumidores)
			t.start();

	}

}
