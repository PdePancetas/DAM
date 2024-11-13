package Timer;

public class Timer implements Runnable {

	private Integer contador;

	@Override
	public void run() {

		long actual = System.nanoTime();
		while (true) {
			while (System.nanoTime() - actual < 1000000000l) {}
			synchronized (this) {
				System.out.println(Thread.currentThread().getName()+" tick: "+System.nanoTime());
				contador++;
				notifyAll();
				
			}
			actual = System.nanoTime();
		}
	}

	public Timer(Integer contador) {
		super();
		this.contador = contador;
	}

}
