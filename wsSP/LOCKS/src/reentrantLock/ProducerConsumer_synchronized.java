package reentrantLock;


import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer_synchronized {

	private final LinkedList<Integer> buffer = new LinkedList<>();
	private final int capacity = 50; // Tamaño máximo del buffer
	private final Object notFull = new Object();
	private final Object notEmpty = new Object();

	// Método del productor
	public void produce() throws InterruptedException {
		int value = 1;
		while (true) {
			synchronized (notFull) {

				// Esperar si el buffer está lleno
				while (buffer.size() != 0) {
					System.out.println("Buffer lleno. Productor esperando...");
					notFull.wait();
				}
				while (buffer.size() != capacity) {
					// Añadir elemento al buffer
					buffer.add(value);
					System.out.println("Productor produjo: " + value + " (" + buffer.size() + ")");
				}
			}
			// Señalar a los consumidores que hay elementos disponibles
			synchronized (notEmpty) {
				notEmpty.notifyAll();
			}
			// Liberar el lock

			Thread.sleep(500); // Simular tiempo de producción
		}
	}

	// Método del consumidor
	public void consume() throws InterruptedException {
		while (true) {
			synchronized (notEmpty) {

				// Esperar si el buffer está vacío
				while (buffer.isEmpty()) {
						System.out.println(Thread.currentThread().getName() + " Buffer vacío. Consumidor esperando...");
						notEmpty.wait();
						System.out.println("Buffer lleno.");
				}

				// Extraer elemento del buffer
				int value = buffer.removeFirst();
				System.out
						.println(Thread.currentThread().getName() + " consumió: " + value + " (" + buffer.size() + ")");
			}
			synchronized (notFull) {
				notFull.notifyAll();
			}

			Thread.sleep(1000); // Simular tiempo de consumo
		}
	}

	// Main para probar la implementación
	public static void main(String[] args) {
		ProducerConsumer_synchronized pc = new ProducerConsumer_synchronized();

		// Crear hilos del productor y consumidor
		Thread producerThread = new Thread(() -> {
			try {
				pc.produce();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		producerThread.start();
		
		ExecutorService e = Executors.newFixedThreadPool(5); 
		for (int i = 0; i < 15; i++) {
			e.execute(() -> {
				try {
					pc.consume();
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
			});
		}
		e.shutdown();
		
		
	}
}
