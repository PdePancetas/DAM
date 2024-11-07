package beans;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

public class Matricula {

	private String alumno;
	private List<Asignatura> asignaturas = new LinkedList<>();

	@XmlElementWrapper(name = "asignaturas")
	@XmlElement(name = "asignatura")
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public Matricula() {
		super();
	}

	public Matricula(String alumno, List<Asignatura> asignaturas) {
		super();
		this.alumno = alumno;
		this.asignaturas = asignaturas;
	}
}
