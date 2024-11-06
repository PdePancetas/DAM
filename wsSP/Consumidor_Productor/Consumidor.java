package Consumidor_Productor;

public class Consumidor implements Runnable {

	private Productos productos;

	public Consumidor(Productos productos) {
		super();
		this.productos = productos;
	}

	public Consumidor() {
		super();
	}

	@Override
	public void run() {

		while (true) {
			while (productos.hayProductos()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (productos.getHayProducto()) {
					productos.getProductos().remove(productos.getProductos().getLast());
					productos.setHayProducto(true);
					System.out.println(Thread.currentThread().getName()+" ha comprado un producto, quedan "+productos.getProductos().size()+" productos");
					
				}
			}
			productos.setHayProducto(false);
		}
	}

}
