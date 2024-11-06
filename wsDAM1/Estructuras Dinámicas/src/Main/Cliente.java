package Main;

public class Cliente {

	private int cod;
	private String nombre;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(int cod, String nombre) {
		this.cod = cod;
		this.nombre = nombre;
	}

}
