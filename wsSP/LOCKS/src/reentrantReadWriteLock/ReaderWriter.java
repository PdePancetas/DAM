package reentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriter {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String sharedResource = ""; // Recurso compartido

    // Método para que los lectores lean
    public void read() {
        lock.readLock().lock(); // Adquirir el bloqueo de lectura
        try {
            System.out.println(Thread.currentThread().getName() + " leyendo: " + sharedResource);
            Thread.sleep(1000); // Simular tiempo de lectura
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.readLock().unlock(); // Liberar el bloqueo de lectura
        }
    }

    // Método para que los escritores escriban
    public void write(String data) {
        lock.writeLock().lock(); // Adquirir el bloqueo de escritura
        try {
            System.out.println(Thread.currentThread().getName() + " escribiendo: " + data);
            Thread.sleep(1500); // Simular tiempo de escritura
            sharedResource = data; // Actualizar el recurso compartido
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock(); // Liberar el bloqueo de escritura
        }
    }

    public static void main(String[] args) {
        ReaderWriter resource = new ReaderWriter();

        // Crear tareas para los lectores
        Runnable readerTask = () -> {
            while (true) {
                resource.read();
                try {
                    Thread.sleep(500); // Pausa antes de la próxima lectura
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Crear tareas para los escritores
        Runnable writerTask = () -> {
            int counter = 0;
            while (true) {
                resource.write("Dato-" + counter++);
                try {
                    Thread.sleep(2000); // Pausa antes de la próxima escritura
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Crear hilos de lectores y escritores
        Thread reader1 = new Thread(readerTask, "Lector-1");
        Thread reader2 = new Thread(readerTask, "Lector-2");
        Thread reader3 = new Thread(readerTask, "Lector-3");
        Thread reader4 = new Thread(readerTask, "Lector-4");
        Thread writer1 = new Thread(writerTask, "Escritor-1");
        Thread writer2 = new Thread(writerTask, "Escritor-2");

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
        writer1.start();
        writer2.start();
    }
}
