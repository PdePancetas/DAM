package logica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utilidades;

public class Main {

	//PATRON SINGLETON: se hace privado el constructor
	//y se genera el objeto con un método,
	//que solo lo instancia si no hay ya uno creado:
	public static Logger logger= LogManager.getLogger(Main.class);	
	
	public static void main(String[] args) {
		
		
		
		//EXISTEN VARIOS NIVELES DE MENSAJES:
		//DEBUG, INFO, AVISO, ERROR, FATAL
		logger.debug("Mensaje de depuración");
		logger.info("Entrando en la aplicación");
		logger.error("Ha ocurrido un error en Main");
		
		Utilidades.saludar();
		
	}

}
