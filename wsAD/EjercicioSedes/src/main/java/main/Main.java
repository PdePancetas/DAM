package main;

import java.sql.SQLException;
import org.hibernate.Session;
import utils.HibernateUtil;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		pruebaConfig();
		
	}
	private static void pruebaConfig() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.close();
	}

}
