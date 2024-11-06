package data;

import java.util.HashMap;
import java.util.Objects;

import exception.SedeException;

public class Empresa {
	/*
	 * Clase Empresa: declara el c�digo de empresa (int), el nombre (String) y el
	 * conjunto de sedes (esto �ltimo ser� una estructura HashMap<Integer, String>,
	 * conteniendo el c�digo de sede y ubicaci�n)
	 */
	private int codigoEmpresa;
	private String nombre;
	private HashMap<Integer, String> sedes = new HashMap<>();

//					codigo	 direccion
	@Override
	public int hashCode() {
		return Objects.hash(codigoEmpresa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return codigoEmpresa == other.codigoEmpresa;
	}

	public Empresa(int codigoEmpresa, String nombre) {
		this.codigoEmpresa = codigoEmpresa;
		this.nombre = nombre;
	}

	public Empresa() {
	}

	/**
	 * @return the codigoEmpresa
	 */
	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(int codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the sedes
	 */
	public HashMap<Integer, String> getSedes() {
		return sedes;
	}

	/**
	 * @param sedes the sedes to set
	 */
	public void setSedes(HashMap<Integer, String> sedes) {
		this.sedes = sedes;
	}

	@Override
	public String toString() {
		return "Empresa [codigoEmpresa=" + codigoEmpresa + ", nombre=" + nombre + "]";
	}

	public boolean addSede(int codSede, String dirSede) {
		if (this.sedes.containsKey(codSede))
			return false;
		this.sedes.put(codSede, dirSede);
		return true;
	}

	public boolean checkSede(int codSede) {
		if (this.sedes.containsKey(codSede))
			return true;
		return false;
	}

	public void setSedeToAlumno(Alumno a, int codSede) throws SedeException {
		if (!this.sedes.containsKey(codSede))
			throw new SedeException();

		a.setCodSede(codSede);
	}

}
