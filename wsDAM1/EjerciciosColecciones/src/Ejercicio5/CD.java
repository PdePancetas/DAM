package Ejercicio5;

import java.util.HashSet;
import java.util.Set;

import utilidadesTeclado.Teclado;

public class CD implements Disco {

	private String titulo;
	private Set<Pista> pistas = new HashSet<>();;

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the pistas
	 */
	public Set<Pista> getPistas() {
		return pistas;
	}

	/**
	 * @param pistas the pistas to set
	 */
	public void setPistas(Set<Pista> pistas) {
		this.pistas = pistas;
	}

	@Override
	public void reproducible() {
		System.out.println("Introduce nombre de pista: ");
		String titulo = Teclado.leerCadena();
		for (Pista p : pistas)
			if (p.getTitulo().equals(titulo)) {
				try {
					Thread.sleep(p.getDuracion());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}

		System.out.println(titulo + " no existe en " + this.titulo);
	}

}
