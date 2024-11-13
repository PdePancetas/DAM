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

	public int getMetodosLanzados() {
		return metodosLanzados;
	}

	public void setMetodosLanzados(int metodosLanzados) {
		this.metodosLanzados = metodosLanzados;
	}

	public Hilo(Object monitor, int metodosLanzados) {
		super();
		this.monitor = monitor;
		this.metodosLanzados = metodosLanzados;
	}

	@Override
	public void run() {
		while (metodosLanzados < 20)
			try {
				ejecutaMetodo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " continua");
	}

	private void ejecutaMetodo() throws InterruptedException {
		metodosLanzados++;
		System.out.println(Thread.currentThread().getName() + " esperando");
		synchronized (monitor) {
			if (metodosLanzados < 20) {
				monitor.notifyAll();
				monitor.wait();
				System.out.println(Thread.currentThread().getName() + " despertado");
			}

		}

		

	}

	public static void main(String[] args) {

		Object o = new Object();
		int metodosLanzados = 0;
		for (int i = 0; i < 10; i++) {
			new Thread(new Hilo(o, metodosLanzados), "T " + i + "-").start();
		}

	}

}
