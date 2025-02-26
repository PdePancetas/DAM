package main;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Autor;
import model.Libro;

public class Main {

	public static void main(String[] args) {
//		List<Autor> autores = new ArrayList<>();
//		
//		autores.add(new Autor("Anita",100));
//		autores.add(new Autor("Valu", 500));
//		insertarLibro("La batuta", autores, 200);
		
//		findByTitulo("La mosca").forEach(System.out::println);
		deleteByGreaterPrecio(400);
	}
	

	
	private static void insertarLibro(String titulo, List<Autor> autores, int precio) {
		
		Document libro = new Document();
		List<Document> autoresDoc = new ArrayList<>();
		libro.append("titulo", titulo).append("precio", precio);
		
		autores.forEach(autor ->
		autoresDoc.add(new Document()
				.append("nombre", autor.getNombre())
				.append("sueldo", autor.getSueldo()))
		);
		libro.append("autores", autoresDoc);
		
		
		MongoClient client = MongoClients.create("mongodb://localhost:27017/");
		MongoDatabase db = client.getDatabase("biblioteca");
		MongoCollection<Document> libros = db.getCollection("libros");
		
		libros.insertOne(libro);
	}
	
	private static List<Libro> findByTitulo(String titulo) {
		Document criterio = new Document("titulo", "La mosca");
		
		MongoClient client = MongoClients.create("mongodb://localhost:27017/");
		MongoDatabase db = client.getDatabase("biblioteca");
		MongoCollection<Document> librosCol = db.getCollection("libros");
		
		ArrayList<Document> librosConsultados = librosCol.find(criterio).into(new ArrayList<Document>());
		
		List<Libro> libros = new ArrayList<>();
		librosConsultados.forEach(l -> {
			Libro libro = new Libro();
			libro.setTitulo(l.getString("titulo"));
			List<Document> autoresDoc = l.getList("autores", Document.class);
			List<Autor> autores = new ArrayList<>();
			autoresDoc.forEach(autorDoc -> autores.add(new Autor(autorDoc.getString("nombre"), autorDoc.getInteger("sueldo"))));
			libro.setAutores(autores);
			libros.add(libro);
		});
		
		
		return libros;
	}
	
	private static void deleteByGreaterPrecio(int precio) {
		Document criterio = new Document();
		Document condicion = new Document().append("$gt", precio);
		
		criterio.append("precio", condicion);
		
		//{"precio" : {"$gt": ___ } }
		
		MongoClient client = MongoClients.create("mongodb://localhost:27017/");
		MongoDatabase db = client.getDatabase("biblioteca");
		MongoCollection<Document> librosCol = db.getCollection("libros");
		
		librosCol.deleteMany(criterio);		
	}

}
