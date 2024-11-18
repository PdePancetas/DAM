package main;

import java.sql.Connection;
import java.sql.SQLException;

import beans.Alumno;
import connection.ConexionBD;
import dao.AlumnoDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Alumno a = new Alumno(5, 2, "Pepito", 10);

		AlumnoDAO alDao = new AlumnoDAO();
		if (alDao.addAlumno(a))
			System.out.println("Se inserto el alumno " + a.getNombre());
		else
			System.out.println("No se pudo insertar a " + a.getNombre());
	}

}
