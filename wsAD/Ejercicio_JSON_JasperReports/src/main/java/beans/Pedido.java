package beans;

import java.util.LinkedList;

public class Pedido {

	private int id;
	private LinkedList<Producto> productos = new LinkedList<>();

	public Pedido(int id, LinkedList<Producto> productos) {
		super();
		this.setId(id);
		this.productos = productos;
	}

	public Pedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}

}
