package com.pancetas.apirest.dto;

import java.io.Serializable;

import com.pancetas.apirest.models.Pedido;

public class PedidoResponse implements Serializable {
	private Long id;
	private String descripcion;
	private Long usuarioId;

	public PedidoResponse() {
		super();
	}

	public PedidoResponse(Pedido pedido) {
		this.id = pedido.getId();
		this.descripcion = pedido.getDescripcion();
		// Asegúrate de asignar el ID del usuario:
		this.usuarioId = (pedido.getUsuario() != null) ? pedido.getUsuario().getId() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "PedidoResponse [id=" + id + ", descripcion=" + descripcion + ", usuarioId=" + usuarioId + "]";
	}

}