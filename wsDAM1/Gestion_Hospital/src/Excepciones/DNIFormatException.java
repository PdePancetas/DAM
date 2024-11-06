package Excepciones;

@SuppressWarnings("serial")
public class DNIFormatException extends Exception{

	public DNIFormatException() {
		super("El formato del DNI no es valido");
		// TODO Auto-generated constructor stub
	}

	public DNIFormatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
