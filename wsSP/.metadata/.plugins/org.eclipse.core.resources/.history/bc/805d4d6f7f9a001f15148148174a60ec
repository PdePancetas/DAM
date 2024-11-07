package Consumidor_Productor;

public class Consumidor implements Runnable {

	private Productos productos;
	boolean seEstaComprando;

	@Override
	public void run() {

		while (true) {
			seEstaComprando = false;

			if (!productos.getProductos().isEmpty()) {
				seEstaComprando = true;
			}
			if (seEstaComprando) {
				int cant = productos.getProductos().size();
				try {
					Thread.sleep(999);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (productos.getProductos().size() == cant) {
					comprar();
				}
			} else
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

	}

	public synchronized void comprar() {
		if (!productos.getProductos().isEmpty()) {
			productos.getProductos().removeFirst();
			System.out.println(Thread.currentThread().getName() + " - Ha comprado un producto. Quedan " + productos.getProductos().size()
					+ " productos.");
		}

	}

	public Consumidor(Productos productos) {
		super();
		this.productos = productos;
	}
}
