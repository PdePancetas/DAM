package logic;

import java.math.BigDecimal;

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

	public static void cambiarSedeDeProy(int idProy, int idOldSede, int idNewSede) {
		
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {

			
			ProyectoSede proyectoSede = sesion.get(ProyectoSede.class, new ProyectoSedeId(idProy,idOldSede));
			ProyectoSede psNuevo = new ProyectoSede(new ProyectoSedeId(idProy, idNewSede), proyectoSede.getProyecto(), proyectoSede.getSede(), proyectoSede.getFInicio());
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

	public static void verSedesDeProyecto(int idProy) {
		Session sesion = HibernateUtil.getSession();
		

		try {

			Proyecto proy = sesion.get(Proyecto.class, idProy);
			
			System.out.println("Sedes en las que se trabaja en "+proy.getNomProy()+":");
			proy.getProyectoSedes().forEach(proyectoSede -> {
				ProyectoSedeId psId = proyectoSede.getId();
				Sede sede = sesion.get(Sede.class, psId.getIdSede());
				System.out.println(sede.getIdSede()+": "+sede.getNomSede());
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}
}
