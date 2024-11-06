package a;

public class a implements Runnable {
	public static long x = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new a());
		t.start();
		
		
		Thread t2 = new Thread(new a());
		t2.start();
		
		t.join();
		t2.join();
		System.out.println("Total:" + x);
	}

	public void run() {
		for (int i = 0; i < 10000; i++) {
			sumaX();
		}
	}

	private synchronized void sumaX() {
		x++;
		
	}
}