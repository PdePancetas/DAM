package Ejercicio10;

public class Casa {

	private String calle;
	private int numero, codArea, plantas;
	private double superficie;

	public Casa(String calle, int numero, int codArea, int superficie, int plantas) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.codArea = codArea;
		this.superficie = superficie;
		this.plantas = plantas;
	}

	public Casa() {
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCodArea() {
		return codArea;
	}

	public void setCodArea(int codArea) {
		this.codArea = codArea;
	}

	public int getPlantas() {
		return plantas;
	}

	public void setPlantas(int plantas) {
		this.plantas = plantas;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public void direccion() {
		System.out.println(
				"Esta casa esta situada en " + this.calle + ", nº" + this.numero + ", codArea: " + this.codArea);
	}

	public int numHabitaciones() {
		int habitaciones = ((int) (this.superficie / 10)) * this.plantas;

		return habitaciones;
	}

	public boolean estaEnArea(int codArea) {

		boolean esta = false;
		if (this.codArea == codArea)
			esta = true;

		return esta;
	}

}
