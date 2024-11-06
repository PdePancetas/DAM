package main;

import java.util.HashMap;

import utilidadesTeclado.Teclado;

public class Main {
	
	public static void main(String[] args) {

	    HashMap<String,Float> listaProductos = new HashMap<String,Float>();
	    
	    int opcionElegida = 0;
	    float precio;
	    String codigo;
	    
	    while (opcionElegida != 5) {
	        System.out.println("Introduce el numero de la opción que quieras:");
	        System.out.println("1.- Introducir producto");
	        System.out.println("2.- Modificar precio");
	        System.out.println("3.- Mostrar todos los productos");
	        System.out.println("4.- Eliminar producto");
	        System.out.println("5.- Salir");
	        opcionElegida = Teclado.leerEntero();

	        switch (opcionElegida) {
	            case 1:
	                System.out.println("Introduce el códido del producto:");
	                codigo = Teclado.leerCadena();
	                System.out.println("Introduce el precio del producto:");
	                precio = Teclado.leerEntero();
	                guardarProducto(codigo, precio, listaProductos);
	                break;
	            case 2:
	                System.out.println("Introduce el códido del producto del que quieres cambiar el precio:");
	                codigo = Teclado.leerCadena();
	                modificaPrecio(codigo, listaProductos);
	                break;
	            case 3:
	                mostrarProductos(listaProductos);
	                break;
	            case 4:
	                System.out.println("Introduce el códido del producto que quieres eliminar:");
	                codigo = Teclado.leerCadena();
	                eliminaProducto(codigo, listaProductos);
	                break;
	            case 5:
	                break;   // Si la opcion es 5 no se hace nada 
	            default:
	                System.out.println("Tienes que introducir una opcion valida");
	        }

	    }
	}
	
	
	private static void guardarProducto(String codigo, float precio, HashMap<String, Float> listaProductos) {
	}
	
	private static void modificaPrecio(String codigo, HashMap<String, Float> listaProductos) {
		
	}
	
	private static void mostrarProductos(HashMap<String, Float> listaProductos) {
		
	}
	
	private static void eliminaProducto(String codigo, HashMap<String, Float> listaProductos) {
		
	}

	

	

	
}
