
@SuppressWarnings("serial")
public class PosteriorDateException extends Exception {

	public PosteriorDateException() {
		super("La fecha de la factura no es v�lida porque es posterior a la actual.");
		
	}

	public PosteriorDateException(String message) {
		super(message);
		
	}
	
	
	
}
