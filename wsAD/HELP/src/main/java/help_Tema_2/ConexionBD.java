package help_Tema_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import properties.Properties;

public class ConexionBD {

	private Connection conex = null;

	public Connection getConex() {
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

	public void setConex(Connection conexion) {
		this.conex = conexion;
	}

}
