package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from standard (hibernate.cfg.xml)
			// config file.
//			    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		Session session = sessionFactory.openSession();
		return session;
	}
/*
	public static Session beginTransaction() {
		Session session = getSession();
		session.beginTransaction();
		return session;
	}

	public static void commitTransaction(Session session) {
		session.getTransaction().commit();
	}

	public static void rollbackTransaction(Session session) {
		session.getTransaction().rollback();
	}
	
	
	 * Crear sesion:
	 * Session sesion = HibernateUtil.getSession(); ó HibernateUtil.getSessionFactory().openSession();
	 *   ** Si se va a modificar la bbdd por seguridad se inicia una transaccion
	 *   	Transaction tx = sesion.beginTransaction();
	 * 
	 * 	try {
	 * 		//Codigo y operaciones...
	 * 		
	 * 		tx.commit(); //Ejecutar las acciones
	 * 	} catch(Exception e) {
	 *  	tx.rollback(); 			//Retroceder los cambios en caso de error, por ejemplo en caso de que a medias 
	 *  	e.printStackTrace();	//de la ejecucion ocurra algun fallo, se revierten las operaciones hechas
	 *  } finally {
	 *  	sesion.close();			//Finalmente se cierra la sesion
	 *  }
	 * 
	 
	
	
*/
}