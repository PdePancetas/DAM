package DatosAlumno;

public class datosAlumno {

	private String nombre;
	private int nota;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public datosAlumno(String nombre, int nota) {
		super();
		this.nombre = nombre;
		this.nota = nota;
	}

	public datosAlumno() {
		super();
	}

}
