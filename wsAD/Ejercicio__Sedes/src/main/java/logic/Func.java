package logic;

import java.sql.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Proyecto;
import beans.ProyectoSede;
import utils.HibernateUtil;

public class Func {

	public static String incorporarProyecto(Date FInicio, Date FFin, String nomProy, Set<ProyectoSede> proyectoSedes) {

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			Proyecto proy = new Proyecto(FInicio, FFin, nomProy, null);
			session.persist(proy);
			tx.commit();
			return "Proyecto creado";
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

		return "Error al registrar proyecto";

	}

}
