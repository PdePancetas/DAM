package main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.DBRef;

public class Demo {

	public static void main(String[] args) throws Exception {

		// URL de conexión (modifícala según tu configuración)
		String uri = "mongodb://localhost:27017"; // Para una base de datos local
		// String uri = "mongodb+srv://usuario:contraseña@cluster.mongodb.net"; // Para
		// MongoDB Atlas

		// Crear un cliente de conexión
		MongoClient mongoClient = MongoClients.create(uri);
		// Seleccionar la base de datos
		MongoDatabase database = mongoClient.getDatabase("biblioteca");

		// Seleccionar una colección
		MongoCollection<Document> collection = database.getCollection("libros");

		for (Document doc : collection.find()) {
			System.out.println(doc.toJson());
		}
/*
//		/* INSERTAR NUEVO ALUMNO
		Document d = new Document();
		d.append("dni", (int)collection.countDocuments()+1);
		d.append("nombre", "Adrian");
		d.append("clase", "2DAM");

		collection.insertOne(d);
		System.out.println("Documento insertado correctamente");
//		*/
		collection.find().forEach( dc -> {
			
			System.out.println("Titulo");
			
		});
		
		mongoClient.close();

	}
}
