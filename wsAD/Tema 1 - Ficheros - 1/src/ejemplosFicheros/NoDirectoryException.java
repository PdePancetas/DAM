package ejemplosFicheros;

public class NoDirectoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDirectoryException() {
		super("La ruta no es un directorio");
	}

	public NoDirectoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
