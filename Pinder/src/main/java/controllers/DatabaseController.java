package controllers;

import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class DatabaseController {
	private static DB database;
	private static DBCollection collection;
	
	public DatabaseController() {
		try {
			System.out.println("If it breaks here, it's because you need to set up mongodb\n in the app, it's on the default host (localhost:27017)\n\t Create a database named Pinder and a collection named APIInfo \n I'm still working on being able to set that up in the backgroun\n  as well as comparing past entried by id or clearing out the database idkd");
			String databaseName = "Pinder";
			String collectionName = "APIInfo";
			MongoClient mongoClient;
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			
			database = mongoClient.getDB(databaseName);
			System.out.println("---if you got here, it got database correctly :) ---");
			collection = database.getCollection(collectionName);		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}
	

	public void insertAnimalRecords(String recordInfo) {
		System.out.println("inserting record page");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("animals");
		for(int i = 0; i < jsonArr.length(); i++) {
			DBObject dbObject = (DBObject) JSON.parse(jsonArr.getJSONObject(i).toString(1));
			collection.insert(dbObject);
		}
		
	}
	
	
}
