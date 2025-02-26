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
		double sueldo = 0;
		try {
			/*String sql = "SELECT e.nif, e.sueldoBase + 0.10 * COALESCE(SUM(p.mensualidad), 0) AS sueldo_total "
					+ "FROM empleados e " + "LEFT JOIN pisos p ON e.nif = p.nif_empleado " + "WHERE e.nif = ? "
					+ "GROUP BY e.nif, e.sueldoBase;";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, nif);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("nif") + ": " + rs.getDouble("sueldo_total"));
			}

			ps.executeQuery();
			 */
			
			
			// Primero, obtener el sueldo base del empleado
			String sqlBase = "SELECT e.sueldoBase FROM empleados e WHERE e.nif = ?";
			PreparedStatement psBase = con.prepareStatement(sqlBase);
			psBase.setInt(1, nif);  // 'nifEmpleado' es el NIF del empleado que deseas consultar
			ResultSet rsBase = psBase.executeQuery();

			double sueldoBase = 0;
			while (rsBase.next()) {
			    sueldoBase = rsBase.getDouble("sueldoBase");
			    System.out.println("Sueldo Base: " + sueldoBase);
			}

			// Luego, obtener el 10% de las mensualidades de los pisos alquilados
			String sqlPisos = "SELECT 0.10 * SUM(p.mensualidad) AS sueldoPorPisos " +
			                  "FROM pisos p WHERE p.nif_Empleado = "+nif+" AND p.codigo = 1";  // Solo los pisos alquilados
			PreparedStatement psPisos = con.prepareStatement(sqlPisos);
			ResultSet rsPisos = psPisos.executeQuery();

			double sueldoPorPisos = 0;
			while (rsPisos.next()) {
			    sueldoPorPisos = rsPisos.getDouble("sueldoPorPisos");
			    System.out.println("Sueldo por Pisos (10% mensualidad de pisos alquilados): " + sueldoPorPisos);
			}

			// Finalmente, puedes calcular el sueldo total si lo deseas
			double sueldoTotal = sueldoBase + sueldoPorPisos;
			System.out.println("Sueldo Total: " + sueldoTotal);

		} catch (SQLException e) {
			e.printStackTrace();
			return sueldo;
		}

		return sueldo;
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
	
	public Empleado getEmpleado(int nif) {
		
		Connection con = ConexionBD.getConex();
		
		Empleado e = null;
		try {
			String sql = "SELECT * FROM empleados WHERE nif = ?";
					

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, nif);

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
