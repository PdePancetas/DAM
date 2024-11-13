package Barrera;

public class Monitor {

	private int metodosLanzados;

	public synchronized int getMetodosLanzados() {
		return metodosLanzados;
	}

	public synchronized void setMetodosLanzados(int metodosLanzados) {
		this.metodosLanzados = metodosLanzados;
	}

	public Monitor(int metodosLanzados) {
		super();
		this.metodosLanzados = metodosLanzados;
	}
	
	
}
