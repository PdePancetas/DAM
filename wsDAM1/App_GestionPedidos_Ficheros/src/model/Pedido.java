package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4749536797779641707L;
	private int codigo;
	private String nombreCliente;
	private LinkedList<Producto> productos = new LinkedList<>();

	public Pedido() {
	}

	public Pedido(int codigo, String nombreCliente, LinkedList<Producto> productos) {
		this.codigo = codigo;
		this.nombreCliente = nombreCliente;
		this.productos = productos;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the pedidos
	 */
	public LinkedList<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param pedidos the pedidos to set
	 */
	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return codigo == other.codigo;
	}


}
