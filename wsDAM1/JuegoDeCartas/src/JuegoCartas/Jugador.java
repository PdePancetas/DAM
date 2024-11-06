package JuegoCartas;

import java.util.LinkedList;

public class Jugador {

	private String nombre;
	private int puntuacion;
	private LinkedList<Carta> cartasJugador = new LinkedList<>();

	public Jugador() {
	}

	public Jugador(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public LinkedList<Carta> getCartasJugador() {
		return cartasJugador;
	}

	public void setCartasJugador(LinkedList<Carta> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}

	public void recibirCarta(Carta carta) {
		cartasJugador.add(carta);
		Baraja.getCartasBaraja().remove(carta);
	}

	public Carta jugarCarta(String palo) {

		Carta cartaElegida = null;
		boolean encontrada = false;
		for (int i = 0; i < cartasJugador.size() && !encontrada; i++) {
			if (cartasJugador.get(i).getPalo().equals(palo)) {
				cartaElegida = cartasJugador.get(i);
				encontrada = true;
			}
		}
		if (encontrada) {
			for (int i = 0; i < cartasJugador.size(); i++) {
				if (cartasJugador.get(i).getPalo().equals(palo))
					if (cartasJugador.get(i).getNumCarta() > cartaElegida.getNumCarta())
						cartaElegida = cartasJugador.get(i);
			}
			System.out.println("(" + nombre + ") Jugando el numero " + cartaElegida.getNumCarta() + " de "
					+ cartaElegida.getPalo());
			cartasJugador.remove(cartaElegida);
			return cartaElegida;

		} else {
			System.out.println("Paso");
			return null;
		}

	}

	public void mostrarMano() {
		for (int i = 0; i < cartasJugador.size(); i++)
			System.out.println(cartasJugador.get(i).toString());
	}

}
