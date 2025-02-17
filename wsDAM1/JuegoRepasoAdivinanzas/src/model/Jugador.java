package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import interfaz.Juego;
import utilidadesTeclado.Teclado;

public class Jugador implements Juego {

	static HashSet<Jugador> jugadores;

	private int partidasGanadas;
	private String nombre;

	public Jugador(String nombre, int partidasGanadas) {
		super();
		this.partidasGanadas = 0;
		this.nombre = nombre;
	}

	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Jugador() {
		super();
	}

	@Override
	public int Jugar(Random r) {

		if(this instanceof JugadorAutomatico) {
			return r.nextInt(1,10001);
		} else if( this instanceof JugadorTramposo) {
			JugadorTramposo j = (JugadorTramposo) this;
			return r.nextInt(j.numeroAElegir-1000,j.numeroAElegir+1001);
		} else {
			System.out.print("Elige un numero: ");
			return Teclado.leerEntero();
		}
		
	}

	@Override
	public void partidoGanado() {
		
		for(Jugador j: jugadores)
			if(j.equals(this))
				this.partidasGanadas++;
		
		//jugadores.stream().filter(j -> j.getNombre().equals(nombre)).findFirst().get().partidasGanadas++;
		
	}

	@Override
	public void partidoAnulado() {

//		for(int i=0;i<jugadores.size();i++)
//			if(jugadores.get(i).getNombre().equals(nombre))
//				jugadores.remove(i);
		
		jugadores.remove(this);
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public static HashSet<Jugador> getJugadores() {
		return jugadores;
	}

	public static void setJugadores(HashSet<Jugador> jugadores) {
		Jugador.jugadores = jugadores;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Jugador nombre=" + nombre;
	}
	
	

}
