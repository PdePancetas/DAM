package ListaArray;

import Main.Cliente;

public class MiArrayList<T> {

	T[] datos= (T[]) new Object[0];
	
	public void add(T dato) {
		T[] aux = (T[]) new Object[datos.length+1];
		for(int i=0;i<datos.length;i++)
			aux[i]=datos[i];
		aux[aux.length-1]=dato;
		datos = aux;
	}

	public int size() {
		return datos.length;
	}

	public T get(int i) {
		return datos[i];
	}
	
	
}
