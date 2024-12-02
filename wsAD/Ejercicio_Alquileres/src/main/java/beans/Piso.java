package beans;

import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "direccion", "mensualidad", "nif_Empleado" })
public class Piso {

	private int codigo;
	private String direccion;
	private double mensualidad;
	private boolean alquilado;
	private int nif_Empleado;

	@XmlTransient
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getMensualidad() {
		return mensualidad;
	}

	public void setMensualidad(double mensualidad) {
		this.mensualidad = mensualidad;
	}

	@XmlTransient
	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public int getNif_Empleado() {
		return nif_Empleado;
	}

	public void setNif_Empleado(int nif_Empleado) {
		this.nif_Empleado = nif_Empleado;
	}

	public Piso(int codigo, String direccion, double mensualidad, boolean alquilado, int nif_Empleado) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.mensualidad = mensualidad;
		this.alquilado = alquilado;
		this.nif_Empleado = nif_Empleado;
	}

	public Piso() {
		super();
	}

	@Override
	public String toString() {
		return "Piso [codigo=" + codigo + ", direccion=" + direccion + ", mensualidad=" + mensualidad + ", alquilado="
				+ alquilado + ", nif_Empleado=" + nif_Empleado + "]";
	}

}
