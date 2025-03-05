package model;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "_id" })
public class Libro {

	private ObjectId _id;

	private String nombre, autor;
	private double precio;

	public Libro(ObjectId _id, String nombre, String autor, double precio) {
		super();
		this._id = _id;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
	}

	public Libro() {
		super();
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + "]";
	}

}
