public class a implements Runnable {
	public static long x = 0;

	public static void main(String[] args) throws InterruptedException {
		
		a a = new a();
		Thread t = new Thread(a);
		t.start();

		Thread t2 = new Thread(a);
		t2.start();

		t.join();
		t2.join();
		System.out.println("Total:" + x);
	}

	public void run() {
		for (int i = 0; i < 10000; i++)
			actualizaX();
	}

	private synchronized void actualizaX() {
		x++;
	}
}