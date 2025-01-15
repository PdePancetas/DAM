package Ejer4;

import java.net.Socket;

public class Mensaje {
	private int puerto;
	private boolean change = false;
	 Socket s=null;
	 boolean primero=false;

	public Mensaje(int x) {
		this.puerto = x;  
	}

	public synchronized boolean isChange() {
		return change;
	}

	public synchronized void setChange(boolean change) {
		this.change = change;
	}

	public synchronized void setPuerto(int x) {
		this.puerto = x;
		change = true;
		notifyAll();
	}

	public synchronized int getPuerto() {
		return puerto;
	}

}
