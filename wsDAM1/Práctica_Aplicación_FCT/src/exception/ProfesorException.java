package exception;

public class ProfesorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4460111844790283853L;

	public ProfesorException() {
		super("El profesor no existe");
	}

	public ProfesorException(String message) {
		super(message);
	}

}
