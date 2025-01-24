package main;

import java.sql.Date;
import java.sql.SQLException;

import controllers.EmpleadoController;
import logic.Func;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws SQLException {
		Func.incorporarProyecto(new Date(0), new Date(0), "ProyectoPrueba", null);
	}

}
