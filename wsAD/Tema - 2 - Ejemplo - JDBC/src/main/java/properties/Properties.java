package properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//CLASE CON PATRÓN SINGLETON
public class Properties {
	
	private static java.util.Properties config = null;
	
	public static java.util.Properties getConfig() {
		
		if(config != null)
			return config;
		
		config = new java.util.Properties();
		
		try {
			InputStream inputStream = Properties.class.getClassLoader().getResourceAsStream("bbdd.properties");
			config.load(inputStream);
//			config.load(new FileInputStream("src/main/resources/bbdd.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
}
