package logic;

import beans.Mensaje;

public class MiThread implements Runnable {

	private Mensaje mensaje;

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public MiThread(Mensaje mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public MiThread() {
		super();
	}

	@Override
	public void run() {
		this.getMensaje().setMensaje(this.getMensaje().getMensaje()+" hola");
		
	}
	
	
	
}
