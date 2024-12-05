package func;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import beans.Empleado;
import jakarta.xml.bind.JAXBException;

public class Func {

	 
	/*-	Cambiar en un piso el empleado que lo lleva*/
	public static void cambiarEmpleadoPiso(int codigo, int nif) {
		
	}

	/*-	Alquilar piso/dejar de alquilarlo*/
	public static void alquilar_noAlquilar(boolean alquilado, int codigo) {
		
	}

	/*-	Dado un código de piso, mostrar el nombre del empleado que lo lleva*/
	public static String mostrarEmpleadoPiso(int codigo) {
		
		return null;
	}

	/*-	Mostrar el empleado que más pisos alquilados tenga*/
	/**
	 * Cuidado: Si hay varios con el mismo numero de pisos alquilados solo devolvera
	 * uno
	 */
	public static Empleado masPisosAlquilados() {
		
		return null;
	}

	public static void mostrarPisos() {
		
	}

	public static List<Integer> codigosPiso() {
		return null;
		
	}

	public static boolean empleadoExiste(int nif) {
		return false;
		
	}

	public static boolean pisoExiste(int codigo) {
		return false;
		
	}

	public static Empleado getEmpleado(int nif) {
		return null;
	
	}

	public static boolean estaAlquilado(int codigo) {
		return false;
		
	}

	public static int numPisos(Empleado e) {
		return 0;

		
	}

	/*
	 * Dado un xml que contenga información con la siguiente estructura: <pisos>
	 * <piso> <direccion>xxxx<direccion> <mensualidad>xxxxx<mensualidad>
	 * <nif_empleado>xxxxxx<nif_empleado> </piso> …… </pisos> Incorporar dichos
	 * pisos en la base de datos. Hacerlo usando tanto DOM como JAXB
	 */
	public static void volcarDatosXML_JAXB_a_BBDD() throws FileNotFoundException, JAXBException, IOException {

	}

	public static void volcarDatos_a_XML() throws FileNotFoundException, JAXBException, IOException {
	}

}
