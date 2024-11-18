package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Profesor;
import connection.ConexionBD;

public class ProfesorDAO {

	public boolean addProferor(Profesor p) {

		Connection con = ConexionBD.getConex();

		try {

			PreparedStatement sent = con.prepareStatement("INSERT INTO profesores VALUES(?,?,?)");
			sent.setInt(1, p.getIdProfesor());
			sent.setString(2, p.getNombre());
			sent.setString(3, p.getModulo());

			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean deleteProfesor(int id) {

		Connection con = ConexionBD.getConex();

		try {

			PreparedStatement sent = con.prepareStatement("DELETE FROM profesores WHERE id = ?");
			sent.setInt(1, id);
			
			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Profesor retrieveProfesor(int id) {

		Connection con = ConexionBD.getConex();
		
		Profesor p = new Profesor();
		try {
			PreparedStatement sent = con.prepareStatement("SELECT * FROM profesores WHERE id = ?");
			sent.setInt(1, id);
			ResultSet result = sent.executeQuery();

			while (result.next()) {
				p = new Profesor(result.getInt("id"), result.getString("nombre"), result.getString("modulo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return p;
	}

	public ArrayList<Profesor> retrieveProfesor(String nombre) {

		Connection con = ConexionBD.getConex();
		ArrayList<Profesor> p = new ArrayList<>();

		try {
			PreparedStatement sent = con.prepareStatement("SELECT * FROM profesores WHERE nombre = ?");
			sent.setString(1, nombre);
			ResultSet result = sent.executeQuery();

			while (result.next()) {
				p.add(new Profesor(result.getInt("id"), result.getString("nombre"), result.getString("modulo")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return p;
	}

	public boolean updateProfesor(Profesor p) {
		
		Connection con = ConexionBD.getConex();
		

		try {
			PreparedStatement sent = con.prepareStatement("UPDATE profesores SET nombre = ?, modulo = ? WHERE id = ?");
			sent.setString(1, p.getNombre());
			sent.setString(2, p.getModulo());
			sent.setInt(3, p.getIdProfesor());
			
			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
