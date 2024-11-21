package stampedLock;

import java.util.concurrent.locks.StampedLock;

public class Punto {
    private double x, y;
    private final StampedLock lock = new StampedLock();

    // Método para escribir (modificar las coordenadas)
    public void mover(double deltaX, double deltaY) {
        long stamp = lock.writeLock(); // Adquiere un Write Lock
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp); // Libera el Write Lock
        }
    }

    // Método para leer (acceder a las coordenadas)
    public double distanciaDesdeOrigen() {
        long stamp = lock.readLock(); // Adquiere un Read Lock
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            lock.unlockRead(stamp); // Libera el Read Lock
        }
    }

    // Método para una lectura optimista (sin bloquear inicialmente)
    public double distanciaOptimista() {
        long stamp = lock.tryOptimisticRead(); // Intenta un Optimistic Read Lock
        double currentX = x, currentY = y;

        // Verifica si no hubo modificaciones durante la lectura
        if (!lock.validate(stamp)) {
            // Si hubo cambios, adquiere un Read Lock
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp); // Libera el Read Lock
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}