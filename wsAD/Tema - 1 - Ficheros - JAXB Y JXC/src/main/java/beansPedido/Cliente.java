package beansPedido;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "nombre", "nif" })
public class Cliente {

	private int id;
	private String nombre, nif;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Cliente() {
		super();
	}

	public Cliente(String nombre, String nif) {
		super();
		this.nombre = nombre;
		this.nif = nif;
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
