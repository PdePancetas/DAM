package model;

public class JugadorTramposo extends Jugador{
	
	int numeroAElegir;

	public JugadorTramposo(String nombre) {
		super(nombre, 0);
	}

	public int getNumeroAElegir() {
		return numeroAElegir;
	}

	public void setNumeroAElegir(int numeroAElegir) {
		this.numeroAElegir = numeroAElegir;
	}

	
	

}
