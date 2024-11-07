package fibonacciEjemplo;
import utilidadesTeclado.Teclado;

public class fibonacciThread extends Thread {

	public static void main(String[] args) {
		System.out.println("Numero: ");
		int n = Teclado.leerEntero();
		new fibonacciThread(n).start();
	}

	int n;

	@Override
	public void run() {
		if (n < 3)
			System.out.println("introduce un numero mayor que 3");
		else {
			/*
			 * N (i)=N (i-1) +N (i-2) N(1)=1 N(2)=2
			 * 
			 */
			int n1 = 1;
			int n2 = 1;
			System.out.println("T: " + n1);
			System.out.println("T: " + n2);

			for (int i = 3; i < n; i++) {
				System.out.println("T: "+i+": "+(n1+n2));
				int aux = n1;
				n1 = n1 + n2;
				n2 = aux;
			}
		}

	}

	public fibonacciThread(int n) {
		super();
		this.n = n;
	}

}
