package properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletContext;

//CLASE CON PATRÓN SINGLETON
public class Properties {
	
	private static java.util.Properties config = null;
	
	public static java.util.Properties getConfig(ServletContext context) {
		
		if(config != null)
			return config;
		
		config = new java.util.Properties();
		
		try {
            // Cargar el archivo .properties desde la ruta relativa
            InputStream is = context.getResourceAsStream("/WEB-INF/config.properties");
            config.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
}
