package Consumidor_Productor;

import java.util.ArrayList;

public class Lanzador {

	public static void main(String[] args) {

		Productos productos = new Productos(10);
		Productor productor = new Productor(productos);
		ArrayList<Thread> consumidores = new ArrayList<>();
		Consumidor c = new Consumidor();
		Thread thread = null;
		for (int i = 0; i < 5; i++) {
			thread = new Thread(c);
			consumidores.add(thread);
		}

		Thread p = new Thread(productor);
		p.start();
		for(Thread t: consumidores)
			t.start();
		

	}

}
