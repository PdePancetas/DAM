package exception;

public class EmpresaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6094017294470438227L;

	public EmpresaException() {
		super("La empresa no existe");
	}

	public EmpresaException(String message) {
		super(message);
	}

}
