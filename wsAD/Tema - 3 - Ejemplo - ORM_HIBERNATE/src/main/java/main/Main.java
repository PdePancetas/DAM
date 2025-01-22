package main;

import java.sql.SQLException;

import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.ConfigHelper;

import com.mysql.cj.xdevapi.SessionFactory;

import controllers.EmpleadoController;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws SQLException {
		//Create
//		String usuario = new EmpleadoController().createEmpleado("1112222", "Michael");
//		System.out.println(usuario);
		
		//Delete
//		System.out.println(new EmpleadoController().deleteEmpleado("1112222"));
		
		//Update
//		System.out.println(new EmpleadoController().updateNombreEmpleado("123", "José Pérez"));
		
		//Read
		System.out.println(new EmpleadoController().getEmpleado("12"));
		
		
		
		
	}

}
