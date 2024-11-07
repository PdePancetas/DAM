package contarVocales;

public class ContadorVocal {

	private String texto;
	private int vocales = 0;

	public ContadorVocal(String texto) {
		super();
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public synchronized void addVocal() {
		vocales++;
	}

	public synchronized int getVocales() {
		return vocales;
	}

}
