package Ejercicio5;

import java.util.*;

public class Discoteca implements Disco {

	private String tematica;
	private List<Disco> discos = new LinkedList<>();;

	public Discoteca(String tematica, List<Disco> discos) {
		this.tematica = tematica;
		this.discos = discos;
	}

	public Discoteca() {

	}

	/**
	 * @return the tematica
	 */
	public String getTematica() {
		return tematica;
	}

	/**
	 * @param tematica the tematica to set
	 */
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	/**
	 * @return the discos
	 */
	public List<Disco> getDiscos() {
		return discos;
	}

	/**
	 * @param discos the discos to set
	 */
	public void setDiscos(List<Disco> discos) {
		this.discos = discos;
	}

	@Override
	public void reproducible() {
		// TODO Auto-generated method stub

	}

}
