package com.dam2.proyectos.model;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "proyecto") 
public class Proyecto{
	
	@Id							
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String nom_proy;
	
	@ManyToOne
	@JoinColumn(name = "dni_jefe_proy", nullable = false, updatable = true)
	private Empleado empJefe;
	
	
	//EL DEFINIR AQUÍ LA RELACIÓN CONVIERTE A Proyecto en PROPIETARIO
	//DE LA RELACIÓN: IMPORTANTE, YA QUE ENTONCES LOS CAMBIOS SE TIENEN
	//QUE HACER EN Proyecto!!!
	@JoinTable(
	        name = "asig_proyecto",
	        joinColumns = @JoinColumn(name = "id_proy"/*, referencedColumnName = "id", nullable = false*/),
	        inverseJoinColumns = @JoinColumn(name="dni_emp"/*, referencedColumnName = "dni", nullable = false*/)
	    )
	@ManyToMany/*(cascade = CascadeType.ALL)*/
	private List<Empleado> empleados;
	
	
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId_proy(Integer id) {
		this.id = id;
	}
	public String getNom_proy() {
		return nom_proy;
	}
	public void setNom_proy(String nom_proy) {
		this.nom_proy = nom_proy;
	}
	public Empleado getEmpJefe() {
		return empJefe;
	}
	public void setEmpJefe(Empleado empJefe) {
		this.empJefe = empJefe;
	}
	
	
	
	

}
