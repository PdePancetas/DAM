package Barrera;

public class Hilo implements Runnable {

	/*
	 * Implementa un método para sincronizar N threads: los thread que llaman este
	 * método se paran a la espera de que otros threads lo llamen. Cuando N threads
	 * han llamado al método, se permite a todos seguir adelante.
	 * 
	 */

	private Monitor monitor;

	public Hilo(Monitor monitor) {
		super();
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (monitor.getMetodosLanzados() < 20)
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
		monitor.setMetodosLanzados(monitor.getMetodosLanzados() + 1);
		System.out.println(Thread.currentThread().getName() + " esperando");
		synchronized (monitor) {
			if (monitor.getMetodosLanzados() < 20) {
				monitor.wait();
				System.out.println(Thread.currentThread().getName() + " despertado");
			} else
				monitor.notifyAll();

		}

	}

	public static void main(String[] args) {

		int metodosLanzados = 0;
		Monitor o = new Monitor(metodosLanzados);

		for (int i = 0; i < 20; i++) {
			new Thread(new Hilo(o), "T " + i + "-").start();
		}

	}

}
