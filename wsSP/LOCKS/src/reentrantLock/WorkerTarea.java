package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class WorkerTarea implements Runnable {
	private final ReentrantLock lock = new ReentrantLock(false); // Para evitar inanición
	private volatile boolean running;

	public WorkerTarea(boolean running) {
		super();
		this.running = running;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	// Método principal del hilo
	@Override
	public void run() {
		while (true) { // Mientras running sea true, el worker sigue ejecutándose
			if (!running) {
				try {
					 lock.lock();
					 running = true;// Intentar adquirir el lock
		                    try {
		                        // Sección crítica: realizar la tarea
		                        System.out.println(Thread.currentThread().getName() + " ejecutando tarea...");

		                        // Simula la tarea con un pequeño retraso
		                        Thread.sleep(1000);

		                    } finally {
		                    	running = false;
		                        lock.unlock(); // Liberar el lock
		                        System.out.println(Thread.currentThread().getName() + " liberó el lock.");
		                        Thread.sleep(500);
		                    }
		                
		                    // Si no se consigue el lock, esperar un tiempo antes de intentarlo nuevamente
		                    Thread.sleep(500); // Pausa para evitar el uso intensivo del CPU
		                
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WorkerTarea worker = new WorkerTarea(false);
		 Thread[] threads = new Thread[10];
	        for (int i = 0; i < threads.length; i++) {
	            threads[i] = new Thread(worker, "Worker-Hilo-" + i);
	           
	        }
	        for (int i = 0; i < threads.length; i++) {
	            threads[i].start();
	           
	        }
		
		Thread.sleep(5000);

		
	}
}