package model;

public class AsignaturaFP extends Asignatura {

	private int nota, numHoras, convocatoria;

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}

	public int getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(int convocatoria) {
		this.convocatoria = convocatoria;
	}

	public AsignaturaFP() {
	}

	public AsignaturaFP(String nombre, int nota, int numHoras, int convocatoria) {
		super(nombre);
		this.nota = nota;
		this.numHoras = numHoras;
		this.convocatoria = convocatoria;
	}

}
