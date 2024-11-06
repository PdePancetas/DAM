package exception;

public class SedeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754509800833224830L;

	public SedeException() {
		super("La sede no existe en esta empresa");
	}

	public SedeException(String message) {
		super(message);
	}

}
