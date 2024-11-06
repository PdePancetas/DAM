package JuegoCartas;

public class Carta {

	private int numCarta;
	private String palo;

	@Override
	public String toString() {
		return (numCarta+1) + " de " + palo;
	}

	public Carta(String palo, int numCarta) {
		super();
		this.numCarta = numCarta;
		this.palo = palo;
	}

	public int getNumCarta() {
		return numCarta;
	}

	public void setNumCarta(int numCarta) {
		this.numCarta = numCarta;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public Carta() {
	}

}
