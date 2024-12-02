package beans;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "pisos")
public class Datos {

	private static List<Piso> pisos;
	private static List<Empleado> empleados;

	@XmlTransient
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		Datos.empleados = empleados;
	}

	@XmlElement(name = "piso")
	public List<Piso> getPisos() {
		return pisos;
	}

	public void setPisos(List<Piso> pisos) {
		Datos.pisos = pisos;
	}

	public Datos(List<Piso> pisos, List<Empleado> empleados) {
		super();
		Datos.pisos = pisos;
		Datos.empleados = empleados;
	}

	public Datos() {
		super();
		Datos.empleados = new ArrayList<Empleado>();
		Datos.pisos = new LinkedList<Piso>();
	}

}
