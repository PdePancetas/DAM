package beansPedido;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;

public class Pedido {

	private Cliente cliente;
	private List<Producto> productos;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@XmlElement(name = "producto")
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
