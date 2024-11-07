package Consumidor_Productor;

public class Productor implements Runnable {

	private Productos productos;

	public Productor(Productos productos) {
		this.productos = productos;
		this.productos.setProductos(productos.getProductos());
	}

	public Productor() {
		super();
	}

	@Override
	public void run() {

		while (true) {
			if (!productos.hayProductos()) {
				for (int i = 1; i <= 10; i++)
					productos.getProductos().add(new Producto(i));
				System.out.println(Thread.currentThread().getName()+" reponiendo 10 productos");
			}
			
		}

	}

}
