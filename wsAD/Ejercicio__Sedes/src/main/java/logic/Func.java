package logic;

import java.sql.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import beans.Proyecto;
import beans.ProyectoSede;

public class Func {
	
	
	
	public static String incorporarProyecto(Date FInicio, Date FFin, String nomProy, Set<ProyectoSede> proyectoSedes) {
		
		
		Session session = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Proyecto.class)
				.buildSessionFactory()
				.openSession();
		
		try {
			session.beginTransaction();
			Proyecto proy = new Proyecto(FInicio, FFin, nomProy, null);
			session.persist(proy);			session.getTransaction().commit();
			return "Proyecto creado";
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "Error al registrar proyecto";
		
	}
	
	
}
