package com.dam2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProducto;

	@Column(name = "nombre", length = 255, nullable = false)
	private String nombre;

	@Column(name = "precio", nullable = false)
	private Double precio;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detalles;

	public Producto() {
	}

	public Producto(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
}