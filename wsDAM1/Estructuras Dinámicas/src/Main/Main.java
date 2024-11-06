package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import ListaArray.MiArrayList;
import ListaEnlazada.MyLinkedList;

public class Main {

	public static void main(String[] args) {
		
		MyLinkedList<Cliente> listaClientes = new MyLinkedList<Cliente>();
		
		listaClientes.addFirst(new Cliente(1,"pepe"));
		listaClientes.addLast(new Cliente(2,"juan"));
		listaClientes.addLast(new Cliente(3,"eva"));
		listaClientes.delPos(1);

		
		
		for(int i=0;i<listaClientes.size();i++) {
			System.out.println(listaClientes.get(i).getNombre());
		}
		
		System.out.println("___________________________________________________________");
		
//		ArrayList<Cliente> clientes = new ArrayList<>();
//		clientes.add(new Cliente(1,"javi"));
//		clientes.add(new Cliente(2,"luis"));
//		clientes.add(new Cliente(3,"alberto"));
//		
//		for(int i=0;i<clientes.size();i++) {
//			System.out.println(clientes.get(i).getNombre());
//		}
//		
		
	}

}
