package beans;

public class Empleado {

	private int nif;
	private String nombre;
	private double sueldoBase;

	public int getNif() {
		return nif;
	}

	public void setNif(int nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public Empleado(int nif, String nombre, double sueldoBase) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.sueldoBase = sueldoBase;
	}

	public Empleado() {
		super();
	}

}
