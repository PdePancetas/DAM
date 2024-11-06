package beansPedido;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pedidos")
public class Pedidos {

	private List<Pedido> pedidos;

	public Pedidos() {
		super();
	}

	public Pedidos(List<Pedido> pedidos) {
		super();
		this.pedidos = pedidos;
	}

	@XmlElement(name = "pedido")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
