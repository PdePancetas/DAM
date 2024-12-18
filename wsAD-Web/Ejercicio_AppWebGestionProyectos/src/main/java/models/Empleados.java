package models;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="empleados")
public class Empleados {

	private List<Empleado> empleados = new ArrayList<>();

	@XmlElement(name="empleado")
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleados(List<Empleado> empleados) {
		super();
		this.empleados = empleados;
	}

	public Empleados() {
		super();
	}
	
	
}
