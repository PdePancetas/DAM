package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import logic.Func;
import model.Contacto;

public class Main {

	public static void main(String[] args) {

		MongoClient client = MongoClients.create("mongodb://localhost:27017/");

		MongoDatabase db = Func.ConexionMongoDB(client, "Agenda");
		MongoCollection<Document> agenda = Func.getMongoCollection(db, "agenda");

		/// Funcionalidad 1
		/// void insertarContacto(String nombre, String email, List<String> teléfonos) 1 pto
		/*
		 * Func.insertarContacto(new Contacto("Arberto", "ticus@gmail.com", new
		 * ArrayList<String>(Arrays.asList("2222","2345"))), agenda);
		 */

		/// Funcionalidad 2
		/// List<String> getTelefonos(String nombre) 1 pto
		List<String> telefonos = null;
		try {
			telefonos = Func.getTelefonos("Ruben", agenda);

			if (telefonos.isEmpty())
				System.out.println("El contacto no tiene telefonos");
			else
				System.out
						.println(telefonos.stream().collect(Collectors.joining(", ", "Telefonos del contacto: ", ".")));

		} catch (NullPointerException e) {
			System.out.println("El contacto especificado no existe");
		}
	}

}
