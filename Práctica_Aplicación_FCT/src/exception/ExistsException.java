package exception;

public class ExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8831505851153882819L;

	public ExistsException() {
		super("El elemento ya existe");
	}

	public ExistsException(String message) {
		super(message);
	}

}
