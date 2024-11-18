package beans;

import java.io.Serializable;
import java.util.Objects;

public class Contacto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Datos datos;

	public Contacto(String nombre, Datos datos) {
		super();
		this.nombre = nombre;
		this.datos = datos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Datos getDatos() {
		return datos;
	}

	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", datos: " + datos.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
