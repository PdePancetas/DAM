package Ejercicio5;

public class Vinilo implements Disco {

	private String titulo;
	private int nrpm;

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the nrpm
	 */
	public int getNrpm() {
		return nrpm;
	}

	/**
	 * @param nrpm the nrpm to set
	 */
	public void setNrpm(int nrpm) {
		this.nrpm = nrpm;
	}

	@Override
	public void reproducible() {
		System.out.println("Reproduciendo " + this.titulo);
	}

}
