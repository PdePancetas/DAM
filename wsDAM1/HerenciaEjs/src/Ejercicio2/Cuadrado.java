package Ejercicio2;

public class Cuadrado extends FiguraGeometrica{
	
	private double lado;
	
	
	
	public double getLado() {
		return lado;
	}
	public void setLado(double lado) {
		this.lado = lado;
	}
	
	public Cuadrado(String nombre, int lados, double lado) {
		super(nombre, lados);
		
		this.lado=lado;
	}
	public Cuadrado() {
	}

}
