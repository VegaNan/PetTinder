package controllers;

<<<<<<< Upstream, based on branch 'Nancy' of https://github.com/VegaNan/PetTinder.git
=======
import org.bson.Document;
>>>>>>> 2dd46f1 new stuff
import org.json.JSONArray;
import org.json.JSONObject;
<<<<<<< Upstream, based on branch 'Nancy' of https://github.com/VegaNan/PetTinder.git
import org.bson.Document;
=======
>>>>>>> 2dd46f1 new stuff

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
<<<<<<< Upstream, based on branch 'Nancy' of https://github.com/VegaNan/PetTinder.git
//import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
=======
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
>>>>>>> 2dd46f1 new stuff

public class DatabaseController {
	private static MongoDatabase database;
//	private static DB database;
	private static DBCollection collection;
	private String collectionName;
	private String databaseName;
	private MongoClient mongoClient;
//	private MongoCredential mongoCredential;
	
	public DatabaseController() {
			System.out.println("If it breaks here, it's because you need to set up mongodb\n in the app, it's on the default host (localhost:27017)\n\t Create a database named Pinder and a collection named APIInfo \n I'm still working on being able to set that up in the backgroun\n  as well as comparing past entried by id or clearing out the database idkd");
			databaseName = "Pinder";
			collectionName = "APIInfo";
//			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			mongoClient = new MongoClient("localhost", 27017);
//			mongoCredential = MongoCredential.createCredential("test", databaseName, "password".toCharArray());
			database = mongoClient.getDatabase(databaseName);
			
//			database = mongoClient.getDB(databaseName);
			System.out.println("---if you got here, it got database correctly :) ---");
//			collection = database.getCollection(collectionName);		
	
	}
	

	public void insertAnimalRecords(String recordInfo) {
		System.out.println("inserting record page");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("animals");
		for(int i = 0; i < jsonArr.length(); i++) {
			DBObject dbObject = (DBObject) BasicDBObject.parse(jsonArr.getJSONObject(i).toString(1));
			collection.insert(dbObject);
		}
		
	}
	
	public String getAnimalById(int id) {
		MongoCollection<Document> collectionResults = database.getCollection(collectionName);
		
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		
		FindIterable<Document> cursor = collectionResults.find(fields);
		System.out.println(cursor.first());
		
		return cursor.first().toString();
	}
<<<<<<< Upstream, based on branch 'Nancy' of https://github.com/VegaNan/PetTinder.git
	
=======
>>>>>>> 2dd46f1 new stuff
	
	
}