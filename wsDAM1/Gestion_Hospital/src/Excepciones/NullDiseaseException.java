package Excepciones;

@SuppressWarnings("serial")
public class NullDiseaseException extends Exception{

	public NullDiseaseException() {
		super("La enfermedad no existe");
		// TODO Auto-generated constructor stub
	}

	public NullDiseaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
