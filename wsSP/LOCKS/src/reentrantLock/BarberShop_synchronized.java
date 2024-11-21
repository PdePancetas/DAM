package reentrantLock;

public class BarberShop_synchronized {
    
    private final int MAX_CUST = 5;  // Número máximo de clientes
    private int currentCustomers = 0;  // Número de clientes esperando
    
    public void enterShop(int customerId) throws InterruptedException {
        synchronized (this) {
            if (currentCustomers == MAX_CUST) {
                System.out.println("Cliente " + customerId + " se va, no hay sillas disponibles.");
                return;  // El cliente se va si no hay espacio
            }

            // Incrementar el número de clientes esperando
            currentCustomers++;
            System.out.println("Cliente " + customerId + " entra en la barbería.");
            
            // Despertar al barbero si está durmiendo
            notify();
            
            // El cliente espera su turno para ser atendido
            wait();
            
            System.out.println("Cliente " + customerId + " está siendo atendido.");
            // Simula el tiempo que se tarda en cortar el pelo
            Thread.sleep(1000);  
            System.out.println("Cliente " + customerId + " se ha ido.");
        }
    }

    public void barberService() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // El barbero duerme si no hay clientes esperando
                if (currentCustomers == 0) {
                    System.out.println("Barbero durmiendo...");
                    wait();
                }
                
                // Si hay clientes esperando, el barbero atiende uno
                currentCustomers--;
                System.out.println("Barbero atiende a un cliente.");
                
                // Despertar al cliente para indicarle que se le está atendiendo
                notify();
            }
        }
    }

    public static void main(String[] args) {
        BarberShop_synchronized shop = new BarberShop_synchronized();
        
        // Hilo del barbero
        Thread barberThread = new Thread(() -> {
            try {
                shop.barberService();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        barberThread.start();
        
        // Hilos de los clientes
        for (int i = 1; i <= 10; i++) {
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
