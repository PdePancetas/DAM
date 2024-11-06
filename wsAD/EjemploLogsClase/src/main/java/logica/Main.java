package logica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	public static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		logger.debug("Mensaje de depuración de Main");
		//EXISTEN VARIOS NIVELES DE MENSAJES:
		//DEBUG, INFO, WARN, ERROR, FATAL
		logger.info("Entrando en la aplicación");
		
		logger.error("Ha ocurrido un error en Main");
		
		utils.Utilidades.saludar();
		
		

	}

}
