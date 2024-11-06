package Ejercicio10;

import java.util.HashMap;
import java.util.Objects;

import javax.swing.JOptionPane;


public class Pedido2 {
	
	private static HashMap<String, Pedido2> pedidos2 = new HashMap<>();
	
	private int tipo;
	private String descripcion;
	private double precio;

	public Pedido2() {
	}

	public Pedido2(int tipo, String descripcion, double precio) {
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.precio = precio;
		if(precio<0)
			throw new NegativeNumberException();
	}

	public static HashMap<String, Pedido2> getPedidos2() {
		HashMap<String,Pedido2> ret = new HashMap<>();
		ret.putAll(pedidos2);
		return ret;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Pedido [tipo: " + tipo + ", descripcion: " + descripcion + ", precio: " + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido2 other = (Pedido2) obj;
		return Objects.equals(descripcion, other.descripcion) && tipo == other.tipo;
	}
	
	public static void addPedido(String cod, Pedido2 pedido) {
		if (!pedidos2.containsValue(pedido))
			pedidos2.put(cod, pedido);
	}
	
	public static Pedido2 checkPedido(String cod) {
		
		if(pedidos2.containsKey(cod))
			return pedidos2.get(cod);
		else  {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el pedido!");
			return null;
		}
	}
	
	public static boolean delPedido(String cod) {
		if(!pedidos2.containsKey(cod))
			return false;
		
		pedidos2.remove(cod);
		return true;
	}
	
}
