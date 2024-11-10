package Barrera;

public class Hilo implements Runnable {

	/*
	 * Implementa un método para sincronizar N threads: los thread que llaman este
	 * método se paran a la espera de que otros threads lo llamen. Cuando N threads
	 * han llamado al método, se permite a todos seguir adelante.
	 * 
	 */

	private Object monitor;
	private int metodosLanzados;

	public Hilo(Object monitor, int metodosLanzados) {
		super();
		this.monitor = monitor;
		this.metodosLanzados = metodosLanzados;
	}

	@Override
	public void run() {
		while (metodosLanzados < 5)
			try {
				ejecutaMetodo();
				System.out.println("--------------------" + metodosLanzados);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		System.out.println(Thread.currentThread().getName() + " continua");
	}

	private synchronized void ejecutaMetodo() throws InterruptedException {
		metodosLanzados++;
		System.out.println(Thread.currentThread().getName() + " esperando");
		synchronized (monitor) {
			if(metodosLanzados == 5)
				monitor.notify();
			else
				monitor.notifyAll();
			
			monitor.wait();
		}
		

		System.out.println(Thread.currentThread().getName() + " despertado");

	}

	public static void main(String[] args) {

		Object o = new Object();
		int metodosLanzados = 0;
		for (int i = 0; i < 5; i++) {
			new Thread(new Hilo(o, metodosLanzados), "T " + i + "-").start();
		}

	}

}
