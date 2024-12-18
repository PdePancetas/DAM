package models;

import java.util.List;

public class Proyecto {

	private int id_proy;
	private String nom_proy;
	private String dni_jefe_proy;
	private List<Empleado> empleados;

	public int getId_proy() {
		return id_proy;
	}

	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}

	public String getNom_proy() {
		return nom_proy;
	}

	public void setNom_proy(String nom_proy) {
		this.nom_proy = nom_proy;
	}

	public String getDni_jefe_proy() {
		return dni_jefe_proy;
	}

	public void setDni_jefe_proy(String dni_jefe_proy) {
		this.dni_jefe_proy = dni_jefe_proy;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Proyecto(int id_proy, String nom_proy, String dni_jefe_proy, List<Empleado> empleados) {
		super();
		this.id_proy = id_proy;
		this.nom_proy = nom_proy;
		this.dni_jefe_proy = dni_jefe_proy;
		this.empleados = empleados;
	}

	public Proyecto(int id_proy, String nom_proy, String dni_jefe_proy) {
		super();
		this.id_proy = id_proy;
		this.nom_proy = nom_proy;
		this.dni_jefe_proy = dni_jefe_proy;
	}

}
