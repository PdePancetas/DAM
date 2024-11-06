package Hotel;

import java.util.Date;

public class Hotel {
	
	private Habitacion[] habs;
	private Reserva[] reservas;
	
	
	public Habitacion[] getHabs() {
		return habs;
	}
	public void setHabs(Habitacion[] habs) {
		this.habs = habs;
	}
	
	public Reserva[] getReservas() {
		return reservas;
	}
	public void setReservas(Reserva[] reservas) {
		this.reservas = reservas;
	}

	public Hotel(Habitacion[] habs, Reserva[] reservas) {
		super();
		this.habs = habs;
		this.reservas = reservas;
	}
	public Hotel() {
	}
	
	public Reserva buscaDisponibilidad(int numPersonas, Date fecha) {
		for(int i=0;i<reservas.length;i++) {
			if(reservas[i].getFecha().equals(fecha)) {
				System.out.println("Esta fecha ya está reservada");
				return null;
			}
		}
		
		int plazasTotales=0;
		for(int i=0;i<habs.length;i++)
			plazasTotales+=habs[i].plazasTotales();
		
		if(plazasTotales>=numPersonas) {
			System.out.println("No hay suficientes ");
			return null;
		}
		Reserva reserva= new Reserva(fecha, numPersonas, numPersonas);
		return reserva;
	}
	
	public int disponibilidad(Date fecha) {
		for(int i=0;i<habs.length;i++) {
			
		}
			
		return (Integer) null;
	}
}
