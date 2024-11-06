package JuegoCartas;

import java.util.HashSet;

public class Baraja {

	private static HashSet<Carta> cartasBaraja = new HashSet<>();

	public static HashSet<Carta> getCartasBaraja() {
		return cartasBaraja;
	}

	public void setCartasBaraja(HashSet<Carta> cartasBaraja) {
		Baraja.cartasBaraja = cartasBaraja;
	}

	public Baraja() {
		for (int i = 1; i < 11; i++) {
			cartasBaraja.add(new Carta("Oros", i));
		}
		for (int i = 1; i < 11; i++) {
			cartasBaraja.add(new Carta("Copas", i));
		}
		for (int i = 1; i < 11; i++) {
			cartasBaraja.add(new Carta("Espadas", i));
		}
		for (int i = 1; i < 11; i++) {
			cartasBaraja.add(new Carta("Bastos", i));
		}

	}

}
