package com.dam1.entidades;

public class Item {

	private String desc;
	private int cant;
	private double precioUnidad;
	
	public Item(String desc, int cant, double precio) {
		this.desc = desc;
		this.cant = cant;
		this.precioUnidad= precio;
	}
	public Item() {
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(double precio) {
		this.precioUnidad = precio;
	}
	
	
	
	
}
