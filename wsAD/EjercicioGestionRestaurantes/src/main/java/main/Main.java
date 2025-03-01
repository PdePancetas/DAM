package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import logic.Func;
import model.Menu;
import model.Plato;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		MongoClient client = MongoClients.create("mongodb://localhost:27017/");

		MongoDatabase db = Func.ConexionMongoDB(client, "GestionMenusRestaurantes");

		MongoCollection<Document> menus = Func.getMongoCollection(db, "Menus");

		int op = -1;
		while (op != 0) {

			System.out.print("""

					Escoge una opcion:

						1. Insertar un menú nuevo
						2. Obtener nombres de platos por menú
						3. Mostrar menús que contienen platos
						4. Mostrar precio de un menú
						5. Mostrar los nombres de restaurantes donde se sirve el menú más caro
						6. Añadir un plato a un menú
						0. Salir

						->	""");
			try {
				op = Teclado.leerEntero();
			} catch (Exception e) {
				System.out.println("Opción no válida");
				continue;
			}

			switch (op) {
			case 1:
				String cod = "";
				try {
					System.out.print("Código del menú: ");
					cod = Teclado.leerCadena();
					System.out.print("Nombre del menú: ");
					String nombre = Teclado.leerCadena();
					System.out.println("Nombre de los restaurantes en los que se utilizará (separados por ',') ");
					String nomRestaurantes = Teclado.leerCadena();
					List<String> restaurantes = List.of(nomRestaurantes.split(",")).stream().map(x -> x.trim())
							.toList();
					System.out.print("Cuantos platos tendrá? ");
					int numPlatos = Teclado.leerEntero();
					Set<Plato> platos = new HashSet<>();
					if (numPlatos > 0) {
						platos = new HashSet<>();
						for (int i = 0; i < numPlatos; i++) {
							System.out.println("Plato " + (i + 1));
							System.out.print("   Nombre: ");
							String nomPlato = Teclado.leerCadena();
							System.out.print("   Precio: ");
							double precioPlato = Teclado.leerDecimal();
							platos.add(new Plato(nomPlato, precioPlato));
						}
					}
					Func.insertarMenu(new Menu(cod, nombre, restaurantes, platos), menus);
					System.out.println("Menu insertado correctamente");
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					continue;
				}

				break;

			case 2:
				cod = "";
				try {
					System.out.print("Código del menú: ");
					cod = Teclado.leerCadena();
					Menu m = Func.obtenerPlatosMenu(cod, menus);
					if (m.getPlatos().isEmpty())
						System.out.println("En el menú con codigo" + cod + " no hay platos");
					else
						m.getPlatos().forEach(System.out::println);

				} catch (Exception e) {
					System.out.println("No se encontró el menú con código " + cod);
					continue;
				}
				break;

			case 3:
				try {
					Func.obtenerMenusConPlatos(menus).stream().forEach(m -> {
						System.out.println("Nombre: " + m.getNombre());
						System.out.println(" Restaurantes que lo usan: "
								+ m.getRestaurantes().stream().collect(Collectors.joining(", ", "", ".")));
						System.out.println(" Platos:");
						m.getPlatos().forEach(p -> {
							System.out.println("  " + p.getNombre() + ": " + p.getPrecio());
						});
						System.out.println();
					});
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					continue;
				}

				break;

			case 4:
				cod = "";
				try {
					System.out.print("Código del menú: ");
					cod = Teclado.leerCadena();
					System.out.println("Precio del menú " + cod + ": " + Func.obtenerPrecioMenu(cod, menus));
				} catch (NullPointerException e) {
					System.out.println("En el menú con codigo" + cod + " no hay platos, " + "por lo que no hay precio");
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					continue;
				}

				break;

			case 5:
				try {

					Menu m = Func.menuMasCaro(menus);
					System.out.println("Restaurantes con el menú más caro: ");
					System.out.println(m.getRestaurantes().stream().collect(Collectors.joining(", ", "", ".")));
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					continue;
				}

				break;

			case 6:
				try {
					System.out.print("Nombre del plato: ");
					String nombre = Teclado.leerCadena();
					System.out.print("Precio del plato: ");
					double precio = Teclado.leerDecimal();
					System.out.print("Codigo del menu: ");
					cod = Teclado.leerCadena();
					Func.insertarPlato(cod, new Plato(nombre, precio), menus);
					System.out.println("Plato añadido correctamente");
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					continue;
				}

				break;

			case 0:
				break;

			default:
				System.out.println("Opción no válida");
			}

		}

		client.close();
		System.out.println("Programa terminado");

	}

}
