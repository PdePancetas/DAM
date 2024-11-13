package Timer;

public class Escritor implements Runnable {

	private Timer reloj;

	@Override
	public void run() {
		while (true) {
			synchronized (reloj) {

				try {
					reloj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(Thread.currentThread().getName() + " hola");

			}

		}

	}

	public Escritor(Timer contador) {
		super();
		this.reloj = contador;
	}

}
