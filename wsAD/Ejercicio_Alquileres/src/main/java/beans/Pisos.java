package beans;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
@XmlRootElement(name = "pisos")
public class Pisos {
	
	private ArrayList<Piso> pisos;

	@XmlElement(name = "piso")
	public ArrayList<Piso> getPisos() {
		return pisos;
	}

	public void setPisos(ArrayList<Piso> pisos) {
		this.pisos = pisos;
	}
	

	public Pisos() {
		super();
		this.pisos = new ArrayList<Piso>();
	}

	public Pisos(ArrayList<Piso> pisos) {
		super();
		this.pisos = pisos;
	}


	@XmlType(propOrder = {"direccion","mensualidad","nif_Empleado"})
	public static class Piso {

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
			return "Piso [codigo=" + codigo + ", direccion=" + direccion + ", mensualidad=" + mensualidad
					+ ", alquilado=" + alquilado + ", nif_Empleado=" + nif_Empleado + "]";
		}
		
		

	}

}
