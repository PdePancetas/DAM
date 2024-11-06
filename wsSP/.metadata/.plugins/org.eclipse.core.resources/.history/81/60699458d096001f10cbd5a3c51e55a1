package Consumidor_Productor;

import java.util.ArrayList;

public class Productos {

	private ArrayList<Producto> productos = new ArrayList<>();
	private boolean hayProducto;

	public Productos(ArrayList<Producto> productos) {
		super();
		this.productos = productos;
	}

	public Productos(int n) {
		super();
		for (int i = 1; i <= n; i++) {
			productos.add(new Producto(i));
		}
	}

	public Productos() {
		super();
	}

	public boolean getHayProducto() {
		return hayProducto;
	}

	public void setHayProducto(boolean hayProducto) {
		this.hayProducto = hayProducto;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public boolean hayProductos() {

		if (productos.size() > 0)
			return true;

		return false;
	}

}
