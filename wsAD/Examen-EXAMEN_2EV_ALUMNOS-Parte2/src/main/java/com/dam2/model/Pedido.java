package com.dam2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Column(name = "fecha")
	private LocalDateTime fechaPedido;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detalles;

	public Pedido() {
	}

	public Pedido(Usuario usuario, LocalDateTime fechaPedido) {
		this.usuario = usuario;
		this.fechaPedido = fechaPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", usuario=" + usuario.getNombre() + ", fechaPedido=" + fechaPedido + "]";
	}
	
	
}
