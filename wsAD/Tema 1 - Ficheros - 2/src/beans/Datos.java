package beans;

import java.io.Serializable;

public class Datos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int tlf;
	private String direccion;

	public Datos(int tlf, String direccion) {
		super();
		this.tlf = tlf;
		this.direccion = direccion;
	}

	public Datos() {
		super();
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "tlf=" + tlf + ", direccion=" + direccion;
	}

}
