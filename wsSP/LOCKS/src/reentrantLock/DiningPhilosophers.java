package reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private final Lock[] forks = new ReentrantLock[5];

    public DiningPhilosophers() {
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public void philosopher(int id) {
        int leftFork = id;
        int rightFork = (id + 1) % forks.length;

        while (true) {
            try {
                System.out.println("Filósofo " + id + " está pensando.");
                Thread.sleep((int) (Math.random() * 1000));

                if (forks[leftFork].tryLock()) {
                    try {
                        if (forks[rightFork].tryLock()) {
                            try {
                                System.out.println("Filósofo " + id + " está comiendo.");
                                Thread.sleep((int) (Math.random() * 1000));
                            } finally {
                                forks[rightFork].unlock();
                            }
                        }
                    } finally {
                        forks[leftFork].unlock();
                    }
                }

                Thread.sleep(100); // Intentar de nuevo si no puede obtener ambos tenedores

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        DiningPhilosophers dp = new DiningPhilosophers();

        for (int i = 0; i < 5; i++) {
            int id = i;
            new Thread(() -> dp.philosopher(id)).start();
        }
    }
}
