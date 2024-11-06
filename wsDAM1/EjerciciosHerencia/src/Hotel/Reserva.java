package Hotel;

import java.util.Date;

public class Reserva {
	
	private Date fecha;
	private int habReservadas, numPersonas;
	

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHabReservadas() {
		return habReservadas;
	}
	public void setHabReservadas(int habReservadas) {
		this.habReservadas = habReservadas;
	}

	public int getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public Reserva(Date fecha, int habReservadas, int numPersonas) {
		super();
		this.fecha = fecha;
		this.habReservadas = habReservadas;
		this.numPersonas = numPersonas;
	}
	public Reserva() {
	}
	
	public int compruebaDisponibilidad(Date fecha) {
		if(fecha.equals(this.fecha)) {
			return habReservadas;
		}
		
		return (Integer) null;
		
	}
	
}
