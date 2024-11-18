package beans;

public class Profesor {

	private int idProfesor;
	private String nombre, modulo;

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public Profesor(int idProfesor, String nombre, String modulo) {
		super();
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.modulo = modulo;
	}

	public Profesor() {
		super();
	}

	@Override
	public String toString() {
		return "Profesor idProfesor=" + idProfesor + ", nombre=" + nombre + ", modulo=" + modulo;
	}

	
	
}
