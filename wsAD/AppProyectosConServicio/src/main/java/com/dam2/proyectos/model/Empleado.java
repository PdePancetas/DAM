package com.dam2.proyectos.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "empleado") 
public class Empleado {
	
	//o ponemos todas las anotaciones antes de las propiedades o antes de los getters
	//no mezclando, si no falla
	
	@Id	
	private String dni;
	
	private String nom_emp;
	
	@OneToMany(mappedBy = "empJefe")
	private List<Proyecto> llevaProyectos;
	
	@ManyToMany(mappedBy = "empleados")
	private List<Proyecto> proyectos;
	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
							
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNom_emp() {
		return nom_emp;
	}
	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}
	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nom_emp=" + nom_emp + "]";
	}
	public List<Proyecto> getLlevaProyectos() {
		return llevaProyectos;
	}
	public void setLlevaProyectos(List<Proyecto> llevaProyectos) {
		this.llevaProyectos = llevaProyectos;
	}
	
	
	

}
