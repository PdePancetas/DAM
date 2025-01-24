package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory factory;

	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static Session getSession() {
		Session session = factory.getCurrentSession();
		return session;
	}

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

}