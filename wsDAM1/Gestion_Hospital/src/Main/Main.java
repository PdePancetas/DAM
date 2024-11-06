package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Excepciones.DNIFormatException;
import Excepciones.NullPatientException;
import Logica.Ops;
import model.Enfermedad;
import model.Paciente;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int op;
		do {
			System.out.println("Elige una opcion: ");
			System.out.println("  1. Registrar un nuevo paciente.");
			System.out.println("  2. Buscar un paciente por su DNI.");
			System.out.println("  3. Agregar una nueva enfermedad al historial médico de un paciente");
			System.out.println("  4. Consultar el historial médico de un paciente.");
			System.out.println("  5. Listar pacientes por enfermedad.");
			System.out.println("0. Salir \n");
			System.out.print("-> ");

			op = Teclado.leerEntero();

			switch (op) {
			case 1:

				System.out.print("DNI del nuevo paciente: ");
				String dni = Teclado.leerCadena();
				System.out.print("Nombre del nuevo paciente: ");
				String nombre = Teclado.leerCadena();
				System.out.print("Fecha de nacimiento del nuevo paciente: ");
				String fecha = Teclado.leerCadena();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

				try {
					Date fecha_nacimiento = formato.parse(fecha);
					Ops.addPaciente(dni, new Paciente(nombre, fecha_nacimiento));
				} catch (DNIFormatException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				} catch (ParseException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				}

				break;

			case 2:
				System.out.print("DNI del paciente: ");
				dni = Teclado.leerCadena();

				try {
					if (Ops.comprobarDNI(dni))
						Ops.checkPacienteByDNI(dni).toString();
				} catch (DNIFormatException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				} catch (NullPatientException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				}

				break;

			case 3:
				
				try {
					System.out.print("Introduce el dni del paciente: ");
					dni = Teclado.leerCadena();
					System.out.println("Introduce la nueva enfermedad. ");
					System.out.print("Codigo: ");
					int codigo = Teclado.leerEntero();
					System.out.print("Nombre: ");
					nombre = Teclado.leerCadena();

					Ops.addEnfermedadToPaciente(dni, new Enfermedad(nombre, codigo));
				} catch (NullPatientException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				} catch (DNIFormatException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				} catch (NumberFormatException e) {
					System.out.println("\nError: El codigo de la enfermedad no tiene un formato valido.\n");
				} 

				break;

			case 4:

				System.out.print("Introduce el dni del paciente: ");
				dni = Teclado.leerCadena();

				try {
					Ops.checkHistorialMedico(dni);
				} catch (NullPatientException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				} catch (DNIFormatException e) {
					System.out.println("\nError: " + e.getMessage() + "\n");
				}

				break;

			case 5:
				System.out.print("Codigo de la enfermedad: ");
				int codigo = Teclado.leerEntero();
				Ops.listPacienteByEnfermedad(codigo);

				break;

			case 0:
				System.out.println("Programa finalizado.");
				break;

			default:
				System.out.println("Opcion no valida.");

			}
		} while (op != 0);

	}

}
