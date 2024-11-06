package model;

import java.time.LocalDate;
import java.util.Date;

public class AlumnoFP extends Alumno {

	private int tlfnoPriv;

	public int getTlfnoPriv() {
		return tlfnoPriv;
	}

	public void setTlfnoPriv(int tlfnoPriv) {
		this.tlfnoPriv = tlfnoPriv;
	}

	public AlumnoFP(String nombre, LocalDate fechaNacimiento, Asignatura[] asignaturas, int tlfnoPriv) {
		super(nombre, fechaNacimiento, asignaturas);
		this.tlfnoPriv = tlfnoPriv;
	}

	public AlumnoFP() {
	}

	public AlumnoFP(String nombre) {
		this.nombre = nombre;
	}

	private boolean alumnoAprobado() {
		for (int i = 0; i < asignaturas.length; i++)
			if (((AsignaturaFP) asignaturas[i]).getNota() < 5)
				return false;

		return true;
	}

	@Override
	public String generarTitulo() {
		if (!alumnoAprobado())
			return null;

		Date fechaTitulo = new Date();

		String titulo = "";
		titulo += "El alumno " + nombre + " ha titulado en fecha " + fechaTitulo.toString();
		titulo += fechaTitulo + "\n";
		titulo += " con las siguientes notas: \n";
		for (Asignatura asig : asignaturas) {
			titulo += asig.getNombre() + "--";
			titulo += ((AsignaturaFP) asig).getNota() + "\n";
		}

		return titulo;

	}

}
