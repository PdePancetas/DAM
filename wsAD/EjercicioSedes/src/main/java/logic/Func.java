package logic;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Empleado;
import modelo.EmpleadoDatosProf;
import modelo.Proyecto;
import modelo.ProyectoSede;
import modelo.ProyectoSedeId;
import modelo.Sede;
import utilidadesTeclado.Teclado;
import utils.HibernateUtil;

public class Func {

	public static String insertProyecto() throws ParseException {

		System.out.println("Nombre Proyecto: ");
		String nombre = Teclado.leerCadena();
		System.out.println("Fecha inicio: ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
		Date fechaInicio = sdf.parse(Teclado.leerCadena());

		System.out.println("Fecha fin: ");
		Date fechaFin = sdf.parse(Teclado.leerCadena());

		Proyecto proy = new Proyecto(fechaInicio, fechaFin, nombre);

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {

			session.persist(proy);

			System.out.println("Ids de las sedes asociadas(1,2,3,4,...): ");
			String idsSedes = Teclado.leerCadena();

			for (int i = 0; i < idsSedes.split(",").length; i++) {
				System.out.println("Id sede " + idsSedes.split(",")[i] + ": ");
				String nombreSede = Teclado.leerCadena();
				ProyectoSedeId proyectoSedeId = new ProyectoSedeId(proy.getIdProy(),
						Integer.parseInt(idsSedes.split(",")[i]));
				ProyectoSede proyectoSede = new ProyectoSede();
				proyectoSede.setId(proyectoSedeId);
				proyectoSede.setFInicio(fechaInicio);
				proyectoSede.setFFin(fechaFin);

				proy.getProyectoSedes().add(proyectoSede);
			}

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

}
