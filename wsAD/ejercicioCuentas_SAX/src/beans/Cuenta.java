package beans;

import java.util.LinkedList;

public class Cuenta {

	private int idCuenta, cantidad;
	private LinkedList<String> titulares = new LinkedList<>();

	public Cuenta() {
		super();
	}

	public Cuenta(int idCuenta, int cantidad, LinkedList<String> titulares) {
		super();
		this.idCuenta = idCuenta;
		this.cantidad = cantidad;
		this.titulares = titulares;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LinkedList<String> getTitulares() {
		return titulares;
	}

	public void setTitulares(LinkedList<String> titulares) {
		this.titulares = titulares;
	}

}
