package data;

public class Alumno implements Comparable<Alumno> {
	/*
	 * Clase Alumno: declara el NIA del alumno (String), el nombre (String) y el
	 * c�digo de empresa y de sede dentro de la empresa en la que est� ubicado
	 * (estos dos c�digos se guardan en un array de enteros)
	 */
	private String NIA, nombre;
	private Integer[] codEmpresaSede = new Integer[2];

	public Alumno(String NIA, String nombre) {
		this.NIA = NIA;
		this.nombre = nombre;
	}

	public Alumno() {
	}

	@Override
	public int hashCode() {
		return NIA.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return NIA.equals(other.NIA);
	}

	/**
	 * @return the NIA
	 */
	public String getNIA() {
		return NIA;
	}

	/**
	 * @param NIA the NIA to set
	 */
	public void setNIA(String NIA) {
		this.NIA = NIA;
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
	 * @return the codEmpresaSede
	 */
	public Integer[] getCodEmpresaSede() {
		return codEmpresaSede;
	}

	/**
	 * @param codEmpresaSede the codEmpresaSede to set
	 */
	public void setCodEmpresaSede(Integer[] codEmpresaSede) {
		this.codEmpresaSede = codEmpresaSede;
	}

	public Integer getCodEmpresa() {
		return codEmpresaSede[0];
	}

	public void setCodEmpresa(int cod) {
		this.codEmpresaSede[0] = cod;
	}

	public Integer getCodSede() {
		return codEmpresaSede[1];
	}

	public void setCodSede(int cod) {
		this.codEmpresaSede[1] = cod;
	}

	@Override
	public String toString() {
		return "Alumno [NIA=" + NIA + ", nombre=" + nombre + ", codEmpresa=" + codEmpresaSede[0] + ", codSede="
				+ codEmpresaSede[1] + "]";
	}

	@Override
	public int compareTo(Alumno a) {
		return this.nombre.compareTo(a.nombre);
	}

	public boolean estaEnEmpresa(int codEmpresa) {
		return this.getCodEmpresa() == codEmpresa;
	}

	public boolean estaEnSede(int codSede) {
		return this.getCodSede() == codSede;
	}
}
