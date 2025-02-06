package logic;

import java.math.BigDecimal;
import java.util.Comparator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Departamento;
import modelo.Empleado;
import modelo.EmpleadoDatosProf;
import modelo.Proyecto;
import modelo.ProyectoSede;
import modelo.ProyectoSedeId;
import modelo.Sede;
import utilidadesTeclado.Teclado;
import utils.HibernateUtil;

public class FuncExtra {

	/**
	 * – cambiar empleado de departamento
	 * 
	 * @param dniEmp
	 * @param idNewDep
	 */
	public static void cambiarDepDeEmpleado(int dniEmp, int idNewDep) {

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {

			Empleado emp = sesion.get(Empleado.class, dniEmp);
			Departamento dep = new Departamento();
			dep.setIdDepto(idNewDep);
			emp.setDepartamento(dep);
			sesion.merge(emp);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}

	}

	/**
	 * – cambiar proyecto de sede
	 * 
	 * @param idProy
	 * @param idOldSede
	 * @param idNewSede
	 */
	public static void cambiarSedeDeProy(int idProy, int idOldSede, int idNewSede) {

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {

			ProyectoSede proyectoSede = sesion.get(ProyectoSede.class, new ProyectoSedeId(idProy, idOldSede));
			ProyectoSede psNuevo = new ProyectoSede(new ProyectoSedeId(idProy, idNewSede), proyectoSede.getProyecto(),
					proyectoSede.getSede(), proyectoSede.getFInicio());
			sesion.remove(proyectoSede);

			sesion.persist(psNuevo);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}

	}

	/**
	 * – dado un proyecto, ver en qué sedes se trabaja
	 * 
	 * @param idProy
	 */
	public static void verSedesDeProyecto(int idProy) {
		Session sesion = HibernateUtil.getSession();

		try {

			Proyecto proy = sesion.get(Proyecto.class, idProy);

			System.out.println("Sedes en las que se trabaja en " + proy.getNomProy() + ":");
			proy.getProyectoSedes().forEach(proyectoSede -> {
				ProyectoSedeId psId = proyectoSede.getId();
				Sede sede = sesion.get(Sede.class, psId.getIdSede());
				System.out.println(sede.getIdSede() + ": " + sede.getNomSede());
			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	/**
	 * – consultar todas las sedes en las que hay departamentos con un nombre dado
	 * 
	 * @param nomDepto
	 */
	public static void consultarSedesDadoApartamento(String nomDepto) {
		Session sesion = HibernateUtil.getSession();

		try {

			sesion.createQuery("FROM Sede", Sede.class).getResultList().stream()
					.filter(sede -> !sede.getDepartamentos().isEmpty()).filter(sede -> {
						for (Departamento d : sede.getDepartamentos()) {
							if (d.getNomDepto().equals(nomDepto))
								return true;
						}
						return false;
					}).forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	/**
	 * – insertar los datos profesionales de un empleado
	 */
	public static void insertEmpleadoDatosProf() {
		System.out.print("Dni del empleado: ");
		String dni = Teclado.leerCadena();
		System.out.print("Categoría: ");
		String categoria = Teclado.leerCadena();
		System.out.print("Sueldo bruto anual: ");
		BigDecimal sueldo = new BigDecimal(Teclado.leerDecimal());

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		Empleado emp = sesion.get(Empleado.class, dni);

		if (emp.getEmpleadoDatosProf() == null) {
			System.out.println("Se insertarán sus datos profesionales");
			EmpleadoDatosProf datosProf = new EmpleadoDatosProf(emp, categoria, sueldo);
			sesion.persist(datosProf);
		} else {
			System.out.print(
					"Este empleado ya tiene datos profesionales,seguro que quieres sobreescribir los datos? (y/n)");

			boolean valido = false;
			while (!valido) {
				String opcion = Teclado.leerCadena();
				switch (opcion) {
				case "y":
					emp.getEmpleadoDatosProf().setCategoria(categoria);
					emp.getEmpleadoDatosProf().setSueldoBrutoAnual(sueldo);
					valido = true;
					break;
				case "n":
					valido = true;
					break;
				default:
					System.out.println("Introduce una opcion valida");
					valido = false;
				}
			}
		}
		tx.commit();
		sesion.close();
	}

	/**
	 * – mostrar la sede con más empleados asociados
	 */
	public static void sedeConMasEmpleados() {
		Session sesion = HibernateUtil.getSession();

		try {
			Sede s = sesion.createQuery("FROM Sede", Sede.class).getResultList().stream()
					.max(Comparator.comparingInt(sede -> sede.getDepartamentos().stream()
							.mapToInt(d -> d.getEmpleados().size()).sum()))
					.get();
			
			System.out.println(s);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}
}
