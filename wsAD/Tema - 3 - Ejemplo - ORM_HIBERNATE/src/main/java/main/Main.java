package main;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.ConfigHelper;

import com.mysql.cj.xdevapi.SessionFactory;

import beans.Empleado;
import beans.Proyecto;
import utilidadesTeclado.Teclado;
import utils.HibernateUtil;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws SQLException {

//		System.out.print("Dni: ");
//		String dni = Teclado.leerCadena();
//		proyectosTrabajaEmpleado(dni);
		
//		System.out.print("Dni: ");
//		String dni = Teclado.leerCadena();
//		System.out.print("Nombre: ");
//		String nombre = Teclado.leerCadena();
//		insertEmpleado(dni, nombre);
		
//		System.out.print("Dni: ");
//		String dni = Teclado.leerCadena();
//		deleteEmpleado(dni);
		
		System.out.print("Dni Empleado: ");
		String dniEmp = Teclado.leerCadena();
		System.out.print("Id Proyecto: ");
		int idProy = Teclado.leerEntero();
		asigEmpleado(dniEmp, idProy);
		
	}

	private static void proyectosTrabajaEmpleado(String dni) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		
		Empleado emp = session.get(Empleado.class, dni);
		//a partir de aqui, este emp es un objeto persistente
		
		Empleado emp2 = new Empleado();
		emp2.setDni(dni);//Este emp2 no es persistente, va por libre (es transitorio)
		
		
		emp.getProyectosTrabaja().stream().forEach(System.out::println);
		
		tx.commit();
		session.close();
	}
	
	private static void insertEmpleado(String dni, String nombre) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		
		Empleado emp = new Empleado(dni, nombre);
		
		session.persist(emp);
		
		tx.commit();
		session.close();
	}
	
	private static void deleteEmpleado(String dni) {
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Empleado emp = session.get(Empleado.class, dni);
		
		session.remove(emp);
		
		tx.commit();
		session.close();
	}

	private static void asigEmpleado(String dniEmp, int idProy) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Empleado emp = session.get(Empleado.class, dniEmp);
		Proyecto proy = new Proyecto();
		proy.setIdProy(idProy);
		
		emp.getProyectosTrabaja().add(proy);
		
		tx.commit();
		session.close();
	}
}
