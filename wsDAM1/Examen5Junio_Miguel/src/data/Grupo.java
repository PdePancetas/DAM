package data;

import java.io.Serializable;
import java.util.Objects;

public class Grupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identificador;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Grupo(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Grupo() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(identificador, other.identificador);
	}

}
