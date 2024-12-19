package logic;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import connection.ConexionBD;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import models.Empleado;
import models.Empleados;
import models.Proyecto;

public class Func {

	/**
	 * Utilizado para obtener el objeto Empleados desde el part recibido (el archivo
	 * .xml que contiene los empleados)
	 * 
	 * @param part
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static Empleados leerFichero(Part part) throws JAXBException, IOException {
		InputStream inputStream = part.getInputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(Empleados.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso del inputStream asociado al part a objeto empleados
		Empleados empleados = (Empleados) unmarshaller.unmarshal(inputStream);

		return empleados;
	}

	/**
	 * Devuelve un true/false dependiendo de si el empleado con dni especificado
	 * existe en la tabla especificada (la tabla debería contener el campo dni)
	 * 
	 * @param dni
	 * @param tabla
	 * @param con
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * Crea el proyecto con nombre, dniJefe y lista de empleados especificados en la
	 * tabla proyecto (el id de proyecto no se requiere ya que se crea de manera
	 * autoincremental en la base de datos)
	 * 
	 * @param nomProy
	 * @param dniJefe
	 * @param empleados
	 * @param con
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * Genera una fila en la tabla asig_proyecto con clave(dni del empleado, id del
	 * proyecto) especificada
	 * 
	 * @param dni
	 * @param claveProyecto
	 * @param con
	 * @throws SQLException
	 */
	public static void generarAsig_proy(String dni, int claveProyecto, Connection con) throws SQLException {
		// Insertar el proyecto en la tabla proyecto
		PreparedStatement ps = con.prepareStatement("INSERT INTO asig_proyecto VALUES(?,?)");
		ps.setString(1, dni);
		ps.setInt(2, claveProyecto);

		ps.executeUpdate();
	}

	/**
	 * Elimina el proyecto de la tabla proyecto, habiendo eliminado antes todas las
	 * referencias de la tabla asig_proyecto
	 * 
	 * @param idProy
	 * @param con
	 * @throws SQLException
	 */
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

	/**
	 * Devuelve una lista de id de todos los proyectos de la tabla proyecto (Para
	 * mostrar por ejemplo al querer eliminar un proyecto)
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static List<String> obtenerIdNomProys(Connection con) throws SQLException {
		ArrayList<String> datosProyectos = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement("SELECT * FROM proyecto");

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			datosProyectos.add(String.valueOf(rs.getInt("id_proy")) + "," + rs.getString("nom_proy") + ","
					+ rs.getString("dni_jefe_proy"));

		return datosProyectos;
	}

	/**
	 * Devuelve la lista de dni de empleados relacionados con un proyecto cuyo id se especifica
	 * Apunte: En el método obtenerNombreTabla(), el código se comporta de tal forma que, conociendo 
	 * los campos de la tabla a la que se debe acceder, sin conocer el nombre de la misma, se pueda obtener 
	 * el nombre para ejecutar sentencias SQL sobre la tabla
	 * @param idProy
	 * @param con
	 * @param servletContext
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> obtenerEmpProy(int idProy, Connection con, ServletContext servletContext)
			throws SQLException {
		ArrayList<String> dniEmpleados = new ArrayList<>();
		String tabla = obtenerNombreTabla(new ArrayList<>(Arrays.asList("dni_emp", "id_proy")), servletContext);
		PreparedStatement ps = con.prepareStatement("SELECT dni_emp FROM " + tabla + " WHERE id_proy = ?");
		ps.setInt(1, idProy);

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			dniEmpleados.add(rs.getString("dni_emp"));

		return dniEmpleados;
	}
	
	/**
	 * Devuelve la lista de dni de los empleados existentes en la tabla empleado
	 * @param con
	 * @param servletContext
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> obtenerDnisEmp(Connection con, ServletContext servletContext) throws SQLException {
	    ArrayList<String> dnis = new ArrayList<>();

	    PreparedStatement ps = con.prepareStatement("SELECT dni FROM empleado");
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        dnis.add(rs.getString("dni"));
	    }

	    return dnis;
	}

	/**
	 * Devuelve el objeto empleado con todos sus datos de la tabla empleado con un dni especificado
	 * @param dni
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static Empleado obtenerEmp(String dni, Connection con) throws SQLException {
		Empleado e = null;

		PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado WHERE dni = ?");
		ps.setString(1, dni);

		ResultSet rs = ps.executeQuery();
		while (rs.next())
			e = new Empleado(rs.getString("dni"), rs.getString("nom_emp"));

		return e;
	}

	/**
	 * Devuelve la lista de id de los proyectos existentes en la tabla proyecto
	 * @param con
	 * @param servletContext
	 * @return
	 * @throws SQLException
	 */
	public static List<String> obteneridProys(Connection con, ServletContext servletContext) throws SQLException {

		List<String> ids = new ArrayList<String>();

		PreparedStatement ps = con.prepareStatement("SELECT id_proy FROM proyecto");
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			ids.add(rs.getString("id_proy"));

		return ids;
	}

	/**
	 * Genera una lista de objectos Proyecto de la base de datos, teniendo que acceder a la tabla
	 * proyecto para obtener los datos de cada proyecto además de a la tabla asig_proyecto
	 * para que a cada dni, se busque en la tabla empleado los datos del empleado correspondiente 
	 * y se les asigne al proyecto en el que están trabajando
	 * @param request
	 * @param servletContext
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<Proyecto> genProy(HttpServletRequest request, ServletContext servletContext)
			throws ClassNotFoundException, SQLException {

		List<Empleado> empleados = new ArrayList<Empleado>();
		List<Proyecto> proyectos = new ArrayList<Proyecto>();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = ConexionBD.getConex(servletContext);
		List<String> datosProyectos = Func.obtenerIdNomProys(con);
		List<String> dniEmpleados = new ArrayList<>();
		for (int idProy : datosProyectos.stream().map(datos -> Integer.parseInt(datos.split(",")[0])).toList()) {
			dniEmpleados
					.add(Func.obtenerEmpProy(idProy, con, servletContext).stream().collect(Collectors.joining(",")));
		}

		for (String datos : datosProyectos) {
			proyectos
					.add(new Proyecto(Integer.parseInt(datos.split(",")[0]), datos.split(",")[1], datos.split(",")[2]));
		}

		for (int i = 0; i < proyectos.size(); i++) {
			empleados = new ArrayList<Empleado>();
			String[] empleadosData = dniEmpleados.get(i).split(",");
			for (int j = 0; j < dniEmpleados.get(i).split(",").length; j++) {
				empleados.add(Func.obtenerEmp(empleadosData[j], con));
			}

			proyectos.get(i).setEmpleados(empleados);
		}
		return proyectos;
	}

	/**
	 * Devuelve el nombre de la tabla cuyos campos o columnas coincidan exactamente con la lista de 
	 * campos especificada, dentro de la base de datos 'proyectos'
	 * @param campos
	 * @param servletContext
	 * @return
	 * @throws SQLException
	 */
	public static String obtenerNombreTabla(ArrayList<String> campos, ServletContext servletContext)
			throws SQLException {
		String nombreTabla = "";

		Connection con = ConexionBD.getConex(servletContext);
		DatabaseMetaData mtdt = con.getMetaData();

		ResultSet tablas = mtdt.getTables(null, "proyectos", null, null);

		while (tablas.next()) {
			ArrayList<String> columnas = new ArrayList<>();
			String tableName = tablas.getString(3); // La tercera columna de datos es el nombre de la tabla
			ResultSet columns = mtdt.getColumns(null, "proyectos", tableName, null);
			while (columns.next()) {
				columnas.add(columns.getString(4)); // La cuarta columna de datos es el nombre de la columna
			}
			if (campos.equals(columnas)) {
				nombreTabla = tableName;
				return nombreTabla;
			}
		}

		return nombreTabla;
	}

}
