package main;

import java.sql.SQLException;

import beans.Alumno;
import beans.Profesor;
import dao.AlumnoDAO;
import dao.ProfesorDAO;

public class Main {

	public static void main(String[] args) throws SQLException {

		AlumnoDAO alDao = new AlumnoDAO();
		ProfesorDAO pDao = new ProfesorDAO();

		Alumno a1 = alDao.retrieveAlumno(1);
		Alumno a2 = alDao.retrieveAlumno(2);
//		if (alDao.addAlumno(a))
//			System.out.println("Se inserto el alumno " + a.getNombre());
//		else
//			System.out.println("No se pudo insertar a " + a.getNombre());

//		System.out.println(alDao.retrieveAlumno(1));

//		for (Alumno al : alDao.retrieveAlumnoV2("Alberto")) {
//			System.out.println(al);
//		}

//		System.out.println(alDao.addAlumnoV2(a));

//		Profesor p = new Profesor(3, "Jorge", "EEDD");
//		System.out.println(pDao.addProferor(p));
//		System.out.println(pDao.deleteProfesor(3));
//		System.out.println(pDao.updateProfesor(p));

//		System.out.println(pDao.retrieveProfesor(2));
//		for (Profesor pr : pDao.retrieveProfesor("Jorge"))
//			System.out.println(pr);

//		alDao.intercambiaNota(a1, a2);
//		alDao.datosAlumnos();
		alDao.eliminaSuspensosSubeNota_v1(50);
	}

}
