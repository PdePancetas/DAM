package beans;

public class Alumno {

	private int idAlumno, idProfesor;
	private String nombre;
	private double nota;

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Alumno(int idAlumno, int idProfesor, String nombre, double nota) {
		super();
		this.idAlumno = idAlumno;
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.nota = nota;
	}

	public Alumno() {
		super();
	}

	@Override
	public String toString() {
		return "Alumno idAlumno=" + idAlumno + ", idProfesor=" + 
				idProfesor + ", nombre=" + nombre + ", nota=" + nota;
	}

	
	
}
