package Comunicacion_Join_Interrupt;

public class Hijo implements Runnable {

	@Override
	public void run() {
		System.out.println("Hijo iniciado");
		int n = 5000;
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			System.err.println("Hijo interrumpido, esperando 2s mas");
			try {
				Thread.sleep(n+2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			
		}
		System.out.println("Hijo acabado");
	}

}
