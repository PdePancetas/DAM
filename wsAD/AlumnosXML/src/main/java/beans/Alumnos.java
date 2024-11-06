package beans;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumnos")
public class Alumnos {

	private TreeSet<Alumno> alumno = new TreeSet<>(Comparator.comparingDouble(Alumno::getNif));

	@XmlElement(name = "alumno")
	public TreeSet<Alumno> getAlumno() {
		return alumno;
	}

	public void setAlumno(TreeSet<Alumno> alumno) {
		this.alumno = alumno;
	}

	public Alumnos(TreeSet<Alumno> alumno) {
		super();
		this.alumno = alumno;
	}

	public Alumnos() {
		super();
	}

	
	
}
