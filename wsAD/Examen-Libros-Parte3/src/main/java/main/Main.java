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

public class Main {

	public static void main(String[] args) {

		MongoClient client = MongoClients.create("mongodb://localhost:27017/");

		MongoDatabase db = Func.ConexionMongoDB(client, "Libros");

		MongoCollection<Document> libros = Func.getMongoCollection(db, "libros");
		
		
		
		
		Func.eliminaLibros(20, libros);
//		Func.listaLibros("Libertad", libros);
	
	}
		
		

}
