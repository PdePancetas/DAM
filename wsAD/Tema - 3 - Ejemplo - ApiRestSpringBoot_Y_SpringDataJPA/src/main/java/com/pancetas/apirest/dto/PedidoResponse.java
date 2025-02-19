package com.pancetas.apirest.dto;

import com.pancetas.apirest.models.Pedido;

public class PedidoResponse {
    private Long id;
    private String descripcion;
    private Long usuarioId;

    public PedidoResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.descripcion = pedido.getDescripcion();
        this.usuarioId = pedido.getUsuario().getId();
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