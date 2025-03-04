package modelJDBC;

import java.sql.Date;
import java.util.Objects;

public class Partido {

	private int idPartido;
	private Date fecha;
	private String lugar;

	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", fecha=" + fecha + ", lugar=" + lugar + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPartido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return idPartido == other.idPartido;
	}

	public Partido() {
		super();
	}

	public Partido(int idPartido, Date fecha, String lugar) {
		super();
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.lugar = lugar;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
