package Ejercicio9;

public class Salmon {
	
	private double peso;
	private String nombre, region;
	
	public Salmon(String nombre, double peso, String region) {
		this.peso = peso;
		this.nombre = nombre;
		this.region = region;
	}
	public Salmon() {
	}
	
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	
	public void nadar() {
		System.out.println(this.nombre+" nada feliz en "+this.region);
	}
	public String describirSalmon() {
		String result= "Me llamo "+this.nombre+", peso "+this.peso+" kg, vivo en "+this.region;
		return result;
	}
	

}
