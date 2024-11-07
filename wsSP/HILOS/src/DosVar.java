import java.util.ArrayList;

public class DosVar implements Runnable {

	private int c1 = 0;
	private int c2 = 0;
	private Object monC1 = new Object();
	private Object monC2 = new Object();

	public static void main(String[] args) throws InterruptedException {

		
		
		System.out.print("Numero de threads: ");
		int n = 500;
		DosVar dosVar = new DosVar();
		Thread thread = null;
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			thread = new Thread(dosVar);

			threads.add(thread);
		}
		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		System.out.println("\nc1: " + dosVar.c1);
		System.out.println("c2: " +  dosVar.c2);
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			inc1();
			inc2();
		}
	}

	private void inc1() {
//		System.out.println(Thread.currentThread().getName());
		synchronized (monC1) {
			setC1(getC1() + 1);
		}
	}

	private void inc2() {
//		System.out.println(Thread.currentThread().getName());
		synchronized (monC2) {
			setC2(getC2() + 1);
		}
	}

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

}
