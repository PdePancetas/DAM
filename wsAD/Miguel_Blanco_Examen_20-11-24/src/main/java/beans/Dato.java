package beans;

import java.util.ArrayList;

public class Dato {

	private int idUsuario;
	private String nombreUsuario;
	private ArrayList<Usuario> usuarios;
	private String nombreJuego;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNombreJuego() {
		return nombreJuego;
	}

	public void setNombreJuego(String nombreJuego) {
		this.nombreJuego = nombreJuego;
	}

	public Dato(int idUsuario, String nombreUsuario, ArrayList<Usuario> usuarios, String nombreJuego) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.usuarios = usuarios;
		this.nombreJuego = nombreJuego;
	}

	public Dato() {
		super();
	}

}
