package Ejercicio10;

@SuppressWarnings("serial")
public class NegativeNumberException extends RuntimeException {

	public NegativeNumberException(String mensaje) {
		super(mensaje);
	}
	
	public NegativeNumberException() {
		super();
	}
}
