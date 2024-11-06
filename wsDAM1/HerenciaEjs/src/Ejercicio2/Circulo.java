package Ejercicio2;

public class Circulo extends FiguraGeometrica{
	
	private double radio;
	
	
	
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	public Circulo(String nombre, int lados, double radio) {
		super(nombre, lados);
		
		this.radio=radio;
	}
	public Circulo() {
	}

}
