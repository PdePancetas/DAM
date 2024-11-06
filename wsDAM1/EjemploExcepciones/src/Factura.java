import java.util.Date;

public class Factura {

	private int numero;
	private Date fecha;

	/**
	 * @return the fecha
	 */
	
	public Factura() {
	}

	public Factura(int numero, Date fecha) {
		this.setNumero(numero);
		this.setFecha(fecha);
	}

	/**
	 * @param fecha the fecha to set
	 */
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
