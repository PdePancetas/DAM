package ExamenNetwork2425;

import java.io.Serializable;
import java.util.Objects;

public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	String nombre;
	String descripcion;
	boolean completada;

	public Tarea(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.completada = false;
	}

	public Tarea() {
		
	}

	public void marcarCompletada() {
		this.completada = true;
	}

	public void marcarPendiente() {
		this.completada = false;
	}

	@Override
	public String toString() {
		return nombre + " - " + descripcion + " - " + (completada ? "Completada" : "Pendiente");
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
