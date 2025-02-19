package com.pancetas.apirest.dto;

public class PedidoRequest {
	
    private String descripcion;
    private Long usuarioId;  // Aquí solo pasamos el ID del usuario

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
}
