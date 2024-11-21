package reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BarberShop {
    
    private final Lock lock = new ReentrantLock();  // Lock para sincronización
    private final Condition barberIsAvailable = lock.newCondition();  // Condición para barbero disponible
    private final Condition customerIsReady = lock.newCondition();  // Condición para cliente listo
    private final int MAX_CUST = 20;  // Número máximo de clientes en espera
    private int currentCustomers = 0;  // Clientes que esperan

    public void enterShop(int customerId) throws InterruptedException {
        lock.lock();
        try {
            if (currentCustomers == MAX_CUST) {
                System.out.println("Cliente " + customerId + " se va, no hay sillas disponibles.");
                return;  // El cliente se va si no hay sillas
            }
            
            // Incrementar la cantidad de clientes en espera
            currentCustomers++;
            System.out.println("Cliente " + customerId + " entra en la barbería.");
            
            customerIsReady.signal();  // Despertar al barbero si está durmiendo

            // Espera a que el barbero lo atienda
            barberIsAvailable.await();
            
            System.out.println("Cliente " + customerId + " está siendo atendido.");
            // Simula el tiempo de corte de pelo
            Thread.sleep(1000);  
            System.out.println("Cliente " + customerId + " se ha ido.");

        } finally {
            lock.unlock();
        }
    }

    public void barberService() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                // El barbero duerme si no hay clientes
                if (currentCustomers == 0) {
                    System.out.println("Barbero durmiendo...");
                    customerIsReady.await();
                }

                // Atiende al siguiente cliente
                currentCustomers--;
                System.out.println("Barbero atiende a un cliente.");
                
                // Despierta al cliente para indicarle que está siendo atendido
                barberIsAvailable.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BarberShop shop = new BarberShop();
        
        // Crear el hilo del barbero
        Thread barberThread = new Thread(() -> {
            try {
                shop.barberService();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        barberThread.start();

        // Crear clientes
        for (int i = 1; i <= 21; i++) {
            int customerId = i;
            Thread customerThread = new Thread(() -> {
                try {
                    shop.enterShop(customerId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            customerThread.start();
        }
    }
}
