package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import model.Jugador;
import model.JugadorAutomatico;
import model.JugadorReal;
import model.JugadorTramposo;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int rondas = 5;
		int numJugadores = 20;

		Random r = new Random();

		int numTramposos = r.nextInt(1, 5);
		Jugador.setJugadores(new HashSet<>());

		for (int i = 0; i < numJugadores - numTramposos - 1; i++) {
			Jugador.getJugadores().add(new JugadorAutomatico("Jugador" + i));
		}

		for (int i = numJugadores - numTramposos; i < numJugadores; i++) {
			Jugador.getJugadores().add(new JugadorTramposo("Jugador" + i));
		}

		Jugador.getJugadores().add(new JugadorReal("Tu"));

		HashMap<Jugador, Integer> numerosElegidos = null;

		for (int i = 0; i < rondas; i++) {
			int numeroAElegir = r.nextInt(1, 10001);
			
			Jugador.getJugadores().stream()
		    .filter(j -> j instanceof JugadorTramposo)// Filtrar solo los JugadorTramposo
		    .forEach(j -> ((JugadorTramposo) j).setNumeroAElegir(numeroAElegir));// Asignar número a cada uno

			
			System.out.println("\nEmpieza una la ronda " + i + "\n");

			numerosElegidos = new HashMap<>();

			for (Jugador j : Jugador.getJugadores()) {
				numerosElegidos.put(j, j.Jugar(r));
			}

			for (Entry<Jugador, Integer> e : numerosElegidos.entrySet())
				System.out.println(e.getKey().getNombre() + " a escogido el " + e.getValue());

			Jugador ganadorRonda = JugadorGanadorRonda(numerosElegidos, numeroAElegir);
			System.out.println("Ganador de la ronda: " + ganadorRonda.getNombre());
			ganadorRonda.setPartidasGanadas(ganadorRonda.getPartidasGanadas() + 1);


			System.out.print("Sospechas de algún jugador? ");

			String nombre = Teclado.leerCadena();

			Optional<Jugador> jugadorr = Jugador.getJugadores().stream().filter(j -> j.getNombre().equals(nombre))
					.findFirst();

			if (!nombre.equals("") && jugadorr.isPresent()) {
				Jugador jug = getJugador(nombre, Jugador.getJugadores());
				if (jug instanceof JugadorTramposo) {
					System.out.println("Has encontrado a un tramposo!");
					int partidasTramposo = jug.getPartidasGanadas();
					for (Jugador jugador : Jugador.getJugadores())
						if (jugador instanceof JugadorReal)
							jugador.setPartidasGanadas(jugador.getPartidasGanadas() + partidasTramposo + 1);

					jug.partidoAnulado();

				} else {
					System.out.println("Has acusado a un jugador inocente!");
					int partidasGanadas = 0;
					for (Jugador jugador : Jugador.getJugadores())
						if (jugador instanceof JugadorReal) {
							partidasGanadas = jugador.getPartidasGanadas();
							jugador.setPartidasGanadas(0);
						}

					for (Jugador jugador : Jugador.getJugadores())
						if (jugador.equals(getJugador(nombre, Jugador.getJugadores())))
							jugador.setPartidasGanadas(jugador.getPartidasGanadas() + partidasGanadas);

				}

			} else {
				System.out.println("No se ha acusado a nadie o no existe el jugador nombrado");
			}

		}

		System.out.println("El ganador de la partida es " + JugadorGanadorPartida(numerosElegidos));

	}

	private static Jugador JugadorGanadorPartida(HashMap<Jugador, Integer> numerosElegidos) {

		List<Jugador> jugadores = List.copyOf(numerosElegidos.keySet());

//		Jugador j = jugadores.get(0);
//
//		for (Jugador jug : jugadores)
//			if (jug.getPartidasGanadas() > j.getPartidasGanadas())
//				j = jug;

		return jugadores.stream().max(Comparator.comparingInt(Jugador::getPartidasGanadas)).orElse(null);

//		return j;
	}

	private static Jugador JugadorGanadorRonda(HashMap<Jugador, Integer> numerosElegidos, int numeroAElegir) {

//		List<Jugador> jugadores = List.copyOf(numerosElegidos.keySet());
//
//		Jugador j = jugadores.get(0);
//
//		for (Jugador jug : jugadores) {
//
//			if (Math.abs(numerosElegidos.get(jug) - numeroAElegir) < Math.abs(numerosElegidos.get(j) - numeroAElegir))
//				j = jug;
//		}
//
//		return j;
		
		return numerosElegidos.entrySet().stream()
			    .min(Comparator.comparingInt(e -> Math.abs(e.getValue() - numeroAElegir)))
			    .map(Entry::getKey)
			    .orElse(null);

	}

	private static Jugador getJugador(String nombre, HashSet<Jugador> jugadores) {

//		for (Jugador j : jugadores)
//			if (j.getNombre().equals(nombre))
//				return j;
//
//		return null;
		
		return jugadores.stream()
		        .filter(j -> j.getNombre().equals(nombre))
		        .findFirst()
		        .orElse(null);

	}

}
