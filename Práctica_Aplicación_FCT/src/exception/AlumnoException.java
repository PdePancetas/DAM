package exception;

public class AlumnoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5006038032638125983L;

	public AlumnoException() {
		super("El alumno no existe");
	}

	public AlumnoException(String message) {
		super(message);
	}

}
