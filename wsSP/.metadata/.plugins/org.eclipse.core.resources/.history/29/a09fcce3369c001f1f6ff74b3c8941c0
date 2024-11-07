package Consumidor_Productor;

public class Productor implements Runnable {

	private Productos productos;

	public Productor(Productos productos) {
		super();
		this.productos = productos;
	}

	public Productor() {
		super();
	}

	@Override
	public void run() {

		while (true) {
			synchronized (productos) {
				if (productos.getProductos().isEmpty()) {

					System.out.println(Thread.currentThread().getName() + ": Reponiendo productos");

					for (int i = 1; i <= 10; i++)
						productos.getProductos().add(new Producto());
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
