package data;

import java.io.Serializable;
import java.util.Objects;

public class Estudiante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private int edad;
	private Grupo grupo;

	public Estudiante(String nombre, int edad, Grupo grupo) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.grupo = grupo;
	}

	public Estudiante() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, grupo, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return edad == other.edad && Objects.equals(grupo, other.grupo) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Estudiante: " + nombre + ", edad=" + edad + ", grupo=" + grupo.getIdentificador();
	}

}
