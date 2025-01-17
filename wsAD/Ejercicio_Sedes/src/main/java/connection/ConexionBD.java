package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import properties.Properties;

//CLASE CON PATRÓN SINGLETON
public class ConexionBD {

	private static Connection conex = null;

	public static Connection getConex() {
		if (conex != null)
			return conex;
		
		String url = Properties.getConfig().getProperty("url");
		String user = Properties.getConfig().getProperty("user");
		String pw = Properties.getConfig().getProperty("password");
		try {
			conex = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conex;
	}

	public static void setConex(Connection conexion) {
		conex = conexion;
	}

}
