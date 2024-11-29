package reentrantReadWriteLock;

public class ReaderWriter_synchronized {

    private int readers = 0;       // Contador de lectores activos
    private boolean writerActive = false; // Indica si hay un escritor activo

    // Método para que los lectores lean
    public synchronized void startReading() throws InterruptedException {
        while (writerActive) { // Esperar si hay un escritor activo
        	wait();
        }
        readers++; // Incrementar el contador de lectores
        System.out.println(Thread.currentThread().getName() + " empezó a leer. Lectores activos: " + readers);
    }

    public synchronized void stopReading() {
        readers--; // Decrementar el contador de lectores
        System.out.println(Thread.currentThread().getName() + " dejó de leer. Lectores activos: " + readers);
        if (readers == 0) {
            notifyAll(); // Notificar a los escritores que pueden escribir
        }
    }

    // Método para que los escritores escriban
    public synchronized void startWriting() throws InterruptedException {
        while (writerActive || readers > 0) { // Esperar si hay lectores o un escritor activo
            wait();
        }
        writerActive = true; // Indicar que un escritor está activo
        System.out.println(Thread.currentThread().getName() + " empezó a escribir.");
    }

    public synchronized void stopWriting() {
        writerActive = false; // Indicar que el escritor terminó
        System.out.println(Thread.currentThread().getName() + " dejó de escribir.");
        notifyAll(); // Notificar a lectores y escritores en espera
    }

    // Clase principal para probar la implementación
    public static void main(String[] args) {
        ReaderWriter_synchronized resource = new ReaderWriter_synchronized();

        // Crear hilos de lectores
        Runnable readerTask = () -> {
            try {
                while (true) {
                    resource.startReading();
                    Thread.sleep(1000); // Simular tiempo de lectura
                    resource.stopReading();
                    Thread.sleep(500);  // Pausa antes de leer nuevamente
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Crear hilos de escritores
        Runnable writerTask = () -> {
            try {
                while (true) {
                    resource.startWriting();
                    Thread.sleep(1500); // Simular tiempo de escritura
                    resource.stopWriting();
                    Thread.sleep(1000); // Pausa antes de escribir nuevamente
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Iniciar hilos
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
