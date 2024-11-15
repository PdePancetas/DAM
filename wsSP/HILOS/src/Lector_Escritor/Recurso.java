package Lector_Escritor;

public class Recurso {

	private int numLectores = 0;
	private boolean hayEscritor;
	private Object escritor = new Object();
	private Object lector = new Object();

	public int getNumLectores() {
		return numLectores;
	}

	public void setNumLectores(int datos) {
		this.numLectores = datos;
	}

	public Recurso(int datos) {
		super();
		this.numLectores = datos;
	}

	public Object getEscritor() {
		return escritor;
	}

	public void setEscritor(Object escritor) {
		this.escritor = escritor;
	}

	public boolean hayEscritor() {
		return hayEscritor;
	}

	public void setHayEscritor(boolean hayEscritor) {
		this.hayEscritor = hayEscritor;
	}

	public void leer() {
		synchronized (this) {
			if (!hayEscritor) {
				numLectores += 1;
			} else {
				try {
					escritor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(Thread.currentThread().getName() + " leyendo...,hay " + numLectores);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " termino de leer,hay " + numLectores);

		synchronized (lector) {
			numLectores -= 1;
		}
		synchronized (this) {
			if (numLectores == 0) {
				escritor.notifyAll();
			}
		}
	}

	public void escribir() {

//		if (hayEscritor) {
//			synchronized (escritor) {
//				try {
//					escritor.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}

		synchronized (lector) {
			if (numLectores == 0) {
				hayEscritor = true;
			}
		}

		System.out.println(Thread.currentThread().getName() + " escribiendo...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " termino de escribir");
		hayEscritor = false;
		
		synchronized (lector) {
			lector.notifyAll();
		}

//		synchronized (escritor) {
//			escritor.notifyAll();
//		}

	}

}
