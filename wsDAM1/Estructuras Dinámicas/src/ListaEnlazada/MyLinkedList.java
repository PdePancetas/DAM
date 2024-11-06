package ListaEnlazada;

public class MyLinkedList<T> {

	private Nodo<T> inicio;

	public void addFirst(T dato) {
		Nodo<T> nodo = new Nodo<>();
		nodo.dato = dato;
		nodo.ste = inicio;
		inicio = nodo;
	}

	public void addLast(T dato) {
		Nodo<T> aux = inicio;
		Nodo<T> nodo = new Nodo<>();
		nodo.dato = dato;
		while (aux.ste != null) {
			aux = aux.ste;
		}
		aux.ste = nodo;

	}

	public void addPos(int num) {
		Nodo<T> aux = inicio;
		Nodo<T> nodo = new Nodo<>();
		int pos = 0;
		while (pos > 1) { // avanza tantas veces como indique pos hasta antes del indicado a borrar
			aux = aux.ste; // aux avanza una posición
			pos--;
		}
		
		aux.ste= nodo;
		
		

	}

	public void delPos(int pos) {
		Nodo<T> aux = inicio;
		while (pos > 0) { // avanza tantas veces como indique pos hasta antes del indicado a borrar
			aux = aux.ste; // aux avanza una posición
			pos--;
		}

	}

	public void delData(T dato) {
		
		
		
	}

	public T get(int pos) {

		if (pos >= this.size())
			return null;

		Nodo<T> aux = inicio;
		while (pos > 0) { // avanza tantas veces como indique pos
			aux = aux.ste; // aux avanza una posición
			pos--;
		}

		return aux.dato;
	}

	public int size() {
		int cont = 0;
		Nodo<T> aux = inicio;
		while (aux != null) {
			aux = aux.ste;
			cont++;
		}
		return cont;
	}

}