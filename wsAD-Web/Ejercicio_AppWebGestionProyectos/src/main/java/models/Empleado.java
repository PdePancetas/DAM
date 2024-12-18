package models;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Empleado {

	private String dni;
	private String nombre;
	
	@XmlAttribute
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Empleado(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
	public Empleado() {
		super();
	}
	@Override
	public String toString() {
		return "Empleado - Nombre: " + nombre + " Dni: " + dni;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
	
}
