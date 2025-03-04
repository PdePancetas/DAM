package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import model.Menu;
import model.Plato;

public class Func {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static MongoDatabase ConexionMongoDB(MongoClient client, String database) {
		return client.getDatabase(database);
	}

	public static MongoCollection<Document> getMongoCollection(MongoDatabase db, String collectionName) {
		return db.getCollection(collectionName);
	}

	public static void insertarMenu(Menu menu, MongoCollection<Document> menus) {
		menus.insertOne(mapper.convertValue(menu, Document.class));
	}

	public static Menu obtenerPlatosMenu(String cod, MongoCollection<Document> menus) {
		Document menu = menus.find(Filters.eq("codigo", cod)).first();
		

		Menu m = mapper.convertValue(menu, Menu.class);
		return m;
	}

	public static List<Menu> obtenerMenusConPlatos(MongoCollection<Document> menus) {
		List<Document> menusConPlatos = menus.find(Filters.exists("platos", true))
				.into(new ArrayList<Document>());

		return menusConPlatos.stream().map(m -> mapper.convertValue(m, Menu.class))
				.filter(m -> !m.getPlatos().isEmpty())
				.toList();
	}

	public static double obtenerPrecioMenu(String cod, MongoCollection<Document> menus) {
		Document menu = menus.find(Filters.eq("codigo", cod)).first();

		Menu m = mapper.convertValue(menu, Menu.class);

		if (m.getPlatos().isEmpty()) {
			throw new NullPointerException();
		}

		return m.getPlatos().stream().mapToDouble(p -> p.getPrecio()).sum();
	}

	public static Menu menuMasCaro(MongoCollection<Document> menus) {
		List<Document> menusConPlatosDoc = menus.find(Filters.exists("platos", true)).into(new ArrayList<Document>());

		List<Menu> menusConPlatos = menusConPlatosDoc.stream().map(m -> mapper.convertValue(m, Menu.class)).toList();

		return menusConPlatos.stream()
				.max(Comparator.comparingDouble(m -> m.getPlatos().stream().mapToDouble(p -> p.getPrecio()).sum()))
				.get();
	}

	public static void insertarPlato(String cod, Plato plato, MongoCollection<Document> menus) {
		Document menu = menus.find(Filters.eq("codigo", cod)).first();

		if (menu != null) {
			menus.updateOne(Filters.eq("codigo", cod),
					Updates.addToSet("platos", mapper.convertValue(plato, Document.class)));
		} else {
			System.out.println("El menú con código " + cod + " no existe.");
		}
	}

}
