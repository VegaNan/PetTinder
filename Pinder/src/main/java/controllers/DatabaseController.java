package controllers;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
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
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		System.out.println(cursor.first());
		return cursor.first().toString();
	}
	
	public String getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = organizationCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		System.out.println(cursor.first());
		return  cursor.first().toString();
	}
	
	public String getAnimalsByOrganization(String id) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson();
		}
		return animals;
	}
	
	public String searchAnimalsBy(String key, String value) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put(key, value);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson();
		}
		return animals;
	}
	
	public String getAnimalsByOrganizationValue(String key, String value) {
		String animals = "";
		
		BasicDBObject fields = new BasicDBObject();
		fields.put(key, value);
		MongoCollection<Document> organizationResults = organizationCollection;
		Iterable<Document> cursor = organizationResults.find(fields);
		System.out.println(key + " " + value );
		for(Document doc: cursor) {
			String id = doc.getString("id");
			System.out.println(id);
			MongoCollection<Document> animalResults = animalCollection;
			BasicDBObject fields2 = new BasicDBObject();
			fields2.put("organization_id", id);
			Iterable<Document> cursor2 = animalResults.find(fields2);
			for(Document doc2: cursor2) {
				animals+=doc2.toJson();
			}
		}
		return animals;
	}
	
}