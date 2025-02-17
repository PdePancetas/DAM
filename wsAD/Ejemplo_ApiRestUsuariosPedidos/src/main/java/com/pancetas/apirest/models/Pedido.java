package com.pancetas.apirest.models;

import java.math.BigInteger;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, updatable = true)
	@JsonBackReference
	private Usuario usuario;

	public Pedido() {
		super();
	}

	public Pedido(BigInteger id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pedido id= " + id + ", descripcion= " + descripcion + ", usuario= " + usuario;
	}

}
