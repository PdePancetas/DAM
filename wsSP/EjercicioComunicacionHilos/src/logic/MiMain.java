package logic;

import beans.Mensaje;
public class MiMain {

	public static void main(String[] args) {
		
		Mensaje mensaje = new Mensaje("mensaje");
		System.out.println("Mensaje original: "+mensaje.getMensaje());
		MiThread miThread = new MiThread(mensaje);
		Thread t1 = new Thread(miThread);
		t1.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Mensaje modificado por t1: "+mensaje.getMensaje());
		
		Thread t2 = new Thread(miThread);
		t2.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Mensaje modificado por t2: "+mensaje.getMensaje());
		
	}

}
