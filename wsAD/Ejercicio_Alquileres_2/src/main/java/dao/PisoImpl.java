package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Piso;
import connection.ConexionBD;

public class PisoImpl implements PisoDao {

	@Override
	public boolean addPiso(Piso piso) {
		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO pisos VALUES(?,?,?,?,?)");
			ps.setInt(1, piso.getId());
			ps.setString(2, piso.getDireccion());
			ps.setDouble(3, piso.getMensualidad());
			ps.setBoolean(4, false);
			ps.setInt(4, piso.getNif_Empleado());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean modMensualidad(Piso piso, double mensualidad) {

		return false;
	}

	@Override
	public boolean cambiarEmpleadoPiso(Piso piso, int nif) {

		return false;
	}

	@Override
	public boolean alquilar_noAlquilar(boolean alquilado, Piso piso) {

		return false;
	}

	@Override
	public String mostrarEmpleadoPiso(int id) {

		return null;
	}

}
