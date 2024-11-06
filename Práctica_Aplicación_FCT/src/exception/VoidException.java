package exception;

public class VoidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -583326618530244756L;

	public VoidException() {
		super("Algun campo esta vacio!");
	}

	public VoidException(String message) {
		super(message);
	}

}
