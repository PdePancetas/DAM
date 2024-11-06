package beansPedido;

public class Producto {

	private String descripcion;
	private double precio;
	private int cantidad;

	public String getDescripcion() {
		return descripcion;
	}

	public Producto() {
		super();
	}

	public Producto(String descripcion, double precio, int cantidad) {
		super();
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
