package model;

public class Plato {

	private String nombre;
	private double precio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Plato(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public Plato() {
		super();
	}

	@Override
	public String toString() {
		return "Plato [nombre=" + nombre + ", precio=" + precio + "]";
	}

}
