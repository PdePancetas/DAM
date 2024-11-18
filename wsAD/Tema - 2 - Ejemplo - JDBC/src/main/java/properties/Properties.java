package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {
	
	private static java.util.Properties config = null;
	
	public static java.util.Properties getConfig() {
		
		if(config != null)
			return config;
		
		config = new java.util.Properties();
		
		try {
			config.load(new FileInputStream("src/main/resources/bbdd.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
}
