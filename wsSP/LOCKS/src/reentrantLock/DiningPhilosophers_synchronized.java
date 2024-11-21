package reentrantLock;

public class DiningPhilosophers_synchronized {
    private final Object[] forks = new Object[5]; // Tenedores como monitores

    public DiningPhilosophers_synchronized() {
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object(); // Cada tenedor es un monitor
        }
    }

    public void philosopher(int id) {
        int leftFork = id;
        int rightFork = (id + 1) % forks.length;

        while (true) {
            try {
                // Pensando
                System.out.println("Filósofo " + id + " está pensando.");
                Thread.sleep((int) (Math.random() * 1000));

                // Intentar tomar los tenedores
                synchronized (forks[leftFork]) {
                    synchronized (forks[rightFork]) {
                        // Comiendo
                        System.out.println("Filósofo " + id + " está comiendo.");
                        Thread.sleep((int) (Math.random() * 1000));
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        DiningPhilosophers_synchronized dp = new DiningPhilosophers_synchronized();

        for (int i = 0; i < 5; i++) {
            int id = i;
            new Thread(() -> dp.philosopher(id)).start();
        }
    }
}
