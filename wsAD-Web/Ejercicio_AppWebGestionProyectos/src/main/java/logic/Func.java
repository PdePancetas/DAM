package logic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.http.Part;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import models.Empleado;
import models.Empleados;

public class Func {

	public static Empleados leerFichero(Part part) throws JAXBException, IOException {
		InputStream inputStream = part.getInputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(Empleados.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso del inputStream asociado al part a objeto empleados
		Empleados empleados = (Empleados) unmarshaller.unmarshal(inputStream);

		return empleados;
	}

	public static boolean existeEmpleado(String dni, String tabla, Connection con) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM " + tabla + " WHERE dni = ?");
		ps.setString(1, dni);

		try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				// Si el resultado es mayor que 0, el empleado existe
				return rs.getInt(1) > 0;
			}
		}

		return false;
	}

	public static int crearProyecto(String nomProy, String dniJefe, ArrayList<String> empleados, Connection con)
			throws SQLException {
		// Insertar el proyecto en la tabla proyecto
		PreparedStatement ps = con.prepareStatement("INSERT INTO proyecto(nom_proy, dni_jefe_proy) VALUES(?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, nomProy);
		ps.setString(2, dniJefe);

		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		int clave = 0;
		while (rs.next())
			clave = rs.getInt(1);

		return clave;

	}

	public static void generarAsig_proy(String dni, int claveProyecto, Connection con) throws SQLException {
		// Insertar el proyecto en la tabla proyecto
		PreparedStatement ps = con.prepareStatement("INSERT INTO asig_proyecto VALUES(?,?)");
		ps.setString(1, dni);
		ps.setInt(2, claveProyecto);

		ps.executeUpdate();
	}

	public static void eliminarProyecto(int idProy, Connection con) throws SQLException {
		con.setAutoCommit(false);
		// Borramos de la tabla asig_proyecto los empleados asociados al proyecto
		// eliminado
		PreparedStatement ps = con.prepareStatement("DELETE FROM asig_proyecto WHERE id_proy = ?");
		ps.setInt(1, idProy);

		ps.executeUpdate();

		// Borramos de la tabla proyecto el proyecto eliminado
		ps = con.prepareStatement("DELETE FROM proyecto WHERE id_proy = ?");
		ps.setInt(1, idProy);

		ps.executeUpdate();

		con.commit();

	}

	public static List<String> obtenerIdNomProys(Connection con) throws SQLException {
		ArrayList<String> datosProyectos = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement("SELECT * FROM proyecto");

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			datosProyectos.add(String.valueOf(rs.getInt("id_proy")) + "," + rs.getString("nom_proy") + ","
					+ rs.getString("dni_jefe_proy"));

		return datosProyectos;
	}

	public static ArrayList<String> obtenerEmpProy(int idProy, Connection con) throws SQLException {
		ArrayList<String> dniEmpleados = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement("SELECT dni_emp FROM asig_proyecto WHERE id_proy = ?");
		ps.setInt(1, idProy);

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			dniEmpleados.add(rs.getString("dni_emp"));

		return dniEmpleados;
	}

	public static Empleado obtenerEmp(String dni, Connection con) throws SQLException {
		Empleado e = null;

		PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado WHERE dni = ?");
		ps.setString(1, dni);

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			e = new Empleado(rs.getString("dni"), rs.getString("nom_emp"));

		return e;
	}

}
