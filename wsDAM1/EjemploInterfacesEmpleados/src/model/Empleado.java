package model;

public abstract class Empleado {

	protected String nif, nombre;
	protected Double sueldo;
	
	
	
	public Empleado(String nif, String nombre, Double sueldo) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}



	public Empleado() {
	}



	public String getNif() {
		return nif;
	}



	public void setNif(String nif) {
		this.nif = nif;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Double getSueldo() {
		return sueldo;
	}



	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}
	
	public abstract String generarNomina();
	
	
	
}
