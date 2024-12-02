package func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import beans.Datos;
import beans.Empleado;
import beans.Pisos;
import beans.Pisos.Piso;

public class Funcionalidades {

	/*-	Insertar tanto empleados como pisos (en principio sin alquilar)*/
	public static void addEmpleado(int nif, String nombre, double sueldoBase) {
		Datos.getEmpleados().add(new Empleado(nif, nombre, sueldoBase));		
	}
	
	public static void addPiso(String direccion, double mensualidad, int nif_Empleado) {
		int codigo = 1+getMaxCode(Datos.getPisos());
		Datos.getPisos().getPisos().add(new Piso(codigo, direccion, mensualidad, true, nif_Empleado));
	}

	private static int getMaxCode(Pisos pisos) {
		Integer codigo = null;
		try {
		codigo = Datos.getPisos().getPisos().stream().mapToInt(piso -> piso.getCodigo()).max().getAsInt();
		} catch(Exception e) {
			codigo = 0;
		}
		return codigo;
	}
	
	/*-	Modificar la mensualidad de un piso*/
	public static void modMensualidad(int codigo, double mensualidad) {
		Datos.getPisos().getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get().setMensualidad(mensualidad);
	}
	
	/*-	Cambiar en un piso el empleado que lo lleva*/
	public static void cambiarEmpleadoPiso(int codigo, int nif) {
		Datos.getPisos().getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get().setNif_Empleado(nif);
	}

	/*-	Alquilar piso/dejar de alquilarlo*/
	public void alquilar_noAlquilar(boolean alquilado, int codigo) {
		Datos.getPisos().getPisos().stream().filter(piso -> piso.getCodigo() == codigo).findFirst().get().setAlquilado(alquilado);
	}
	
	/*-	Dado un código de piso, mostrar el nombre del empleado que lo lleva*/
	public static String mostrarEmpleadoPiso(int nif_Empleado) {
		Integer nif = Datos.getPisos().getPisos().stream()
				.map(piso -> piso.getNif_Empleado())
				.filter(NIF -> NIF== nif_Empleado)
				.findFirst().get();
		
		return Datos.getEmpleados().stream().filter(e -> e.getNif() == nif).findFirst().get().getNombre();
	}
	
	/*-	Si el sueldo mensual del empleado es su sueldo base más un 10% de la mensualidad de cada piso que tenga alquilado, 
	 * mostrar el sueldo de un empleado dado su nif*/
	public static double sueldoMensual(int nif) {
		double sueldo_pisos = Datos.getPisos().getPisos().stream()
				.filter(piso -> piso.getNif_Empleado() == nif).mapToDouble(piso -> piso.getMensualidad()).sum()*0.1;
		
		Empleado emp = Datos.getEmpleados().stream().filter(e -> e.getNif() == nif).findFirst().get();
		
		return sueldo_pisos +emp.getSueldoBase();
		
	}
	
	/*-	Mostrar el empleado que más pisos alquilados tenga*/
	/**
	 * Cuidado: Si hay varios con el mismo numero de pisos alquilados solo devolvera uno
	 */
	public static Empleado masPisosAlquilados() {
//		Empleado empleado = null;
//		Empleado max = 
		TreeMap<Long, Empleado> numAlquilados = new TreeMap<>();
		for(Empleado e: Datos.getEmpleados()) {
			numAlquilados.put(Datos.getPisos().getPisos().stream().filter(p -> p.getNif_Empleado() == e.getNif() && p.isAlquilado()).count(),e);
		}
		
		return numAlquilados.lastEntry().getValue();
	}
	
	
	public static void mostrarPisos() {
		Datos.getPisos().getPisos().stream().forEach(System.out::println);
		
	}
	
	
	
	
}
