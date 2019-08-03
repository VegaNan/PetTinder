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
			String databaseName = "Pinder";
			String collectionName = "APIInfo";
			MongoClient mongoClient;
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			
			System.out.println("created client");
			database = mongoClient.getDB(databaseName);
			System.out.println("got database");
			collection = database.getCollection(collectionName);		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}
	

	public void insertAnimalRecords(String recordInfo) {
		System.out.println("inserting record");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("animals");
		for(int i = 0; i < jsonArr.length(); i++) {
			DBObject dbObject = (DBObject) JSON.parse(jsonArr.getJSONObject(i).toString(1));
			System.out.println(dbObject.toString());
			collection.insert(dbObject);
		}
		
	}
	
	
}
