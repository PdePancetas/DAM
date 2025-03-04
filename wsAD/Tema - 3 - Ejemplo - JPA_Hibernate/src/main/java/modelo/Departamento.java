package modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementada ó .AUTO si no lo es
	private int id;

	// Si el nombre de la propiedad fuera distinto al de la tabla
	// usariamos
	@Column(name = "nombre")
	private String nomDepto;

	@OneToMany(mappedBy = "departamento", targetEntity = Empleado.class, fetch = FetchType.LAZY)
	private List<Empleado> empleados = new ArrayList<>(); //Porque es 1-n con la tabla empleados
	// Para propiedades que no se quieran mapear
	@Transient
	private String notas;

	public Departamento() {
		super();
	}

	public Departamento(int id, String nombre) {
		super();
		this.id = id;
		this.nomDepto = nombre;
	}

	public Departamento(int id, String nomDepto, List<Empleado> empleados) {
		super();
		this.id = id;
		this.nomDepto = nomDepto;
		this.empleados = empleados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomDepto() {
		return nomDepto;
	}

	public void setNomDepto(String nombre) {
		this.nomDepto = nombre;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nomDepto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return id == other.id;
	}

}
