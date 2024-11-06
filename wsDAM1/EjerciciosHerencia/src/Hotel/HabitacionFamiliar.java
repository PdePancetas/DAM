package Hotel;

public class HabitacionFamiliar extends Habitacion {
	
	
	private int numCamasDobles;
	
	public int getNumCamasDobles() {
		return numCamasDobles;
	}
	public void setNumCamasDobles(int numCamasDobles) {
		this.numCamasDobles = numCamasDobles;
	}
	
	public HabitacionFamiliar(int numHab, int numCamas, double precioNoche, int numCamasDobles) {
		super(numHab, numCamas, precioNoche);
		this.numCamasDobles = numCamasDobles;
	}
	public HabitacionFamiliar() {
	}
	
	@Override
	public int plazasTotales() {
		return this.numCamas+this.numCamasDobles*2;	
	}
}
