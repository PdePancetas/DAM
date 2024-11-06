package Hotel;

public class Habitacion {

	protected int numHab, numCamas;
	protected double precioNoche;

	public int getNumHab() {
		return numHab;
	}
	public void setNumHab(int numHab) {
		this.numHab = numHab;
	}

	public int getNumCamas() {
		return numCamas;
	}
	public void setNumCamas(int numCamas) {
		this.numCamas = numCamas;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	public Habitacion(int numHab, int numCamas, double precioNoche) {
		super();
		this.numHab = numHab;
		this.numCamas = numCamas;
		this.precioNoche = precioNoche;
	}
	public Habitacion() {
	}
	
	public int plazasTotales() {
		return this.numCamas;
	}
	
	
}
