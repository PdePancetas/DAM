package beans;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "idJuego", "titulo", "consola", "estado" })
public class Juego {

	private int idJuego;
	private String titulo, consola, estado;

	public Juego() {
		super();
	}

	public Juego(int idJuego, String titulo, String consola, String estado) {
		super();
		this.idJuego = idJuego;
		this.titulo = titulo;
		this.consola = consola;
		this.estado = estado;
	}

	@XmlElement(name = "ID_Juego")
	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConsola() {
		return consola;
	}

	public void setConsola(String consola) {
		this.consola = consola;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idJuego);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juego other = (Juego) obj;
		return idJuego == other.idJuego;
	}

	@Override
	public String toString() {
		return "Juego [idJuego=" + idJuego + ", titulo=" + titulo + ", consola=" + consola + ", estado=" + estado + "]";
	}
	
	

}
