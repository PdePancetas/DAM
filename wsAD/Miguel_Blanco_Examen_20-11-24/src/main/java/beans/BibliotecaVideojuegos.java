package beans;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BibliotecaVideojuegos")
@XmlType(propOrder = { "usuarios", "juegos", "intercambios" })
public class BibliotecaVideojuegos {

	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Juego> juegos = new ArrayList<>();
	private ArrayList<Intercambio> intercambios = new ArrayList<>();

	public BibliotecaVideojuegos() {
		super();
	}

	public BibliotecaVideojuegos(ArrayList<Usuario> usuarios, ArrayList<Juego> juegos,
			ArrayList<Intercambio> intercambios) {
		super();
		this.usuarios = usuarios;
		this.juegos = juegos;
		this.intercambios = intercambios;
	}

	@XmlElementWrapper(name = "usuarios")
	@XmlElement(name = "usuario")
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@XmlElementWrapper(name = "juegos")
	@XmlElement(name = "juego")
	public ArrayList<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Juego> juegos) {
		this.juegos = juegos;
	}

	@XmlElementWrapper(name = "intercambios")
	@XmlElement(name = "intercambio")
	public ArrayList<Intercambio> getIntercambios() {
		return intercambios;
	}

	public void setIntercambios(ArrayList<Intercambio> intercambios) {
		this.intercambios = intercambios;
	}

}
