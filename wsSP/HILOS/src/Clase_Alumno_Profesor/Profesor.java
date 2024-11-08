package Clase_Alumno_Profesor;

public class Profesor implements Runnable {

	private Clase clase;

	public Profesor(Clase clase) {
		super();
		this.clase = clase;
	}

	@Override
	public void run() {
		
		synchronized (clase) {
			clase.estaEnClase = true;
			System.out.println(Thread.currentThread().getName() + " ha llegado, hola a todos, inicia la clase");
			clase.notifyAll();
		}

	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

}
