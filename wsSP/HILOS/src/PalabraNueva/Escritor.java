package PalabraNueva;

public class Escritor implements Runnable {

	private PidePalabra pedidorPalabra;

	@Override
	public void run() {
		long actual = System.nanoTime();
		while (true) {
			while (System.nanoTime() - actual < 1000000000l) {

			}

			System.out.println("palabra: " + pedidorPalabra.getPalabra());

			actual = System.nanoTime();
		}
	}

	public Escritor(PidePalabra pedidorPalabra) {
		super();
		this.pedidorPalabra = pedidorPalabra;
	}

}
