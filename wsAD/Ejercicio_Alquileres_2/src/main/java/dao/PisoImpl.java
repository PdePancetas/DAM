package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Empleado;
import beans.Piso;
import connection.ConexionBD;

public class PisoImpl implements PisoDao {

	@Override
	public boolean addPiso(Piso piso) {
		Connection con = ConexionBD.getConex();

		try {

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO pisos(direccion, mensualidad, alquilado, nif_Empleado) VALUES(?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);// SI QUEREMOS SABER LA CLAVE PRIMARIA QUE SE HA GENERADO:
			ps.setString(1, piso.getDireccion());
			ps.setDouble(2, piso.getMensualidad());
			ps.setInt(3, 0);
			ps.setInt(4, piso.getNif_Empleado());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			int clave = 0;
			while(rs.next())
				clave = rs.getInt(1);
			
			System.out.println("La clave generada es: "+clave);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean modMensualidad(int codigo, double mensualidad) {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE pisos SET mensualidad = ? WHERE codigo = ?");
			ps.setDouble(1, mensualidad);
			ps.setInt(2, codigo);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean cambiarEmpleadoPiso(Piso piso, int nif) {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE pisos SET nif_Empleado = ? WHERE codigo = ?");
			ps.setDouble(1, nif);
			ps.setInt(2, piso.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alquilar_noAlquilar(boolean alquilado, Piso piso) {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE pisos SET alquilado = ? WHERE codigo = ?");
			ps.setDouble(1, alquilado ? 1 : 0);
			ps.setInt(2, piso.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String mostrarEmpleadoPiso(int id) {
		Connection con = ConexionBD.getConex();
		String nombre = "";
		try {
			PreparedStatement ps = con.prepareStatement("SELECT e.nombre FROM pisos p "
					+ "JOIN empleados e ON p.nifEmpleado = e.nif " + "WHERE p.nif_Empleado = ?" + "");

			ps.setInt(2, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nombre = rs.getString("nombre");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return nombre;
		}

		return nombre;
	}

}
