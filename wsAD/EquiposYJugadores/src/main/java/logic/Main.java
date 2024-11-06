package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Jugador;
import dao.EquipoDao;
import equipoDaoImpl.EquipoDaoImpl;
import exception.EquipoExisteException;
import exception.JugadorExisteException;
import utilidadesTeclado.Teclado;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);

	/*
	 * 1. Crear equipo nuevo (sin jugadores) 2. Insertar jugador en equipo 3.
	 * Eliminar jugador de un equipo dado su nombre 4. Cambiar posici�n de un
	 * jugador dado nombre de equipo, nombre de jugador y nueva posici�n
	 * 
	 * 
	 */

	public static void main(String[] args) {

		logger.info("Aplicacion arrancada");
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("config.props"));
		} catch (IOException e) {
			logger.error("No se pudo acceder al archivo de configuracion");
			e.printStackTrace();
		}
		EquipoDao equipoDaoImpl = new EquipoDaoImpl(new File(p.getProperty("NOMBREFICHERO")));
		logger.info("Fichero cargado desde: "+p.getProperty("NOMBREFICHERO"));
		int op = 1;
		do {
			System.out.println("\n_________________________________________________________");
			System.out.println("Elige una opcion: ");
			System.out.println("1. Crear equipo nuevo");
			System.out.println("2. Insertar jugador en equipo");
			System.out.println("3. Eliminar jugador de un equipo");
			System.out.println("4. Cambiar posicion de un jugador");
			System.out.println("5. Listar jugadores de un equipo");
			System.out.println("6. Listar equipos");
			System.out.println("0. Salir");
			System.out.print("\n -> ");

			try {
				op = Teclado.leerEntero();
			} catch (NumberFormatException e) {
				op = -1;
			}
			switch (op) {
			case 1:

				try {
					System.out.print("Introduce nombre del equipo: ");
					String nombreEquipo = Teclado.leerCadena();

					equipoDaoImpl.createEquipo(nombreEquipo);
					logger.info("Equipo " + nombreEquipo + " creado");
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				} catch (EquipoExisteException e) {
					System.err.println("Ya existe un equipo con ese nombre");
				}

				break;
			case 2:
				try {
					System.out.print("Introduce nombre del equipo: ");
					String nombreEquipo = Teclado.leerCadena();

					System.out.print("Introduce nombre del jugador: ");
					String nombreJugador = Teclado.leerCadena();

					System.out.print("Introduce numero de " + nombreJugador + ": ");
					int numJugador = Teclado.leerEntero();

					System.out.print("Introduce posicion de " + nombreJugador + " en " + nombreEquipo + ": ");
					int posJugador = Teclado.leerEntero();

					equipoDaoImpl.insJugador(nombreEquipo, new Jugador(nombreJugador, numJugador, posJugador));
					logger.info("Se inscribio al jugador " + nombreJugador + " en el equipo " + nombreEquipo);
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				} catch (JugadorExisteException e) {
					System.err.println("Ya existe un jugador con ese nombre");
				}

				break;
			case 3:

				try {
					System.out.print("Introduce nombre del equipo: ");
					String nombreEquipo = Teclado.leerCadena();

					System.out.print("Introduce nombre del jugador: ");
					String nombreJugador = Teclado.leerCadena();

					equipoDaoImpl.deleteJugador(nombreEquipo, nombreJugador);
					logger.info("Se elimino al jugador " + nombreJugador + "del equipo " + nombreEquipo);
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				}
				break;
			case 4:
				try {
					System.out.print("Introduce nombre del equipo: ");
					String nombreEquipo = Teclado.leerCadena();

					System.out.print("Introduce nombre del jugador: ");
					String nombreJugador = Teclado.leerCadena();

					System.out.print("Introduce nueva posicion de " + nombreJugador + ": ");
					int posJugador = Teclado.leerEntero();

					equipoDaoImpl.updatePosition(nombreEquipo, nombreJugador, posJugador);
					logger.info("Se actualizo la posicion de "+nombreJugador+" a "+posJugador);
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				}

				break;
			case 5:
				try {
					System.out.print("Introduce nombre del equipo: ");
					String nombreEquipo = Teclado.leerCadena();
					for (Jugador j : equipoDaoImpl.listEquipo(nombreEquipo).getJugadores())
						System.out.println("-> " + j.toString());
					logger.info("Se hizo un listado de los jugadores de "+nombreEquipo);
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				}

				break;
			case 6:
				try {
					for (String equipo : equipoDaoImpl.listEquipos())
						System.out.println(equipo);
					logger.info("Se hizo un listado de todos los equipos");
				} catch (FileNotFoundException e) {
					System.err.println("No se pudo encontrar el archivo de equipos");
					logger.error("No se encontro el archivo: " + e.getMessage());
				} catch (IOException e) {
					System.err.println("Se produjo un error");
					logger.error("Error I/O: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.err.println("No se pudo encontrar algun equipo en el fichero");
				}
				break;
			case 0:
				System.out.println("Saliendo...");
				logger.info("Se cerro la aplicacion");
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} while (op != 0);

	}

}
