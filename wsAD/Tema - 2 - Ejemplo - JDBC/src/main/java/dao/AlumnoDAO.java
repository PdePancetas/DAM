package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			PreparedStatement sent = con
					.prepareStatement("UPDATE alumnos SET nombre = ?, idProf = ? ,nota = ? WHERE id = ?");
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

	// TRANSACCIONES:
	public boolean intercambiaNota(Alumno a1, Alumno a2) {

		Connection con = ConexionBD.getConex();

		try {

			con.setAutoCommit(false); // Para que si hay errores entre sentencias, la operacion no se quede a medias
										// y realizar el commit cuando se crea oportuno
//			double nota1 = a1.getNota();
//			double nota2 = a2.getNota();
			// Modificar nota de a1
			PreparedStatement ps = con.prepareStatement("UPDATE alumnos SET nota = ? WHERE id = ?");
			ps.setDouble(1, a2.getNota());
			ps.setInt(2, a1.getIdAlumno());
			ps.executeUpdate();

//			int n = 5 / 0;

			// Modificar nota de a2
			ps = con.prepareStatement("UPDATE alumnos SET nota = ? WHERE id = ?");
			ps.setDouble(1, a1.getNota());
			ps.setInt(2, a2.getIdAlumno());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public void datosAlumnos() {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from alumnos");

			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numColumns; i++)
				System.out.print("\t" + rsmd.getColumnName(i));

			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= numColumns; i++) {
					System.out.print("\t" + rs.getObject(i));
				}
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que elimina todos los alumnos suspensos y sube la nota
	 * porcentajeSubida a los demas
	 */
	public void eliminaSuspensosSubeNota_v1(double porcentajeNota) {

		Connection con = ConexionBD.getConex();
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("DELETE from alumnos WHERE nota < 5");
			ps.executeUpdate();
			ps = con.prepareStatement("SELECT * from alumnos");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				double porcentaje = 1+(porcentajeNota/100);
				double nota = rs.getDouble("nota");
				ps = con.prepareStatement("UPDATE alumnos SET nota = ? WHERE id = ?");
				if (nota * porcentaje > 10)
					ps.setDouble(1, 10);
				else
					ps.setDouble(1, nota * porcentaje);
				
				ps.setInt(2, rs.getInt("id"));
				ps.executeUpdate();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que elimina todos los alumnos suspensos y sube la nota
	 * porcentajeSubida a los demas
	 */
	public void eliminaSuspensosSubeNota_v2(int porcentajeNota) {
		
		
		Connection con = ConexionBD.getConex();
		
		
	}
}
