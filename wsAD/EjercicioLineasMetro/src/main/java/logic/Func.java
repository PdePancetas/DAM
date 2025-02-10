package logic;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;

import modelo.Cochera;
import modelo.Linea;
import modelo.Tren;
import utils.HibernateUtil;

public class Func {

	/**
	 * 1. Modificar el nombre de la cochera en la que se guardará un tren.
	 * 
	 * @param id_cochera
	 * @param nuevoNombre
	 */
	public static void cambiarNombreCochera(Integer cod_cochera, String nuevoNombre) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		try {
			Cochera c = s.get(Cochera.class, cod_cochera);
			c.setNombre(nuevoNombre);
			s.merge(c);
			s.getTransaction().commit();
		} catch (Exception e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	public static void cambiarNombreCocheraPorTren(Integer codTren, String nuevoNombre){
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		try {
			Tren t = s.get(Tren.class, codTren);
			Cochera c = s.get(Cochera.class, t.getCochera().getCodCochera());
			c.setNombre(nuevoNombre);
			s.merge(c);
			s.getTransaction().commit();
		} catch (Exception e) {
			s.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	/**
	 * 2. Mostrar la línea en la que prestan servicio más trenes.
	 */
	public static List<Linea> lineaConMasTrenes() {
		Session s = HibernateUtil.getSession();

		try {

			Linea maxLinea = s.createQuery("FROM Linea", Linea.class).getResultList().stream()
					.max(Comparator.comparingInt(l -> l.getTrenes().size())).get();
			
			return s.createQuery("FROM Linea", Linea.class).getResultList().stream()
					.filter(l -> l.getTrenes().size() == maxLinea.getTrenes().size()).toList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}

	}

}
