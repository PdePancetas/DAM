package model;

public class AsignaturaSecundaria extends Asignatura {

	private String nota;

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public AsignaturaSecundaria() {
	}

	public AsignaturaSecundaria(String nombre, String nota) {
		super(nombre);
		this.nota = nota;
	}

}
