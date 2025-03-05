package logic;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.Libro;

public class Func {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static MongoDatabase ConexionMongoDB(MongoClient client, String database) {
		return client.getDatabase(database);
	}

	public static MongoCollection<Document> getMongoCollection(MongoDatabase db, String collectionName) {
		return db.getCollection(collectionName);
	}

	public static void eliminaLibros(double precio, MongoCollection<Document> libros) {
		
		libros.deleteMany(Filters.gt("precio", precio));
		
	}
	
	public static void listaLibros(String nombre, MongoCollection<Document> libros) {
		
		List<Document> librosDoc = libros.find(Filters.eq("nombre", nombre)).into(new ArrayList<Document>());
		
		List<Libro> ls = librosDoc.stream().map(l -> mapper.convertValue(l, Libro.class)).toList();
		
		
		ls.forEach(System.out::println);
	}

}
