package Lector_Escritor;

public class Escritor implements Runnable {

	private Recurso recCompartido;

	@Override
	public void run() {
		while (true) {
			
			recCompartido.escribir();
		}
	}

	public Escritor(Recurso recCompartido) {
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
