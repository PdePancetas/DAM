package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utilidades {

	public static Logger logger = LogManager.getLogger(Utilidades.class);
	
	public static void saludar() {
		logger.info("Entrando en método saludar");
		System.out.println("Hello there");
		logger.error("ALA VERGA UN ERROR");
	}
	
}
