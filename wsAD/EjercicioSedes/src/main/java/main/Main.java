package main;

import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.Session;

import logic.Func;
import utils.HibernateUtil;

public class Main {

	public static void main(String[] args) throws SQLException {
		
//		pruebaConfig();
//		try {
//			Func.insertProyecto();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		Func.insertEmpleadoDatosProf();
	}
	private static void pruebaConfig() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.close();
	}

}
