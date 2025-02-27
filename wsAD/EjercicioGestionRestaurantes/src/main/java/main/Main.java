package main;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import logic.Func;
import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		MongoDatabase db = Func.ConexionMongoDB("mongodb://localhost:27017/", "GestionMenusRestaurantes");

		MongoCollection<Document> menus = Func.getMongoCollection(db, "Menus");

		int op=-1;
		while (op!=0) {

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
	
					break;
	
				case 2:
	
					break;
	
				case 3:
	
					break;
	
				case 4:
	
					break;
	
				case 5:
	
					break;
	
				case 6:
	
					break;
	
				case 0:
					break;
	
				default:
					System.out.println("Opción no válida");
			}

		}
		
		System.out.println("Programa terminado");

	}

}
