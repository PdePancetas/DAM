package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Paciente {

	private String nombre;
	private Date fecha_nacimiento;
	private HistorialMedico historial;

	public Paciente() {
		super();
	}

	public Paciente(String nombre, Date fecha_nacimiento) {
		super();
		this.nombre = nombre;
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public HistorialMedico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialMedico historial) {
		this.historial = historial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha_nacimiento, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(fecha_nacimiento, other.fecha_nacimiento) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return "Paciente [nombre= " + nombre + ", fecha_nacimiento= " + formato.format(fecha_nacimiento) + "]";
	}
	

}
