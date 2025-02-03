package modelo;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "departamentos")
@XmlAccessorType(XmlAccessType.NONE)
public class Departamentos {

	private List<Departamento> departamentos;

	@XmlElement(name = "departamento")
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamentos(List<Departamento> departamentos) {
		super();
		this.departamentos = departamentos;
	}

	public Departamentos() {
		super();
	}

}
