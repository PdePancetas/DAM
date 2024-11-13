package Lector_Escritor;

public class Escritor implements Runnable {

	private Recurso recCompartido;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int cant = (int) (Math.random() * 10) + 1;
			synchronized (recCompartido) {
				System.out.print(Thread.currentThread().getName() + "Modificando datos... Añadiendo " + cant);
				recCompartido.setDatos(recCompartido.getDatos() + cant);
				System.out.println(" Datos : " + recCompartido.getDatos());

				recCompartido.notifyAll();
				try {
					recCompartido.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public Escritor(Recurso recCompartido) {
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
