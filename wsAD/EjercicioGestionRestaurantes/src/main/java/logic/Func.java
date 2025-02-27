package logic;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Func {

	public static MongoDatabase ConexionMongoDB(String connectionString, String database) {
		MongoClient client = MongoClients.create(connectionString);
		return client.getDatabase(database);
	}
	
	public static MongoCollection<Document> getMongoCollection(MongoDatabase db, String collectionName){
		return db.getCollection(collectionName);
	}
	
}
