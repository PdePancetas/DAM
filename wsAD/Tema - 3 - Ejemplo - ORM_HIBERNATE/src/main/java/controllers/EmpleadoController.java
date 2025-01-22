package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.Empleado;
//CRUD Empleado
public class EmpleadoController {

	public String createEmpleado(String dni, String nom_emp) {
		// Creamos un factory con la configuración del archivo creado anteriormente de
		// hibernate
		// y le añadimos la clase empleado para iniciar una sesión
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class)
				.buildSessionFactory();

		// Abrimos la sesión
		Session session = factory.openSession();

		try {

			session.beginTransaction();// Indicamos que se comenzará una transacción a la base de datos
			Empleado empleado = new Empleado(dni, nom_emp);// Creamos un objeto empleado con los parámetros pasados
			session.persist(empleado);// Método para cargar en la memoria de la transacción la inserción de un objeto
										// (save está en desuso)
			session.getTransaction().commit();// Ejecuto la transacción
			factory.close();// Cierro el factory
			

			return "Empleado creado";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// En caso de que haya algún error, se devolverá este mensaje
		return "Error al registrar empleado";
	}

	public String deleteEmpleado(String dni) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class)
				.buildSessionFactory();

		// Abrimos la sesión
		Session session = factory.openSession();

		try {

			session.beginTransaction();// Indicamos que se comenzará una transacción a la base de datos
			Empleado empleado = session.get(Empleado.class, dni); // Busca en la bbdd el objecto tipo empleado con dni
																	// especificado
			session.remove(empleado);// Método para cargar en la memoria de la transacción la inserción de un objeto
										// (delete está en desuso)
			session.getTransaction().commit();// Ejecuto la transacción
			factory.close();// Cierro el factory

			return "Empleado eliminado";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Error al eliminar empleado";
	}

	public String updateNombreEmpleado(String dni, String nombre) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class)
				.buildSessionFactory();

		// Abrimos la sesión
		Session session = factory.openSession();

		try {

			session.beginTransaction();// Indicamos que se comenzará una transacción a la base de datos
			Empleado empleado = session.get(Empleado.class, dni); // Busca en la bbdd el objecto tipo empleado con dni
																	// especificado
			empleado.setNomEmp(nombre);// Le asignamos los parámetros como nuevos datos
			session.merge(empleado);// Método para cargar en la memoria de la transacción la inserción de un objeto
									// (update está en desuso)
			session.getTransaction().commit();// Ejecuto la transacción
			factory.close();// Cierro el factory

			return "Empleado actualizado";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Error al actualizar empleado";
	}

	public String getEmpleado(String dni) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class)
				.buildSessionFactory();

		// Abrimos la sesión
		Session session = factory.openSession();

		try {

			session.beginTransaction();// Indicamos que se comenzará una transacción a la base de datos
			Empleado empleado = session.get(Empleado.class, dni); // Busca en la bbdd el objecto tipo empleado con dni
																	// especificado
			session.getTransaction().commit();// Ejecuto la transacción
			factory.close();// Cierro el factory

			return empleado.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Error al obtener empleado";
	}

}
