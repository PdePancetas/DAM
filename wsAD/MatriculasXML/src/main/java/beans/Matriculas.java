package beans;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "matriculas")
public class Matriculas {

	private List<Matricula> matriculas = new LinkedList<>();

	@XmlElement(name = "matricula")
	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matriculas(List<Matricula> matriculas) {
		super();
		this.matriculas = matriculas;
	}

	public Matriculas() {
		super();
	}

}
