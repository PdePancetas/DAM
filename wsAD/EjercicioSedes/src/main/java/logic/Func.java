package logic;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

	public static void insertEmpleado() {

		Empleado emp = new Empleado();

		System.out.print("Dni del empleado: ");
		String dni = Teclado.leerCadena();
		emp.setDni(dni);
		System.out.print("Nombre del empleado: ");
		String nomEmp = Teclado.leerCadena();
		emp.setNomEmp(nomEmp);
		System.out.print("Id de su departamento: ");
		int idDep = Teclado.leerEntero();

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		try {

			Departamento dep = sesion.get(Departamento.class, idDep);
			if (dep == null) {
				dep = new Departamento();
				dep.setIdDepto(idDep);
				System.err.println("No existe ese departamento, introduce sus datos: ");
				System.out.print("Nombre del departamento: ");
				String nomDep = Teclado.leerCadena();
				dep.setNomDepto(nomDep);

				System.out.print("Id sede: ");
				int idSede = Teclado.leerEntero();
				Sede sede = sesion.get(Sede.class, idSede);

				if (sede == null) {
					sede = new Sede();
					sede.setIdSede(idSede);
					System.err.println("No existe esa sede, introduce sus datos: ");
					System.out.print("Nombre de la sede: ");
					String nomSede = Teclado.leerCadena();
					sede.setNomSede(nomSede);

					sesion.persist(sede); // Guardamos primero la Sede
				}

				dep.setSede(sede);
				sesion.persist(dep); // Ahora guardamos el Departamento
			}

			emp.setDepartamento(dep);
			sesion.persist(emp); // Finalmente guardamos el Empleado

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	public static void insertSedeEnProyecto() {

		Session sesion = HibernateUtil.getSession();
	    Transaction tx = sesion.beginTransaction();
	    try {
	        System.out.print("Nombre del proyecto: ");
	        String nomProy = Teclado.leerCadena();

	        
	        List<Proyecto> proyectos = 
	        		sesion.createQuery("FROM Proyecto WHERE nomProy = :nombre", Proyecto.class)
	        			.setParameter("nombre", nomProy)
	        			.getResultList();

	        Proyecto proyecto;
	        if (proyectos.isEmpty()) {
	            System.err.println("No se encontró un proyecto con ese nombre.");
	            return;
	        } else if (proyectos.size() > 1) {
	            System.out.println("Se encontraron múltiples proyectos con ese nombre. Seleccione el ID:");
	            for (Proyecto p : proyectos) {
	                System.out.println("ID: " + p.getIdProy() + " | Nombre: " + p.getNomProy());
	            }
	            int idProy = Teclado.leerEntero();
	            proyecto = sesion.get(Proyecto.class, idProy);
	            if (proyecto == null) {
	                System.err.println("ID de proyecto no válido.");
	                return;
	            }
	        } else {
	            proyecto = proyectos.get(0);
	        }

	        
	        System.out.print("ID de la nueva sede: ");
	        int idSede = Teclado.leerEntero();

	        Sede sede = sesion.get(Sede.class, idSede);
	        if (sede == null) {
	            sede = new Sede();
	            sede.setIdSede(idSede);
	            System.out.print("Nombre de la nueva sede: ");
	            String nombreSede = Teclado.leerCadena();
	            sede.setNomSede(nombreSede);
	            sesion.persist(sede);
	        }
	        
	        ProyectoSedeId psId = new ProyectoSedeId(proyecto.getIdProy(), sede.getIdSede());
	        ProyectoSede ps = new ProyectoSede(psId, proyecto, sede, new Date());

	        sesion.persist(ps);

	        tx.commit();
	        System.out.println("Sede agregada exitosamente al proyecto.");
	    } catch (Exception e) {
	        tx.rollback();
	        e.printStackTrace();
	    } finally {
	        sesion.close();
	    }
        
	}

}
