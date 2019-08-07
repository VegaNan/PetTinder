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
	private static MongoCollection<Document> animalCollection;
	private static MongoCollection<Document> organizationCollection;
	private String databaseName;
	private MongoClient mongoClient;
	
	public DatabaseController() {
		databaseName = "Pinder";
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase(databaseName);
		animalCollection = database.getCollection("animals");
		organizationCollection = database.getCollection("organizations");
		System.out.println("---if you got here, it got database correctly :) ---");
	}

	public void insertAnimalRecords(String recordInfo) {
		System.out.println("inserting record page");
		JSONObject obj = new JSONObject(recordInfo);
		JSONArray jsonArr = obj.getJSONArray("animals");
		for(int i = 0; i < jsonArr.length(); i++) {
			Document dbObject = Document.parse(jsonArr.getJSONObject(i).toString(1));
			if(dbObject != null) {
				animalCollection.insertOne(dbObject);
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
				organizationCollection.insertOne(dbObject);
			}
		}
	}
	
	public String getAnimalById(int id) {
		MongoCollection<Document> collectionResults = database.getCollection("animalCollection");
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		
		return cursor.first().toString();
	}
	
	public String getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = database.getCollection("organizationCollection");
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		
		return  cursor.first().toString();
	}
	
	public String getAnimalsByOrganization(String id) {
		MongoCollection<Document> collectionResults = database.getCollection("animalCollection");
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		System.out.println(cursor.first());
		return cursor.first().toString();
	}
	
}