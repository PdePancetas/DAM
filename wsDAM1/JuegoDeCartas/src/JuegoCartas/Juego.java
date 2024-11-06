package JuegoCartas;

import java.util.HashSet;

public class Juego {

	public static void main(String[] args) {

		Baraja baraja = new Baraja();
		baraja.setCartasBaraja(Baraja.getCartasBaraja());

//		for(Carta c: baraja.getCartasBaraja())
//			System.out.println(c.toString());

		Jugador j1 = new Jugador("Jug1", 0);
		Jugador j2 = new Jugador("Jug2", 0);

		int cont = 0;
		for (Carta c : Baraja.getCartasBaraja())
			if (cont < 10) {
				j1.getCartasJugador().add(c);
				cont++;

			} else if (cont < 20) {
				j2.getCartasJugador().add(c);
				cont++;

			}

		HashSet<String> palos = new HashSet<>();
		palos.add("Oros");
		palos.add("Espadas");
		palos.add("Copas");
		palos.add("Bastos");

		String paloEscogido = "";
		int contPalo = 0;
		for (String s : palos)
			if (contPalo == 0) {
				paloEscogido = s;
				contPalo++;
			}

		System.out.println("El palo será " + paloEscogido);

		while (/* j1.getCartasJugador().size()!=0||j1.getCartasJugador().size()!=0 */ Baraja.getCartasBaraja()
				.size() != 0) {
			System.out.println("\n***************** \nCartas Jugador 1: ");
			j1.mostrarMano();
			System.out.println("\nCartas Jugador 2: \n*****************");
			j2.mostrarMano();
			System.out.println("********************");

			Carta cj1 = j1.jugarCarta(paloEscogido);
			Carta cj2 = j2.jugarCarta(paloEscogido);
			if (j1.getCartasJugador().contains(cj1))
				j1.getCartasJugador().remove(cj1);
			if (j2.getCartasJugador().contains(cj2))
				j2.getCartasJugador().remove(cj2);
			System.out.println("\n***************** \nCartas Jugador 1: ");
			j1.mostrarMano();
			System.out.println("\nCartas Jugador 2: \n*****************");
			j2.mostrarMano();
			System.out.println("********************");

			// En caso de que los 2 hayan podido jugar
			if (cj1 != null && cj2 != null)
				if (cj1.getNumCarta() > cj2.getNumCarta())
					j1.setPuntuacion(j1.getPuntuacion() + 1);
				else if (cj1.getNumCarta() < cj2.getNumCarta())
					j2.setPuntuacion(j2.getPuntuacion() + 1);

			// En caso de que solo 1 haya podido jugar
			if ((cj1 != null && cj2 == null) || (cj1 == null && cj2 != null)) {
				if (cj1 != null)
					j1.setPuntuacion(j1.getPuntuacion() + 1);
				if (cj2 != null)
					j2.setPuntuacion(j2.getPuntuacion() + 1);
			}
			// En caso de que ninguno pueda jugar
			if (cj1 == null && cj2 == null) {
				Object[] cartas = Baraja.getCartasBaraja().toArray();
				for (int i = 0; i < Baraja.getCartasBaraja().size() && j1.getCartasJugador().size() < 10; i++) {

					j1.recibirCarta((Carta) cartas[i]);
				}

				for (int i = 0; i < Baraja.getCartasBaraja().size() && j2.getCartasJugador().size() < 10; i++) {

					j2.recibirCarta((Carta) cartas[i]);
				}
			}

			System.out.println("Puntuacion jugador 1: " + j1.getPuntuacion());
			System.out.println(
					"Puntuacion jugador 2: " + j2.getPuntuacion() + "\n_______________________________________ \n");

		}

	}

}
