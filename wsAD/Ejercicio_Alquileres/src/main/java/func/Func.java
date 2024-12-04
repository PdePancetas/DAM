package func;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import beans.Datos;
import beans.Empleado;
import beans.Piso;
import connection.ConexionBD;
import io.IO;
import jakarta.xml.bind.JAXBException;

public class Func {

	private static Datos datos = new Datos();

	/*-	Insertar tanto empleados como pisos (en principio sin alquilar)*/
	public static boolean addEmpleado(int nif, String nombre, double sueldoBase)
			throws FileNotFoundException, JAXBException, IOException {

//		datos = IO.leerFichero(IO.getFichero());

		if (datos.getEmpleados().add(new Empleado(nif, nombre, sueldoBase))) {
//			IO.escribirFichero(datos);
			return true;
		} else
			return false;
	}

	public static boolean addPiso(String direccion, double mensualidad, int nif_Empleado) {

		int codigo = 1 + getMaxCode(datos.getPisos());
		return datos.getPisos().add(new Piso(codigo, direccion, mensualidad, false, nif_Empleado));
	}

	private static int getMaxCode(List<Piso> pisos) {
		Integer codigo = null;
		try {
			codigo = datos.getPisos().stream().mapToInt(piso -> piso.getCodigo()).max().getAsInt();
		} catch (Exception e) {
			codigo = 0;
		}
		return codigo;
	}

	/*-	Modificar la mensualidad de un piso*/
	public static void modMensualidad(int codigo, double mensualidad) {
		datos.getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get()
				.setMensualidad(mensualidad);
	}

	/*-	Cambiar en un piso el empleado que lo lleva*/
	public static void cambiarEmpleadoPiso(int codigo, int nif) {
		datos.getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get().setNif_Empleado(nif);
	}

	/*-	Alquilar piso/dejar de alquilarlo*/
	public static void alquilar_noAlquilar(boolean alquilado, int codigo) {
		datos.getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get().setAlquilado(alquilado);
	}

	/*-	Dado un código de piso, mostrar el nombre del empleado que lo lleva*/
	public static String mostrarEmpleadoPiso(int codigo) {
		Integer nif = datos.getPisos().stream().filter(p -> p.getCodigo() == codigo).map(p -> p.getNif_Empleado())
				.findFirst().get();

		return datos.getEmpleados().stream().filter(e -> e.getNif() == nif).findFirst().get().getNombre();
	}

	/*-	Si el sueldo mensual del empleado es su sueldo base más un 10% de la mensualidad de cada piso que tenga alquilado, 
	 * mostrar el sueldo de un empleado dado su nif*/
	public static double sueldoMensual(int nif) {
		double sueldo_pisos = datos.getPisos().stream()
				.filter(piso -> piso.getNif_Empleado() == nif && piso.isAlquilado())
				.mapToDouble(piso -> piso.getMensualidad()).sum() * 0.1;

		Empleado emp = datos.getEmpleados().stream().filter(e -> e.getNif() == nif).findFirst().get();

		return sueldo_pisos + emp.getSueldoBase();

	}

	/*-	Mostrar el empleado que más pisos alquilados tenga*/
	/**
	 * Cuidado: Si hay varios con el mismo numero de pisos alquilados solo devolvera
	 * uno
	 */
	public static Empleado masPisosAlquilados() {
		TreeMap<Long, Empleado> numAlquilados = new TreeMap<>();
		for (Empleado e : datos.getEmpleados())
			numAlquilados.put(
					datos.getPisos().stream().filter(p -> p.getNif_Empleado() == e.getNif() && p.isAlquilado()).count(),
					e);

		return numAlquilados.lastEntry().getValue();
	}

	public static void mostrarPisos() {
		datos.getPisos().stream().forEach(System.out::println);
	}

	public static List<Integer> codigosPiso() {
		return datos.getPisos().stream().map(p -> p.getCodigo()).toList();
	}

	public static boolean empleadoExiste(int nif) {
		return datos.getEmpleados().stream().anyMatch(e -> e.getNif() == nif);
	}

	public static boolean pisoExiste(int codigo) {
		return datos.getPisos().stream().anyMatch(p -> p.getCodigo() == codigo);
	}

	public static Empleado getEmpleado(int nif) {
		return datos.getEmpleados().stream().filter(e -> e.getNif() == nif).findFirst().get();
	}

	public static boolean estaAlquilado(int codigo) {
		return datos.getPisos().stream().filter(p -> p.getCodigo() == codigo).findFirst().get().isAlquilado();
	}

	public static int numPisos(Empleado e) {

		return (int) datos.getPisos().stream().filter(p -> p.getNif_Empleado() == e.getNif() && p.isAlquilado())
				.count();
	}

	/*
	 * Dado un xml que contenga información con la siguiente estructura: <pisos>
	 * <piso> <direccion>xxxx<direccion> <mensualidad>xxxxx<mensualidad>
	 * <nif_empleado>xxxxxx<nif_empleado> </piso> …… </pisos> Incorporar dichos
	 * pisos en la base de datos. Hacerlo usando tanto DOM como JAXB
	 */
	public static void volcarDatosXML_JAXB_a_BBDD() throws FileNotFoundException, JAXBException, IOException {
		Connection con = ConexionBD.getConex();

		Datos datos = IO.leerFichero(IO.getFichero());

		try {
			con.setAutoCommit(false);
//			for (Empleado e : datos.getEmpleados()) {
//				PreparedStatement ps = con
//						.prepareStatement("INSERT INTO empleados (nif, nombre, sueldoBase) VALUES (?, ?, ?)");
//				ps.setInt(1, e.getNif());
//				ps.setString(2, e.getNombre());
//				ps.setDouble(3, e.getSueldoBase());
//				ps.executeUpdate();
//			}

			for (Piso p : datos.getPisos()) {
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO pisos (direccion, mensualidad, nifEmpleado) VALUES (?, ?, ?)");
				ps.setString(1, p.getDireccion());
				ps.setDouble(2, p.getMensualidad());
				ps.setInt(3, p.getNif_Empleado());
				ps.executeUpdate();
			}

			con.commit();
			System.out.println("Se han volcado con exito los datos a la BBDD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void volcarDatos_a_XML() throws FileNotFoundException, JAXBException, IOException {
		IO.escribirFichero(datos);
	}

}
