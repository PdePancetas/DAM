package main;

import java.util.List;

import logic.Func;
import modelJDBC.Partido;

public class Main {

	public static void main(String[] args) {

		/// Funcionalidad 1
		/// Dado un id de jugador devuelve todos los objetos Partido en los que juega,
		/// teniendo los objetos Partido las propiedades id, fecha y lugar.
		/// Realizar con JDBC
		/*
		List<Partido> partidosJuegaEn = Func.juegaEn(2);

		if (partidosJuegaEn.isEmpty()) {
			System.out.println("El jugador con id " + 1 + " no juega en ningun partido");
		} else {
			System.out.println("El jugador con id " + 1 + " juega en los siguientes partidos: ");
			partidosJuegaEn.forEach(System.out::println);
		}
		*/
		
		
		///Funcionalidad 2
		///Realiza la asignación correspondiente entre jugador y partido. 
		///Realizar con JDBC
		/*
		if (Func.asignaPartidoJugador(1, 3))
			System.out.println("El jugador fue asociado correctamente al partido");
		else
			System.out.println("Hubo un error al asociar el jugador con el partido, puede que alguno no exista");
		 */
		
		
		///Funcionalidad 3
		///Devuelve el jugador que más partidos tiene asignados.
		///Realizar con Hibernate Nativo
		/*
		System.out.println("El jugador que tiene más partidos asignados es "+
				Func.masPartidosAsignados().getNombre());
		*/
		
		
		///Funcionalidad 4
		///Indica si un jugador juega algún partido en el lugar indicado. 
		///Realizar con Hibernate 
		/*
		System.out.println(Func.jugadorJuegaEnLugar(2, "Úbeda")?
				"El jugador sí juega en algun partido en el lugar indicado"
				:"El jugador no juega en algun partido en el lugar indicado");
		*/
	
	
	
	}

}
