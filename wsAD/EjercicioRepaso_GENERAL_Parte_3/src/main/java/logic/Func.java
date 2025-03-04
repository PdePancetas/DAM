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

import model.Contacto;

public class Func {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static MongoDatabase ConexionMongoDB(MongoClient client, String database) {
		return client.getDatabase(database);
	}

	public static MongoCollection<Document> getMongoCollection(MongoDatabase db, String collectionName) {
		return db.getCollection(collectionName);
	}

	public static void insertarContacto(Contacto contacto, MongoCollection<Document> agenda) {
		agenda.insertOne(mapper.convertValue(contacto, Document.class));
	}

	public static List<String> getTelefonos(String nombre, MongoCollection<Document> agenda) {

		Document contactoDoc = agenda.find(Filters.eq("nombre", nombre)).first();
		
		Contacto contacto = mapper.convertValue(contactoDoc, Contacto.class);
		
		return contacto.getTlfos();
		
	}
	
	
	
	

}
