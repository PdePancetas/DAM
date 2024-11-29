package data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Movimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193835860593054465L;
	private Libro libro;
	private Date fecha;
	private String tipo = "";
	private int numCopias;

	@Override
	public int hashCode() {
		return Objects.hash(fecha, libro, numCopias, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(libro, other.libro) && numCopias == other.numCopias
				&& Objects.equals(tipo, other.tipo);
	}

	public Movimiento() {
	}

	public Movimiento(Libro libro, Date fecha, String tipo, int numCopias) throws ParseException {
		this.libro = libro;
		this.fecha = fecha;
		this.tipo = tipo;
		this.numCopias = numCopias;
	}

	/**
	 * @return the libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * @param libro the libro to set
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 * @throws ParseException
	 */
	public void setFecha(String fecha) throws ParseException {

		this.fecha = (new SimpleDateFormat("dd-MM-yy")).parse(fecha);
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the numCopias
	 */
	public int getNumCopias() {
		return numCopias;
	}

	/**
	 * @param numCopias the numCopias to set
	 */
	public void setNumCopias(int numCopias) {
		this.numCopias = numCopias;
	}

	@Override
	public String toString() {
		return "\nMovimiento: \n\tLibro: " + libro.getNombre() + "\n\tFecha: " + (new SimpleDateFormat("dd-MM-yy")).format(fecha)
				+ "\n\tTipo: " + tipo + "\n\tNumero de copias prestadas: " + numCopias + "\n";
	}

}
