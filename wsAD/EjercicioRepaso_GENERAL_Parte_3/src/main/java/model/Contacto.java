package model;

import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "_id" })
public class Contacto {

	private ObjectId _id;

	private String nombre, email;
	private List<String> tlfos;

	public Contacto() {
		super();
	}

	public Contacto(String nombre, String email, List<String> tlfos) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.tlfos = tlfos;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getTlfos() {
		return tlfos;
	}

	public void setTlfos(List<String> tlfos) {
		this.tlfos = tlfos;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", email=" + email + "]";
	}

}
