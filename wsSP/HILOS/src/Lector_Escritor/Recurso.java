package Lector_Escritor;

public class Recurso {

	private boolean hayLector;
	private boolean hayEscritor;
	private Object escritor = new Object();
	private Object lector = new Object();

	public Recurso() {
		super();
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
				hayLector = true;
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(Thread.currentThread().getName() + " leyendo...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " termino de leer");

		synchronized (lector) {
			hayLector = false;
		}
		synchronized (this) {
			if (!hayLector) {
				notifyAll();
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

		synchronized (this) {
			if (!hayLector) {
				hayEscritor = true;
			}
		}
		synchronized (this) {

			System.out.println(Thread.currentThread().getName() + " escribiendo...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " termino de escribir");
			hayEscritor = false;
		}
		synchronized (this) {
			notifyAll();
		}

//		synchronized (escritor) {
//			escritor.notifyAll();
//		}

	}

}
