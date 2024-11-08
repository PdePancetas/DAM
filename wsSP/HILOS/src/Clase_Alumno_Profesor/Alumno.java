package Clase_Alumno_Profesor;

public class Alumno implements Runnable {

	private Clase clase;
	private boolean acaboDeEntrar;

	@Override
	public void run() {

		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acaboDeEntrar = true;
		synchronized (clase) {
			
			try {
				while (!clase.hayProfesor) {

					if (!clase.hayAlumno) {
						System.out.println(Thread.currentThread().getName() + " Soy el primero en llegar");
						clase.hayAlumno = true;
						clase.wait();
					} else
						if (clase.hayAlumno) {
							if (acaboDeEntrar) {
								System.out.println(Thread.currentThread().getName() + " Hola ");
								clase.notifyAll();
								acaboDeEntrar = false;
								clase.wait();
							} else {
								System.out.println(Thread.currentThread().getName() + " Hola compañero");
								clase.wait();
							}
						}

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Hola profesor, " + Thread.currentThread().getName() + " siguiendo la clase");
		}

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
