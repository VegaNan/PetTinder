package controllers;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseController {
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;
	private String collectionName;
	private String databaseName;
	private MongoClient mongoClient;
	
	public DatabaseController() {
		databaseName = "Pinder";
		collectionName = "APIInfo";
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase(databaseName);
		collection = database.getCollection(collectionName);
		System.out.println("---if you got here, it got database correctly :) ---");
	}

	public void insertAnimalRecords(String recordInfo) {
		System.out.println("inserting record page");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("animals");
		for(int i = 0; i < jsonArr.length(); i++) {
			Document dbObject = Document.parse(jsonArr.getJSONObject(i).toString(1));
			if(dbObject != null) {
				collection.insertOne(dbObject);
			}
		}
	}
	
	public void insertOrganizationRecords(String recordInfo) {
		System.out.println("inserting record page");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("organizations");
		for(int i = 0; i < jsonArr.length(); i++) {
			Document dbObject = Document.parse(jsonArr.getJSONObject(i).toString(1));
			if(dbObject != null) {
				collection.insertOne(dbObject);
			}
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
	
	public String getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = database.getCollection(collectionName);
		
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		
		FindIterable<Document> cursor = collectionResults.find(fields);
		System.out.println(cursor.first());
		
		return  cursor.first().toString();
	}
	
}