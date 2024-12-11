package menu;

import beans.Empleado;
import beans.Piso;
import dao.EmpleadoImpl;
import dao.PisoImpl;
import func.Func;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int op = -1;
		do {

			/*
			 * 
			 * Los requisitos de la aplicación Java serán los siguientes:
			 * 
			 * - Insertar tanto empleados como pisos (en principio sin alquilar) - Modificar
			 * la mensualidad de un piso - Cambiar en un piso el empleado que lo lleva -
			 * Alquilar piso/dejar de alquilarlo - Dado un código de piso, mostrar el nombre
			 * del empleado que lo lleva - - Si el sueldo mensual del empleado es su sueldo
			 * base más un 10% de la mensualidad de cada piso que tenga alquilado, mostrar
			 * el sueldo de un empleado dado su nif - Mostrar el empleado que más pisos
			 * alquilados tenga - Dado un xml que contenga información con la siguiente
			 * estructura: <pisos> <piso> <direccion>xxxx<direccion>
			 * <mensualidad>xxxxx<mensualidad> <nif_empleado>xxxxxx<nif_empleado> </piso> ……
			 * </pisos> Incorporar dichos pisos en la base de datos. Hacerlo usando tanto
			 * DOM como JAXB
			 * 
			 * - Generar un informe con todos los pisos que están alquilados, mostrando su
			 * dirección y el nombre del empleado que se encarga
			 * 
			 * 
			 * 
			 */

			EmpleadoImpl empleado = new EmpleadoImpl();
			PisoImpl piso = new PisoImpl();
			try {
				System.out.println("\n____________________________________");
				System.out.println("Escoge una opcion ");
				System.out.println(" 1. Añadir empleado ");
				System.out.println(" 2. Añadir piso ");
				System.out.println(" 3. Modificar mensualidad de un piso ");
				System.out.println(" 4. Cambiar en un piso el empleado que lo lleva ");
				System.out.println(" 5. Alquilar piso/dejar de alquilarlo ");
				System.out.println(" 6. Dado un código de piso, mostrar el nombre del empleado que lo lleva ");
				System.out.println(" 7. Mostrar el sueldo mensual de un empleado dado su nif ");
				System.out.println(" 8. Mostrar el empleado que más pisos alquilados tenga ");
				System.out.println(" 9. Generar xml de pisos");
				System.out.println(" 10. Volcar en la base de datos ");
				System.out.println(" 11. Generar un informe de los pisos ");
				System.out.println(" 12. Mostrar pisos");
				System.out.println("0. Salir");

				System.out.print("\n-> ");

				try {
					op = Teclado.leerEntero();
				} catch (Exception e) {
					op = -1;
				}

				switch (op) {

				case 1:
					System.out.print("Nif: ");
					int nif = Teclado.leerEntero();
					System.out.print("Nombre: ");
					String nombre = Teclado.leerCadena();
					System.out.print("Sueldo base: ");
					double sueldo = Teclado.leerDecimal();

					if (empleado.addEmpleado(new Empleado(nif, nombre, sueldo))) {
						System.out.println("Se añadio correctamente a " + nombre);
					} else
						System.err.println("Hubo un error, no se pudo añadir al alumno");
					break;
				case 2:
					System.out.print("Direccion: ");
					String direccion = Teclado.leerCadena();
					System.out.print("Mensualidad: ");
					double mensualidad = Teclado.leerDecimal();
					System.out.print("Nif Propietario: ");
					nif = Teclado.leerEntero();

					if (piso.addPiso(new Piso(direccion, mensualidad, nif))) {
						System.out.println("Se añadio correctamente el piso");
					} else
						System.err.println("Hubo un error, no se pudo añadir el piso");
					break;
				case 3:
					System.out.print("Codigo: ");
					int codigo = Teclado.leerEntero();
					System.out.print("Nueva mensualidad: ");
					mensualidad = Teclado.leerDecimal();

					if (piso.modMensualidad(codigo, mensualidad))
						System.out.println("Se ha modificado la mensualidad del piso");
					else
						System.err.println("Hubo un error, no se pudo modificar la mensualidad");

					break;
				case 4:
					System.out.print("Codigo: ");
					codigo = Teclado.leerEntero();
					System.out.print("Nif nuevo propietario: ");
					nif = Teclado.leerEntero();

					if (piso.cambiarEmpleadoPiso(codigo, nif))
						System.out.println("Se ha modificado el empleado del piso");
					else
						System.err.println("Hubo un error, no se pudo cambiar de empleado");
					break;

				case 5:
					System.out.print("Codigo: ");
					codigo = Teclado.leerEntero();

					if (piso.alquilar_noAlquilar(true, codigo))
						System.out.println("Se ha alquilado el piso");
					else
						System.err.println("Hubo un error, no se pudo cambiar el estado del piso");

					break;
				case 6:
					System.out.print("Codigo: ");
					codigo = Teclado.leerEntero();

					System.out.println("El propietario de este piso es " + Func.mostrarEmpleadoPiso(codigo));

					break;
				case 7:
					System.out.print("Nif: ");
					nif = Teclado.leerEntero();

					System.out.println(
							"El sueldo mensual de " + nif + " es de " + empleado.sueldoEmpleado(nif) + " euros");
					break;
				case 8:
					System.out.println(
							"El empleado que tiene mas pisos alquilados es " + empleado.getMejorEmpleado().getNombre());
					break;
				case 9:
					Func.volcarDatos_a_XML();
					break;
				case 10:
					Func.volcarDatosXML_JAXB_a_BBDD();
					break;
				case 11:

					break;
				case 12:
					Func.mostrarPisos();
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.err.println("Opcion no valida");
					break;
				}
			} catch (Exception e) {
				System.err.println("Hubo un error: ");
				e.printStackTrace();
			}

		} while (op != 0);

	}

}
