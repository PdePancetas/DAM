package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class HistorialMedico {

	private LinkedList<Enfermedad> enfermedades = new LinkedList<>();
	private Date fecha_diagnostico;

	public HistorialMedico() {
		super();
	}

	public HistorialMedico(LinkedList<Enfermedad> enfermedades, Date fecha_diagnostico) {
		super();
		this.enfermedades = enfermedades;
		this.fecha_diagnostico = fecha_diagnostico;
	}

	public LinkedList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(LinkedList<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public Date getFecha_diagnostico() {
		return fecha_diagnostico;
	}

	public void setFecha_diagnostico(Date fecha_diagnostico) {
		this.fecha_diagnostico = fecha_diagnostico;
	}

	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		String historial = "Informe realizado en "+formato.format(fecha_diagnostico)+"\n Enfermedades: \n";
		for (Enfermedad e : enfermedades) {
			historial += "[Codigo: " + e.getCodigo() + "; Nombre " + e.getNombre() + "] \n ";
		}
		return historial + "\n";
	}

}
