package logic;

import net.sf.jasperreports.engine.JRException;
import properties.Properties;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int op = -1;
		do {

			try {
				System.out.println("\n__________________\nEscoge una opcion: ");
				System.out.println("1. Mostrar info de juego intercambiado: ");
				System.out.println("2. Eliminar usuario y referencias: ");
				System.out.println("3. Generar informe de usuario: ");
				System.out.print("-> ");

				try {
					op = Teclado.leerEntero();
				} catch (Exception e) {
					op = -1;
				}

				switch (op) {

				case 1:
					System.out.print("Id de juego " + Func.getGameIds().toString() + ": ");
					int id = Teclado.leerEntero();
					Func.intercambiado(id);
					break;
				case 2:
					System.out.print("Id de usuario " + Func.getUserIds().toString() + ": ");
					id = Teclado.leerEntero();
					String nombre = Func.getUserName(id, Func.leerFicheroJAXB(Func.getFichero())).get();
					Func.eliminaUsuario(id);
					System.out.println("\n Se ha eliminado con exito al usuario " + nombre);
					break;
				case 3:
					System.out.print("Id de usuario " + Func.getUserIds().toString() + ": ");
					id = Teclado.leerEntero();
					System.out.println("Generando informe...");
					try {
						Func.generaInforme(id);
						System.out.println("Informe generado en " + Properties.getConfig().getProperty("fichero"));
					} catch (JRException e) {
						e.printStackTrace();
					}

					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
			} catch (Exception e) {
				System.err.println("Opcion no valida");
			}

		} while (op != 0);

	}

}
