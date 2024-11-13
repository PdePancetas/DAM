package PalabraNueva;

public class Launcher {

	//Hacer un programa que cada segundo escribe una palabra por pantalla.
	//El usuario puede cambiar la palabra que se escribe introduciendo una nueva palabra por teclado
	public static void main(String[] args) {
		
		String palabra = "hola";
		PidePalabra p = new PidePalabra(palabra);
		new Thread(p).start();
		new Thread(new Escritor(p),"escritor").start();
	}
}
