
public class Alumno implements Comparable {

	protected String nif, nombre;
	private int nota;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

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

	public Alumno() {
	}

	public Alumno(String nif, String nombre, int nota) {
		this.nif = nif;
		this.nombre = nombre;
		this.nota = nota;
	}

	@Override
	public int compareTo(Object o) {
		
		return this.nota - ((Alumno)o).getNota();
		
	}

}
