package Clase_Alumno_Profesor;

public class Alumno implements Runnable {

	private Clase clase;

	@Override
	public void run() {

		while (!clase.estaEnClase) {
			try {
				System.out.println("El profesor no esta aun, " + Thread.currentThread().getName() + " esperando");
				synchronized (clase) {
					clase.wait();

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Hola profesor, " + Thread.currentThread().getName() + " siguiendo la clase");

	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public Alumno(Clase clase) {
		super();
		this.clase = clase;

	}
}