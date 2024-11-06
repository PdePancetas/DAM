package Ejercicio2;

public class FiguraGeometrica {

	protected String nombre;
	protected int lados;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getLados() {
		return lados;
	}
	public void setLados(int lados) {
		this.lados = lados;
	}
	
	public FiguraGeometrica(String nombre, int lados) {
		super();
		this.nombre = nombre;
		this.lados = lados;
	}
	public FiguraGeometrica() {
	}
	
	
}
