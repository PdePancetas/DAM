package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Alumno;
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
	

}
