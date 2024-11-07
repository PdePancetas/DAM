package Comunicacion_Join_Interrupt;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main iniciado");
		Hijo hijo = new Hijo();
		Thread t1 = new Thread(hijo);
		long init = System.currentTimeMillis();
		t1.start();
		System.out.println("hijo vivo: "+t1.isAlive());
		try {
			t1.interrupt();
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hijo vivo: "+t1.isAlive());
		System.out.println("Tiempo: "+(System.currentTimeMillis()-init)+" ms");
		System.out.println("Main acabado");
	}

}
