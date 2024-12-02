package beans;

import java.util.ArrayList;
import java.util.List;

public class Datos {
	
	private static Pisos pisos;
	private static List<Empleado> empleados;

	public static Pisos getPisos() {
		return pisos;
	}

	public static void setPisos(Pisos pisos) {
		Datos.pisos = pisos;
	}

	public static List<Empleado> getEmpleados() {
		return empleados;
	}

	public static void setEmpleados(List<Empleado> empleados) {
		Datos.empleados = empleados;
	}

	public Datos(Pisos pisos, List<Empleado> empleados) {
		super();
		Datos.pisos = pisos;
		Datos.empleados = empleados;
	}

	public Datos() {
		super();
		Datos.empleados = new ArrayList<Empleado>();
		Datos.pisos = new Pisos();
	}

}
