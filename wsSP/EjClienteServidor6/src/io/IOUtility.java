package io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IOUtility {
	
	public static void escribir(BufferedWriter bw,String msg)
	{
		try {
		bw.write(msg);
		bw.newLine();
		bw.flush();
		}catch(Exception e) {}
	}
	
	public static String leer(BufferedReader br) throws IOException
	{
		String mensaje ="";
	
			mensaje = br.readLine();
			System.out.println(mensaje);
		
		return mensaje;
	}
}
