package ListaEnlazada;

public class Nodo<T> {

  //2 campos obligatorios.

	//1. El dato en sí y 2. la dirección de memoria del siguiente nodo.
	
	
	/*1*/	T dato;
	/*2*/	Nodo<T> ste;
	
	
	public Nodo(T dato, Nodo<T> ste) {
		this.dato = dato;
		this.ste = ste;
	}
	
	Nodo() {
			
	}
	
}
