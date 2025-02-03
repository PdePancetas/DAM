package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import modelo.Departamento;
import modelo.Departamentos;
import modelo.Empleado;
import modelo.EmpleadoDatosProf;
import modelo.Proyecto;
import modelo.ProyectoSede;
import modelo.ProyectoSedeId;
import modelo.Sede;
import utilidadesTeclado.Teclado;
import utils.HibernateUtil;

public class Func {

	// tipo es la propiedad del fichero
	public static File getFichero(String tipo) throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("config.properties"));
		File f = new File(configuracion.getProperty(tipo));

		return f;
	}

	private static void escribirFicheroXML(Document doc, String tipo)
			throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(getFichero(tipo));

		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(source, result);
	}

	public static Departamentos leerFichero(File f) throws JAXBException {
		// Leer el documento xml (leer contenido y pasar a objeto Libros)

		// Contexto : Clase Raiz
		JAXBContext jaxbContext = JAXBContext.newInstance(Departamentos.class);

		// Como el parser
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Traspaso de fichero a objeto
		Departamentos departamentos = (Departamentos) unmarshaller.unmarshal(f);

		return departamentos;
	}

	public static String insertProyecto() {

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		try {
			System.out.println("Nombre Proyecto: ");
			String nombre = Teclado.leerCadena();
			System.out.println("Fecha inicio: ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
			Date fechaInicio = sdf.parse(Teclado.leerCadena());

			System.out.println("Fecha fin: ");
			Date fechaFin = sdf.parse(Teclado.leerCadena());

			Proyecto proy = new Proyecto(fechaInicio, fechaFin, nombre);

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
				session.persist(proyectoSede);
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

		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		try {

			Departamento dep = sesion.get(Departamento.class, idDep);
			if (dep == null) {
				Departamento depNuevo = new Departamento();
				System.out.println("No existe ese departamento, introduce sus datos: ");
				System.out.print("Nombre del departamento: ");
				String nomDep = Teclado.leerCadena();
				depNuevo.setNomDepto(nomDep);

				System.out.print("Id sede: ");
				int idSede = Teclado.leerEntero();
				Sede sede = sesion.get(Sede.class, idSede);
				Sede sedeNueva = null;
				if (sede == null) {
					sedeNueva = new Sede();
					System.out.println("No existe esa sede, introduce sus datos: ");
					System.out.print("Nombre de la sede: ");
					String nomSede = Teclado.leerCadena();
					sedeNueva.setNomSede(nomSede);

					sesion.persist(sedeNueva); // Guardamos primero la Sede
					depNuevo.setSede(sedeNueva);

				} else {

					depNuevo.setSede(sede);

				}
				sesion.persist(depNuevo); // Ahora guardamos el Departamento
				emp.setDepartamento(depNuevo);

			} else {
				emp.setDepartamento(dep);
			}

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

			List<Proyecto> proyectos = sesion.createQuery("FROM Proyecto WHERE nomProy = :nombre", Proyecto.class)
					.setParameter("nombre", nomProy).getResultList();

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

	public static void asociarProyectoYSede(int idProyecto, int idSede) {

		ProyectoSedeId psid = new ProyectoSedeId(idProyecto, idSede);
		ProyectoSede ps = new ProyectoSede();
		ps.setId(psid);

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		try {
			// No hace falta porque solo se necesita asociar el id, no el objeto entero
//			Proyecto p = sesion.get(Proyecto.class, idProyecto);
//			Sede s = sesion.get(Sede.class, idSede);
//			ps.setProyecto(p);
//			ps.setSede(s);
//			
			ps.setFInicio(new Date());

			sesion.persist(ps);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	public static void eliminarSede(int idSede) {

		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		try {
			Sede sede = sesion.get(Sede.class, idSede);
			sesion.remove(sede);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}

	}

	// USO DE HQL (Hibernate Query Language)
	// Necesario para cualquier consulta distinta a las
	// consultas por clave primaria (session.get(_.class,id))
	// HQL CONSULTA CAMPOS DENTRO DE TABLAS, NO DE TABLAS
	public static List<Sede> masProyectos() {

		Session sesion = HibernateUtil.getSession();
		try {

//			List<Sede> sedes = sesion.createQuery("FROM Sede", Sede.class).stream()
//					.filter(sede -> sede.getProyectoSedes() != null).toList();

			List<Sede> sedes = sesion.createQuery("FROM Sede", Sede.class).getResultList().stream()
					.filter(sede -> sede.getProyectoSedes() != null).toList();

			int max = sedes.stream().map(x -> x.getProyectoSedes().size()).max(Comparator.naturalOrder()).get();

			return sedes.stream().filter(x -> x.getProyectoSedes().size() == max).toList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sesion.close();
		}
	}

	/// TERMINAR
	public static Departamento depMayorGastoSueldo() {
		Session sesion = HibernateUtil.getSession();

		try {

			List<Departamento> deps = sesion.createQuery("FROM Departamento", Departamento.class).stream()
					.filter(dep -> dep.getEmpleados() != null).toList();

			Departamento dep = deps.stream()
					.max(Comparator.comparingDouble(d -> d.getEmpleados().stream()
							.filter(emp -> emp.getEmpleadoDatosProf() != null)
							.mapToDouble(emp -> emp.getEmpleadoDatosProf().getSueldoBrutoAnual().doubleValue()).sum()))
					.get();

			return dep;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sesion.close();
		}
	}

	public static void subirSueldo(int idDep) {
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();

		try {

			Departamento d = sesion.get(Departamento.class, idDep);
			for (Empleado emp : d.getEmpleados()) {
				if (emp.getEmpleadoDatosProf() != null) {
					emp.getEmpleadoDatosProf().setSueldoBrutoAnual(
							new BigDecimal(emp.getEmpleadoDatosProf().getSueldoBrutoAnual().doubleValue() * 1.1));
				}

			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	public static void generarProyectosXML() {
		Session sesion = HibernateUtil.getSession();
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element raiz = doc.createElement("proyectos");
			if (!getFichero("fileProy").exists())
				doc.appendChild(raiz);
			else {
				getFichero("fileProy").delete();
				getFichero("fileProy").createNewFile();
				doc.appendChild(raiz);
			}

			List<Proyecto> proyectos = sesion.createQuery("FROM Proyecto", Proyecto.class).getResultList();

			for (Proyecto p : proyectos) {
				Element proy = doc.createElement("proyecto");
				proy.appendChild(doc.createElement("nombre").appendChild(doc.createTextNode(p.getNomProy())));
				if (!p.getProyectoSedes().isEmpty()) {
					Element sedes = doc.createElement("sedes");
					for (ProyectoSede ps : p.getProyectoSedes()) {
						Element sede = doc.createElement("sede");
						sedes.appendChild(sede);
						sede.appendChild(doc.createTextNode(ps.getSede().getNomSede()));

					}
					proy.appendChild(sedes);
				}

				raiz.appendChild(proy);

			}

			escribirFicheroXML(doc, "fileProy");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

	public static void cargarDepartamentos() throws FileNotFoundException, JAXBException, IOException {
		Session sesion = HibernateUtil.getSession();
		Transaction tx = sesion.beginTransaction();
		try {
			Departamentos deps = leerFichero(getFichero("fileDep"));
			List<Departamento> listDeps = deps.getDepartamentos();

			List<Sede> sedes = sesion.createQuery("FROM Sede", Sede.class).getResultList();

			for (Departamento departamento : listDeps) {
				departamento.setSede(
						sedes.stream().filter(s -> s.getIdSede() == departamento.getIdSedeXml())
						.findFirst().get());
				
				sesion.persist(departamento);

				departamento.getEmpleados().stream()
					.forEach(emp -> {
				        emp.setDepartamento(departamento); // Asigna el departamento
				        sesion.persist(emp); 
					});
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sesion.close();
		}
	}

}
