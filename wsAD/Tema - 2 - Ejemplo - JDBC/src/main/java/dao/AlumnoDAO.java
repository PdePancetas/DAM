package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Alumno;
import beans.Profesor;
import connection.ConexionBD;

public class AlumnoDAO {

	public boolean addAlumno(Alumno a) {
		Connection con = ConexionBD.getConex();

		try {

			Statement sentencia = con.createStatement();

			String SQL = "INSERT INTO alumnos VALUES(" + a.getIdAlumno() + ",'" + a.getNombre() + "',"
					+ a.getIdProfesor() + "," + a.getNota() + ")";

			sentencia.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public Alumno retrieveAlumno(int id) {
		Connection con = ConexionBD.getConex();
		Alumno a = null;
		try {
			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
			sentencia.setInt(1, id);
			ResultSet result = sentencia.executeQuery();

			while (result.next()) {
				a = new Alumno(result.getInt("id"), result.getInt("idProf"), result.getString("nombre"),
						result.getDouble("nota"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return a;

	}

	// CON ESTE MÉTODO SERÍA MUY FACIL HACER INYECCIONES SQL:
	// ArrayList<Alumno> a = alDao.retrieveAlumno("Alberto' or '1' = '1")
	public ArrayList<Alumno> retrieveAlumno(String nombre) {
		Connection con = ConexionBD.getConex();
		ArrayList<Alumno> a = new ArrayList<Alumno>();
		try {
			String SQL = "SELECT * FROM alumnos WHERE nombre = '" + nombre + "'";
			Statement sentencia = con.createStatement();
			ResultSet result = sentencia.executeQuery(SQL);

			while (result.next()) {
				a.add(new Alumno(result.getInt("id"), result.getInt("idProf"), result.getString("nombre"),
						result.getDouble("nota")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return a;

	}

	// ESTA VERSION NO PERMITE LA INYECCION SQL
	public ArrayList<Alumno> retrieveAlumnoV2(String nombre) {
		Connection con = ConexionBD.getConex();
		ArrayList<Alumno> a = new ArrayList<Alumno>();

		try {
			PreparedStatement sent = con.prepareStatement("SELECT * FROM alumnos WHERE nombre = ?");
			sent.setNString(1, nombre);
			ResultSet result = sent.executeQuery();

			while (result.next()) {
				a.add(new Alumno(result.getInt("id"), result.getInt("idProf"), result.getString("nombre"),
						result.getDouble("nota")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return a;

	}

	public boolean addAlumnoV2(Alumno a) {
		Connection con = ConexionBD.getConex();

		try {

			PreparedStatement sent = con.prepareStatement("INSERT INTO alumnos VALUES(?,?,?,?)");
			sent.setInt(1, a.getIdAlumno());
			sent.setString(2, a.getNombre());
			sent.setInt(3, a.getIdProfesor());
			sent.setDouble(4, a.getNota());

			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean deleteAlumno(int id) {

		Connection con = ConexionBD.getConex();

		try {

			PreparedStatement sent = con.prepareStatement("DELETE FROM alumnos WHERE id = ?");
			sent.setInt(1, id);

			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateAlumno(Alumno a) {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement sent = con.prepareStatement("UPDATE alumnos SET nombre = ?, idProf = ? ,nota = ? WHERE id = ?");
			sent.setString(1, a.getNombre());
			sent.setInt(2, a.getIdProfesor());
			sent.setDouble(3, a.getNota());
			sent.setDouble(4, a.getIdAlumno());

			sent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
