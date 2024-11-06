package Logica;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.DataFormatException;

import Excepciones.*;
import model.Enfermedad;
import model.HistorialMedico;
import model.Paciente;

public class Ops {

	private static HashMap<String, Paciente> pacientes = new HashMap<>();

	public static HashMap<String, Paciente> getPacientes() {
		return pacientes;
	}

	public static void setPacientes(HashMap<String, Paciente> pacientes) {
		Ops.pacientes = pacientes;
	}

	/**
	 * añade un paciente a la base de datos
	 * 
	 * @param p
	 * @throws DataFormatException si el dni no tiene el formato correcto
	 * @throws DNIFormatException
	 */
	public static void addPaciente(String dni, Paciente p) throws DNIFormatException {
		if (comprobarDNI(dni))
			if (!pacientes.containsKey(dni)) {
				pacientes.put(dni, p);
				System.out.println("\n" + p.getNombre() + " añadido a pacientes!\n");
			} else
				System.out.println("\nYa hay un paciente con ese dni \n");
		else
			throw new DNIFormatException();
	}

	/**
	 * Consultar paciente mediante su DNI
	 * 
	 * @param dni
	 * @return El paciente cuyo DNI es igual al introducido dni
	 * @throws DNIFormatException   si el dni no es valido
	 * @throws NullPatientException si no existe el paciente con DNI dni
	 */
	public static Paciente checkPacienteByDNI(String dni) throws DNIFormatException, NullPatientException {
		if (comprobarDNI(dni)) {
			if (pacientes.containsKey(dni)) {
				System.out.println("\n" + pacientes.get(dni).toString() + "\n");
				return pacientes.get(dni);
			} else
				throw new NullPatientException();

		} else
			throw new DNIFormatException();
	}

	/**
	 * Añade la enfermedad e al historial del paciente p
	 * 
	 * @param e
	 * @throws DNIFormatException   si el dni no es valido
	 * @throws NullDiseaseException si la enfermedad no existe
	 */
	public static void addEnfermedadToPaciente(String dni, Enfermedad e)
			throws NullPatientException, DNIFormatException {
		if (comprobarDNI(dni)) {
			if (pacientes.containsKey(dni)) {
				if (pacientes.get(dni).getHistorial() == null) {
					pacientes.get(dni).setHistorial(new HistorialMedico(new LinkedList<>(), new Date()));
					pacientes.get(dni).getHistorial().getEnfermedades().add(e);
				} else
					pacientes.get(dni).getHistorial().getEnfermedades().add(e);
				System.out.println(
						"\n" + e.getNombre() + " añadida al historial de " + pacientes.get(dni).getNombre() + ".\n");
			} else
				throw new NullPatientException();
		} else
			throw new DNIFormatException();
	}

	/**
	 * Consulta el historial medico del paciente p
	 * 
	 * @param p
	 * @throws NullPatientException si el paciente no existe en pacientes
	 * @throws DNIFormatException   si el dni introducido no es valido
	 */
	public static void checkHistorialMedico(String dni) throws NullPatientException, DNIFormatException {
		if (comprobarDNI(dni)) {
			if (pacientes.containsKey(dni)) {
				if (pacientes.get(dni).getHistorial() != null) {
					System.out.println(pacientes.get(dni).getHistorial().toString());
				} else
					System.out.println("\n Este paciente aun no tiene ninguna enfermedad asociada \n");

			} else
				throw new NullPatientException();
		} else
			throw new DNIFormatException();
	}

	/**
	 * Muestra el nombre de todos los pacientes y sus DNI si tienen la enfermedad e
	 * 
	 * @param e
	 */
	public static void listPacienteByEnfermedad(int codigo) {
		Set<Entry<String, Paciente>> patients = pacientes.entrySet();
		boolean esta = false;
		for (Entry<String, Paciente> entry : patients) {
			if (entry.getValue().getHistorial() != null)
				for (Enfermedad e : entry.getValue().getHistorial().getEnfermedades())
					if (e.getCodigo() == codigo) {
						System.out.println("- " + entry.getValue().getNombre());
						esta = true;
					}
		}

		if (!esta)
			System.out.println("\n No hay pacientes que tengan esa enfermedad.\n");

	}

	public static boolean comprobarDNI(String dni) throws DNIFormatException {

		if (dni.length() != 9)
			throw new DNIFormatException();
		else {
			String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";

			String numerosDNI = dni.substring(0, 8);
			char letraDNI = dni.charAt(8);
			int valNumDni = Integer.parseInt(numerosDNI) % 23;

			if (dni.length() == 9 && dniChars.charAt(valNumDni) == letraDNI)
				return true;
			else
				return false;
		}

	}

}
