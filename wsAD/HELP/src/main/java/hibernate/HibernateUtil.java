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
*/
}