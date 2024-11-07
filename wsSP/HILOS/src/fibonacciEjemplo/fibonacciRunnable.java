package fibonacciEjemplo;
import utilidadesTeclado.Teclado;

public class fibonacciRunnable implements Runnable {
	
	public static void main(String[] args) {
		System.out.println("Numero: ");
		int n = Teclado.leerEntero();
		Thread t1 = new Thread( new fibonacciRunnable(n));
		t1.start();
	}
	
	long n;
	
	@Override
	public void run() {
		
		/*
		 	N (i)=N (i-1) +N (i-2)
			N(1)=1
			N(2)=2

		 */
		long n1 = 1;
		long n2 = 1;
		System.out.println("R: "+n1);
		System.out.println("R: "+n2);
		
		for(int i=3;i<n;i++) {
			System.out.println("R: "+i+": "+(n1+n2));
			long aux=n1;
			n1 = n1+n2;
			n2=aux;
		}
		
		
	}

	public fibonacciRunnable(long n) {
		super();
		this.n = n;
	}

	
	
}
