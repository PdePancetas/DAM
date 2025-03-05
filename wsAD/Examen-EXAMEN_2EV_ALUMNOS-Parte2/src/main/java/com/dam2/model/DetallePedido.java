package com.dam2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetallePedidoId id;

	@ManyToOne
	@MapsId("idPedido")
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@MapsId("idProducto")
	@JoinColumn(name = "id_producto")
	private Producto producto;

	private Integer cantidad;

	public DetallePedido() {
	}

	public DetallePedido(Pedido pedido, Producto producto, Integer cantidad) {
		this.id = new DetallePedidoId(pedido.getIdPedido(), producto.getIdProducto());
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public DetallePedidoId getId() {
		return id;
	}

	public void setId(DetallePedidoId id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "DetallePedido [pedido=" + pedido.getIdPedido() + ", producto=" + producto.getNombre() + ", cantidad=" + cantidad
				+ "]";
	}
	
	
}
