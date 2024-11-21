package reentrantLock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

	private final LinkedList<Integer> buffer = new LinkedList<>();
	private final int capacity = 5; // Tamaño máximo del buffer
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	// Método del productor
	public void produce() throws InterruptedException {
		int value = 1;
		while (true) {
			lock.lock(); // Adquirir el lock
			try {
				// Esperar si el buffer está lleno
				while (buffer.size() != 0) {
					System.out.println("Buffer lleno. Productor esperando...");
					notFull.await();
				}
				while(buffer.size()!=capacity) {
				// Añadir elemento al buffer
				buffer.add(value);
				System.out.println("Productor produjo: " + value);
				}

				// Señalar a los consumidores que hay elementos disponibles
				notEmpty.signal();
			} finally {
				lock.unlock(); // Liberar el lock
			}
			Thread.sleep(500); // Simular tiempo de producción
		}
	}

	// Método del consumidor
	public void consume() throws InterruptedException {
		while (true) {
			lock.lock(); // Adquirir el lock
			try {
				// Esperar si el buffer está vacío
				while (buffer.isEmpty()) {
					while (buffer.size() != 5) {
						System.out.println(Thread.currentThread().getName()+" Buffer vacío. Consumidor esperando...");
						notEmpty.await();
						System.out.println("Buffer lleno.");
					}
				}
				
				// Extraer elemento del buffer
				int value = buffer.removeFirst();
				System.out.println(Thread.currentThread().getName()+" consumió: " + value);
				
				// Señalar al productor que hay espacio disponible
				notFull.signal();
			} finally {
				lock.unlock(); // Liberar el lock
			}
			Thread.sleep(1000); // Simular tiempo de consumo
		}
	}

	// Main para probar la implementación
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		pc.buffer.addAll(Arrays.asList(1, 2, 3, 4, 5));

		// Crear hilos del productor y consumidor
		Thread producerThread = new Thread(() -> {
			try {
				pc.produce();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		Thread consumerThread1 = new Thread(() -> {
			try {
				pc.consume();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
		Thread consumerThread2 = new Thread(() -> {
			try {
				pc.consume();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		// Iniciar los hilos
		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();
	}
}
