package Lector_Escritor;

public class Lector implements Runnable {

	private Recurso recCompartido;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (recCompartido) {
				System.out.println(Thread.currentThread().getName() + "leyendo dato: " + recCompartido.getDatos());
			}
		}

	}

	public Lector(Recurso recCompartido) {
		super();
		this.recCompartido = recCompartido;
	}

	public Recurso getRecCompartido() {
		return recCompartido;
	}

	public void setRecCompartido(Recurso recCompartido) {
		this.recCompartido = recCompartido;
	}

}
