package Excepciones;

@SuppressWarnings("serial")
public class NullPatientException extends Exception{

	public NullPatientException() {
		super("El paciente no existe");
		// TODO Auto-generated constructor stub
	}

	public NullPatientException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
