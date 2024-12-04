package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Empleado;
import connection.ConexionBD;

public class EmpleadoImpl implements EmpleadoDao {

	@Override
	public boolean addEmpleado(Empleado emp) {

		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO empleados VALUES(?,?,?)");
			ps.setInt(1, emp.getNif());
			ps.setString(2, emp.getNombre());
			ps.setDouble(3, emp.getSueldoBase());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public double sueldoEmpleado(int nif) {

		Connection con = ConexionBD.getConex();

		try {
			String sql = "SELECT e.nif, e.sueldoBase + (0.10 * SUM(p.mensualidad)) AS sueldo_total "
					+ "FROM empleados e " + "LEFT JOIN pisos p ON e.nif = p.nif_empleado " + "WHERE e.nif = " + nif
					+ " and " + "p.alquilado!=0 " + "GROUP BY e.nif, e.sueldoBase;";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, nif);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("id") + ": " + rs.getDouble("sueldo_total"));
			}

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return 0;
	}

	@Override
	public Empleado getMejorEmpleado() {

		Connection con = ConexionBD.getConex();
		Empleado e = null;
		try {
			String sql = "SELECT \r\n"
					+ "    e.nif,\r\n"
					+ "	   e.nombre,\r\n"
					+ "    e.sueldoBase,\r\n"
					+ "    COUNT(p.codigo) AS numPisos\r\n"
					+ "FROM \r\n"
					+ "    empleados e\r\n"
					+ "LEFT JOIN \r\n"
					+ "    pisos p ON e.nif = p.nif_Empleado\r\n"
					+ "WHERE p.alquilado !=0 "
					+ "GROUP BY \r\n"
					+ "    e.nif, e.sueldoBase\r\n"
					+ "ORDER BY \r\n"
					+ "    numPisos DESC\r\n"
					+ "LIMIT 1;\r\n";
					

			PreparedStatement ps = con.prepareStatement(sql);
			

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				e = new Empleado(rs.getInt("nif"), rs.getString("nombre"), rs.getDouble("sueldoBase"));
			}

			ps.executeQuery();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return e;
	}

}
