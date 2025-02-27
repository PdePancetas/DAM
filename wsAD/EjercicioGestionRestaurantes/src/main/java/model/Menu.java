package model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Menu {

	private String codigo, nombre;
	private List<String> restaurantes;
	private Set<Plato> platos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<String> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Set<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(Set<Plato> platos) {
		this.platos = platos;
	}

	public Menu(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Menu(String codigo, String nombre, List<String> restaurantes, Set<Plato> platos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.restaurantes = restaurantes;
		this.platos = platos;
	}

	public Menu() {
		super();
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
		Menu other = (Menu) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Menu [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
