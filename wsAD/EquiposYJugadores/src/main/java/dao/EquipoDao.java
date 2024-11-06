package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import beans.Equipo;
import beans.Jugador;
import exception.EquipoExisteException;
import exception.JugadorExisteException;

public interface EquipoDao {

	/*
	 * - addEquipo(String nombre) - insertPlayer(String nombreEquipo, Jugador
	 * jugador) - deletePlayer(String nombreEquipo, String nombreJugador) -
	 * updatePosition(String nombreEquipo, String nombreJugador, int posición)
	 */

	public void createEquipo(String nombre)
			throws FileNotFoundException, IOException, ClassNotFoundException, EquipoExisteException;

	public void insJugador(String nombreEquipo, Jugador jugador)
			throws FileNotFoundException, IOException, ClassNotFoundException, JugadorExisteException;

	public void deleteJugador(String nombreEquipo, String nombreJugador)
			throws FileNotFoundException, IOException, ClassNotFoundException;

	public void updatePosition(String nombreEquipo, String nombreJugador, int posicion)
			throws FileNotFoundException, IOException, ClassNotFoundException;

	public Equipo listEquipo(String nombreEquipo) throws IOException, ClassNotFoundException;

	public ArrayList<String> listEquipos() throws IOException, ClassNotFoundException;
}
