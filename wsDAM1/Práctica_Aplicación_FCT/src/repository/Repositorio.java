package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

import data.Alumno;
import data.Empresa;
import data.Profesor;
import exception.AlumnoException;
import exception.EmpresaException;
import exception.ExistsException;
import exception.ProfesorException;
import exception.SedeException;
import exception.VoidException;

public class Repositorio {

	/*
	 * Los datos de la aplicaci�n se guardar�n en un TreeSet en el caso de Alumno y
	 * Profesor, y en un HashSet en el caso de Empresa. El objetivo de guardar a los
	 * alumnos y profesores en un TreeSet es que cuando iteremos en dichas
	 * colecciones queremos que se haga en orden alfab�tico de nombres. Estos tres
	 * Set ser�n variables est�ticas de una clase Repositorio.
	 */

	private static HashSet<Empresa> empresas = new HashSet<>();
	private static TreeSet<Alumno> alumnos = new TreeSet<>(
			Comparator.comparing(Alumno::getNIA) /* new OrdenNombresAlumnos() */);
	public static TreeSet<Profesor> profesores = new TreeSet<>(Comparator.comparing(Profesor::getNifProfesor));

	/*
	 * 1. Alta profesor dados nif y nombre. El conjunto de alumnos estará vacío.
	 * 
	 * 2. Alta alumno dados NIA y nombre. No tendrá empresa asignada.
	 * 
	 * 3. Alta empresa dado código y nombre. No tendrá sedes.
	 * 
	 * 4. Alta sede en empresa, dado código de empresa y código y dirección de sede.
	 * En las cuatro funcionalidades anteriores, si se trata de dar de alta un
	 * elemento que ya existe se avisará al usuario y no se hará nada más.
	 * 
	 * 5. Asignar empresa a alumno dado código de empresa, código de sede y NIA de
	 * alumno. Si no existiera la empresa o la sede dentro de la empresa debe
	 * lanzarse una excepción EmpresaSedeException. Si no existe el alumno se
	 * lanzará una excepción AlumnoException.
	 * 
	 * 6. Asignar alumno a profesor dado NIA de alumno y nif de profesor. Si no
	 * existe el alumno se lanzará una excepción AlumnoException. Si no existe el
	 * profesor se lanzará una excepción ProfesorException.
	 * 
	 * 7. Dado nif de profesor, mostrar el nombre de alumno y nombre de empresa de
	 * todos los alumnos que tenga asignados y que tengan empresa. Si no existe el
	 * profesor se lanzará una excepción ProfesorException.
	 * 
	 * 8. Mostrar los nombres de alumnos que aún no tengan empresa asignada.
	 * 
	 * 9. Mostrar el nombre del profesor que tenga más alumnos con empresa.
	 * 
	 * 10. Dado un código de empresa y dos códigos de sede, cambiar a todos los
	 * alumnos que estén en esa empresa de la primera sede a la segunda. Si no
	 * existiera la empresa o la sede dentro de la empresa debe lanzarse una
	 * excepción EmpresaSedeException.
	 * 
	 * 11. Eliminar una empresa dado código. Verificar antes que la empresa no tiene
	 * alumnos asignados, si los tiene avisar y no hacer nada. Si no existiera la
	 * empresa debe lanzarse una excepción EmpresaSedeException.
	 * 
	 * 12. Dado un nif de profesor, crear un fichero con el nombre del profesor con
	 * los nombres de todos sus alumnos en líneas separadas.
	 */

	/*
	 * 1.altaProfesor 2.altaAlumno 3.altaEmpresa 4.altaSede 5.asignarEmpresaAlumno
	 * 6.asignarAlumnoProfesor 7.mostrarAlumnosEmpresasProfesor
	 * 8.mostrarAlumnosSinEmpresa 9.profesorConMasAlumnosEmpresa
	 * 10.cambiarAlumnosSedeEmpresa 11.eliminarEmpresa
	 * 12.crearFicheroNombresAlumnosProfesor
	 */

	//
	public static boolean checkEmpresa(int codEmpresa) {
		return empresas.contains(new Empresa(codEmpresa, ""));
	}

	public static boolean checkProfesor(String nifProfesor) {
		return profesores.contains(new Profesor(nifProfesor, ""));
	}

	public static boolean checkAlumno(String NIA) {
		return alumnos.contains(new Alumno(NIA, ""));
	}
	//

	// 1
	public static boolean altaProfesor(String nifProfesor, String nombre) throws ExistsException, VoidException {

		if (nifProfesor.equals(""))
			throw new VoidException();

		if (nombre.equals(""))
			throw new VoidException();

		if (checkProfesor(nifProfesor))
			throw new ExistsException();

		return profesores.add(new Profesor(nifProfesor, nombre));
	}

	// 2
	public static boolean altaAlumno(String NIA, String nombre) throws ExistsException, VoidException {

		if (NIA.equals(""))
			throw new VoidException();

		if (nombre.equals(""))
			throw new VoidException();

		if (checkAlumno(NIA))
			throw new ExistsException();

		return alumnos.add(new Alumno(NIA, nombre));
	}

	// 3
	public static boolean altaEmpresa(int codEmpresa, String nombre) throws ExistsException, VoidException {

		if ((codEmpresa + "").equals(""))
			throw new VoidException();

		if (nombre.equals(""))
			throw new VoidException();

		if (checkEmpresa(codEmpresa))
			throw new ExistsException();

		return empresas.add(new Empresa(codEmpresa, nombre));
	}

	// 4
	public static boolean altaSede(int codEmpresa, int codSede, String dirSede)
			throws ExistsException, EmpresaException, VoidException {

		if ((codEmpresa + "").equals(""))
			throw new VoidException();
		if ((codSede + "").equals(""))
			throw new VoidException();
		if (dirSede.equals(""))
			throw new VoidException();

		if (!checkEmpresa(codEmpresa))
			throw new EmpresaException();

		for (Empresa e : empresas)
			if (e.getCodigoEmpresa() == codEmpresa) {
				if (e.addSede(codSede, dirSede))
					return true;
				else
					throw new ExistsException();
			}
		return false;
	}

	// 5
	public static void asignarEmpresaAlumno(int codEmpresa, int codSede, String NIA)
			throws AlumnoException, EmpresaException, SedeException, VoidException, ExistsException {

		if ((codEmpresa + "").equals(""))
			throw new VoidException();
		if ((codSede + "").equals(""))
			throw new VoidException();
		if (NIA.equals(""))
			throw new VoidException();

		if (!checkEmpresa(codEmpresa))
			throw new EmpresaException();
		if (!checkAlumno(NIA))
			throw new AlumnoException();

		for (Empresa e : empresas)
			if (e.getCodigoEmpresa() == codEmpresa)
				if (!e.checkSede(codSede))
					throw new SedeException();

		Alumno alumno = null;
		for (Alumno a : alumnos)
			if (a.getNIA().equals(NIA))
				alumno = a;

		Empresa empresa = null;
		for (Empresa e : empresas)
			if (e.getCodigoEmpresa() == codEmpresa)
				empresa = e;

		for (Alumno a : alumnos)
			if (a.getNIA().equals(NIA) && a.getCodSede() != null && a.getCodSede().equals(codSede)
					&& a.getCodEmpresa().equals(codEmpresa))
				throw new ExistsException();

		alumno.setCodEmpresa(codEmpresa);
		empresa.setSedeToAlumno(alumno, codSede);

	}

	// 6
	public static void asignarAlumnoProfesor(String NIA, String nifProfesor)
			throws AlumnoException, ProfesorException, VoidException, ExistsException {

		if (NIA.equals(""))
			throw new VoidException();
		if (nifProfesor.equals(""))
			throw new VoidException();

		if (!checkProfesor(nifProfesor))
			throw new ProfesorException();
		if (!checkAlumno(NIA))
			throw new AlumnoException();

		Profesor profesor = null;
		for (Profesor p : profesores)
			if (p.getNifProfesor().equals(nifProfesor))
				profesor = p;

		Alumno alumno = null;
		for (Alumno a : alumnos)
			if (a.getNIA().equals(NIA))
				alumno = a;

		if (profesor.checkAlumno(alumno))
			throw new ExistsException();

		profesor.addAlumno(alumno);
	}

	// 7
	public static String mostrarAlumnosEmpresasProfesor(String nifProfesor) throws ProfesorException {

		if (!checkProfesor(nifProfesor))
			throw new ProfesorException();

		Profesor profesor = null;
		for (Profesor p : profesores)
			if (p.getNifProfesor().equals(nifProfesor))
				profesor = p;

		return profesor.getAlumnosEmpresa();
	}

	// 8
	public static String mostrarAlumnosSinEmpresa() {

		boolean hay = false;
		String s = "Alumnos sin empresa asignada: \n";
		for (Alumno a : alumnos)
			if (a.getCodEmpresa() == null) {
				hay = true;
				s += "Nombre: " + a.getNombre() + "; NIA: " + a.getNIA() + "\n";
			}

		if (hay)
			return s;
		else
			return s + "No hay";
	}

	// 9
	public static String profesorConMasAlumnosEmpresa() {
		Profesor maxProfesor = null;
		for (Profesor profesor : profesores) {
			if (maxProfesor == null || profesor.getNumAlumnosEmpresa() > maxProfesor.getNumAlumnosEmpresa()) {
				maxProfesor = profesor;
			}
		}

		if (maxProfesor == null)
			return "No hay profesores o profesores con alumnos en empresas";

		return maxProfesor.getNombre();
	}

	// 10
	public static void cambiarAlumnosSedeEmpresa(int codEmpresa, int codAntSede, int codNewSede)
			throws EmpresaException, SedeException, VoidException, ExistsException {

		if ((codEmpresa + "").equals(""))
			throw new VoidException();
		if ((codAntSede + "").equals(""))
			throw new VoidException();
		if ((codNewSede + "").equals(""))
			throw new VoidException();

		if (!checkEmpresa(codEmpresa))
			throw new EmpresaException();

		boolean estaAnt = false;
		for (Empresa e : empresas)
			if (e.checkSede(codAntSede))
				estaAnt = true;

		if (!estaAnt)
			throw new SedeException();

		boolean estaNew = false;
		for (Empresa e : empresas)
			if (e.checkSede(codNewSede))
				estaNew = true;

		if (!estaNew)
			throw new SedeException();

		boolean hay = false;
		for (Alumno a : alumnos)
			if (a.getCodEmpresa() != null && a.estaEnEmpresa(codEmpresa))
				if (a.estaEnSede(codAntSede) && a.getCodSede() != codNewSede) {
					hay = true;
					a.setCodSede(codNewSede);
				}
		if (!hay)
			throw new ExistsException();
	}

	// 11
	public static boolean eliminarEmpresa(int codEmpresa) throws EmpresaException {
		if (!checkEmpresa(codEmpresa))
			throw new EmpresaException();

		for (Alumno a : alumnos)
			if (a.estaEnEmpresa(codEmpresa))
				return false;

		return empresas.remove(new Empresa(codEmpresa, null));

	}

	// 12
	public static File crearFicheroNombresAlumnosProfesor(String nifProfesor)
			throws ProfesorException, IOException, ExistsException, VoidException {

		if (nifProfesor.equals(""))
			throw new VoidException();

		if (!checkProfesor(nifProfesor))
			throw new ProfesorException();

		Profesor profesor = null;
		for (Profesor p : profesores)
			if (p.getNifProfesor().equals(nifProfesor))
				profesor = p;

		FileWriter fw = new FileWriter(profesor.getNombre());
		if (profesor.getAlumnosProfesor().size() != 0) {
			for (Alumno a : profesor.getAlumnosProfesor()) {
				fw.write(a.toString() + "\n");
			}
			fw.close();
			return new File("Alumnos de " + profesor.getNombre());
		} else {
			fw.close();
			throw new ExistsException();
		}

	}

}
