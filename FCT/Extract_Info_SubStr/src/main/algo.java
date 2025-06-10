package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class algo {

	public static void main(String[] args) {
		
		Connection cnx = null; // Aqui usas un patron singleton q eso si os lo ha tenido q dar
		try {
			PreparedStatement ps = cnx.prepareStatement("la sql q te salga de los huevs");
			
			ResultSet rs = ps.executeQuery(); // puede ser executeUpdate u otras opciones dependiendo de lo q haga la sql
			while(rs.next()) { // Itera por todos los registros que salgan de la sql
				
				/*
				 * Si quieres el dato de la columna 1 y es el id
				 */
				int dato1 =  rs.getInt(1);
				//O si quieres el dato sabiendo el nombre de la columna
				dato1 = rs.getInt("Nombre columna");
				
				/*
				 * Si es string
				 */
				String dato = rs.getString(1);
				
				
				/*
				 * Y asi para el resto de datos
				 */
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
