package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

		System.out.println("Fecha inicio: ");
		Date fechaFin = sdf.parse(Teclado.leerCadena());
		
		Proyecto proy = new Proyecto(fechaInicio, fechaFin, nombre);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {

			session.persist(proy);
			
			System.out.println("Ids de las sedes asociadas(1,2,3,4,...): ");
			String idsSedes = Teclado.leerCadena();
			
			for(int i=0;i<idsSedes.split(",").length;i++) {
				System.out.println("Nombre sede "+idsSedes.split(",")[i]+": ");
				int idSede = Teclado.leerEntero();
				ProyectoSedeId proyectoSedeId = new ProyectoSedeId(proy.getIdProy(), idSede);
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
	
	

}
