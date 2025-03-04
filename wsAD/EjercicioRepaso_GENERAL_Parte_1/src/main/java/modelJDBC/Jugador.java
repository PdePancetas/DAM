package modelJDBC;

import java.util.Objects;

public class Jugador {

	private int idJugador;
	private String nombre;
	private int numeroCamiseta;

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", nombre=" + nombre + ", numeroCamiseta=" + numeroCamiseta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idJugador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return idJugador == other.idJugador;
	}

	public Jugador() {
		super();
	}

	public Jugador(int idJugador, String nombre, int numeroCamiseta) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.numeroCamiseta = numeroCamiseta;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(int numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

}
