package beans;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Alumno {

	
	private int nif;

	private String nombre;
	private String direccion;
	private double nota;

	public Alumno() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(nif, other.nif);
	}

	public Alumno(int nif, String nombre, String direccion, double nota) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.nota = nota;
	}

	@XmlAttribute
	public int getNif() {
		return nif;
	}

	public void setNif(int nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Alumno: nif= "+nif+", nombre= " + nombre + ", direccion= " + direccion + ", nota= " + nota;
	}

}
