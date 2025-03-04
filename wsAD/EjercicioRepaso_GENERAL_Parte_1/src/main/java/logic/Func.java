package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.ConexionBD;
import modelHibernate.Jugadores;
import modelJDBC.Partido;
import utils.HibernateUtil;

public class Func {

	public static List<Partido> juegaEn(int idJugador) {

		Connection con = ConexionBD.getConex();
		List<Partido> partidos = new ArrayList<>();

		try {
			PreparedStatement ps = con.prepareStatement("SELECT p.* FROM partidos p JOIN "
					+ "jugadorpartido jp ON p.idPartido = jp.idPartido " + "WHERE jp.idJugador = ?");
			ps.setInt(1, idJugador);

			ResultSet result = ps.executeQuery();
			while (result.next()) {
				partidos.add(
						new Partido(result.getInt("idPartido"), result.getDate("fecha"), result.getString("lugar")));

			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return partidos;
	}

	public static boolean asignaPartidoJugador(int idPartido, int idJugador) {
		boolean insertadoCorrectamente = false;
		Connection con = ConexionBD.getConex();
		try {
			con.setAutoCommit(false);
//			boolean partidoEsta = false;
			PreparedStatement obtenerPartido = con
					.prepareStatement("SELECT idPartido FROM partidos WHERE idPartido = ?");
			obtenerPartido.setInt(1, idPartido);

			ResultSet rs1 = obtenerPartido.executeQuery();
			while (rs1.next())
				if (rs1.getInt(idPartido) == 0)
					return false;

//			boolean jugadorEsta = false;
			PreparedStatement obtenerJugador = con
					.prepareStatement("SELECT idJugador FROM jugadores WHERE idJugador = ?");
			obtenerJugador.setInt(1, idJugador);

			ResultSet rs2 = obtenerJugador.executeQuery();
			if (rs2.next()) {
				if (rs2.getInt(idJugador) == 0) {
					return false;
				}
			} else
				return false;
			PreparedStatement insertarJugadorPartido = con.prepareStatement("INSERT INTO jugadorpartido VALUES(?,?)");
			insertarJugadorPartido.setInt(1, idJugador);
			insertarJugadorPartido.setInt(2, idPartido);

			System.out.println(insertarJugadorPartido.executeUpdate());

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return insertadoCorrectamente;
	}

	public static Jugadores masPartidosAsignados() {

		Session session = HibernateUtil.getSession();
		Jugadores j = null;

		try {
			List<Jugadores> jugadores = session.createQuery("FROM Jugadores", Jugadores.class).list();
			
			j = jugadores.stream().max(Comparator.comparingInt(Jugadores::partidosSize)).get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	public static boolean jugadorJuegaEnLugar(int idJugador, String lugar) {

		
		Session session = HibernateUtil.getSession();
		try {
			List<Jugadores> js = session.createQuery("FROM Jugadores", Jugadores.class).list();
			
			Optional<Jugadores> jugador = js.stream().filter(jug -> jug.getIdJugador().equals(idJugador)).findFirst();
			
			if(jugador.isPresent())
				return jugador.get().getPartidos().stream().filter(p -> p.getLugar().equals(lugar)).count()>0;
				
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
		
	}

}
