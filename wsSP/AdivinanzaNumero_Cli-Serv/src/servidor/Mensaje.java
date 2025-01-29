package servidor;

public class Mensaje {

	private int numConexion;
	private Object datos;

	public int getNumConexion() {
		return numConexion;
	}

	public void setNumConexion(int numConexion) {
		this.numConexion = numConexion;
	}

	public Object getDatos() {
		return datos;
	}

	public void setDatos(Object datos) {
		this.datos = datos;
	}

	public Mensaje() {
		super();
	}

	public Mensaje(int id, Object datos) {
		super();
		this.numConexion = id;
		this.datos = datos;
	}

	
	
	
}
