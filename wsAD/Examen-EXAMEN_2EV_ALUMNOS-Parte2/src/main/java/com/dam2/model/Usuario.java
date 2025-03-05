package com.dam2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1676125515803462540L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true, nullable = false)
	private Integer id;

	private String nombre;

	private String email;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private List<Pedido> pedidos;

	public Usuario(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public Usuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
