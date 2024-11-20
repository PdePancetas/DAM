package beans;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "idIntercambio", "idEmisor", "idReceptor", "idJuego" })
public class Intercambio {

	private int idIntercambio, idEmisor, idReceptor, idJuego;

	public Intercambio() {
		super();
	}

	public Intercambio(int idIntercambio, int idEmisor, int idReceptor, int idJuego) {
		super();
		this.idIntercambio = idIntercambio;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
		this.idJuego = idJuego;
	}

	@XmlElement(name="ID_Intercambio")
	public int getIdIntercambio() {
		return idIntercambio;
	}

	public void setIdIntercambio(int idIntercambio) {
		this.idIntercambio = idIntercambio;
	}

	@XmlElement(name="ID_Usuario_Emisor")
	public int getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(int idEmisor) {
		this.idEmisor = idEmisor;
	}

	@XmlElement(name="ID_Usuario_Receptor")
	public int getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(int idReceptor) {
		this.idReceptor = idReceptor;
	}

	@XmlElement(name="ID_Juego")
	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idIntercambio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intercambio other = (Intercambio) obj;
		return idIntercambio == other.idIntercambio;
	}

	@Override
	public String toString() {
		return "Intercambio [idIntercambio=" + idIntercambio + ", idEmisor=" + idEmisor + ", idReceptor=" + idReceptor
				+ ", idJuego=" + idJuego + "]";
	}
	
	

}
