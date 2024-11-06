package model;

import java.time.LocalDate;
import java.util.Date;

public class AlumnoSecundaria extends Alumno {

	private String nombreTutor;
	private int tlfnoTutor;

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public int getTlfnoTutor() {
		return tlfnoTutor;
	}

	public void setTlfnoTutor(int tlfnoTutor) {
		this.tlfnoTutor = tlfnoTutor;
	}

	public AlumnoSecundaria(String nombre, LocalDate fechaNacimiento, Asignatura[] asignaturas, String nombreTutor,
			int tlfnoTutor) {
		super(nombre, fechaNacimiento, asignaturas);
		this.nombreTutor = nombreTutor;
		this.tlfnoTutor = tlfnoTutor;
	}

	public AlumnoSecundaria() {
	}

	public AlumnoSecundaria(String nombre) {
		this.nombre = nombre;
	}

	private boolean alumnoAprobado() {
		
		//Considerando que por debajo de aprobado (Suficiente, Insuficiente) es suspenso.
		for (int i = 0; i < asignaturas.length; i++)
			if (!((AsignaturaSecundaria) asignaturas[i]).getNota().equals("Aprobado")
					|| !((AsignaturaSecundaria) asignaturas[i]).getNota().equals("Notable")
					|| !((AsignaturaSecundaria) asignaturas[i]).getNota().equals("Sobresaliente"))
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
			titulo += ((AsignaturaSecundaria) asig).getNota();
		}
		return titulo;

	}

}
