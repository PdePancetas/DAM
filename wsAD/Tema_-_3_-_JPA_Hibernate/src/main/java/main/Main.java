package main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Departamento;
import modelo.Empleado;
import utils.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		addDepartamento("INFORMATICA", "Prueba de notas transitorias");

		insertEmpleado(2, "María José");
		
//		listaEmpleados(2);
	}

	private static void addDepartamento(String nomDepto, String notas) {
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {
			Departamento dep = new Departamento();
			dep.setNomDepto(nomDepto);
			dep.setNotas(notas);

			sesion.persist(dep);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}

	}

	private static void insertEmpleado(int id_depto, String nomEmp) {
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {

			Empleado emp = new Empleado();
			emp.setNombre(nomEmp);

//			Departamento d = sesion.get(Departamento.class, id_depto);
			Departamento dep = new Departamento();
			dep.setId(id_depto);
			emp.setDepartamento(dep);

			sesion.persist(emp);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}
	
	private static void listaEmpleados(int id) {
		Session sesion = HibernateUtil.getSession();

		try {


			Departamento d = sesion.get(Departamento.class, id);
			
			d.getEmpleados().forEach(System.out::println);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}
}
