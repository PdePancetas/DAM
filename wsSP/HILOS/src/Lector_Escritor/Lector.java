package Lector_Escritor;

public class Lector implements Runnable {

	private Recurso recCompartido;

	@Override
	public void run() {
		while (true) {

			recCompartido.leer();
		}
	}

	public Lector(Recurso recCompartido) {
		super();
		this.recCompartido = recCompartido;
	}

	public Recurso getRecCompartido() {
		return recCompartido;
	}

	public void setRecCompartido(Recurso recCompartido) {
		this.recCompartido = recCompartido;
	}

}
