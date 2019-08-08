package controllers;

import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import objects.Animal;

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
			Document dbObject = Document.parse(jsonArr.toString(1));
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
			Document dbObject = Document.parse(jsonArr.toString(1));
			if(dbObject != null) {
				organizationCollection.insertOne(dbObject);
			}
		}
	}
	
	public String getAnimalById(int id) {
		MongoCollection<Document> collectionResults = animalCollection;
		String animals = "";
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson() + "~";
		}
		return animals;
	}
	
	public String getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = organizationCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		return  cursor.first().toString();
	}
	
	public String getAnimalsByOrganization(String id) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson() + "~";
		}
		return animals;
	}
	
	public String getAnimalsBy(String key, String value) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put(key, value);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson() + "~";
		}
		return animals;
	}
	
	public String getAnimalsByOrganizationValue(String orgKey, String orgValue) {

		MongoCollection<Document> organizationResults = organizationCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put(orgKey, orgValue);
		Iterable<Document> cursor = organizationResults.find(fields);
		
		String animals = "";
		
		for(Document doc: cursor) {
			String id = doc.getString("id");
			System.out.println(id);
			MongoCollection<Document> animalResults = animalCollection;
			BasicDBObject fields2 = new BasicDBObject();
			fields2.put("organization_id", id);
			Iterable<Document> cursor2 = animalResults.find(fields2);
			for(Document doc2: cursor2) {
				animals+=doc2.toJson() + "~";
			}
		}
		return animals;
	}
	
	public Animal[] createAnimalObjects(String dbString) {
		String[] dbAnimals = dbString.split("~");
		int animalNum = dbAnimals.length;
		Animal[] animals = new Animal[animalNum];
		
		for(int i = 0; i < animalNum; i++) {
			JSONObject jo = new JSONObject(dbAnimals[i]);

			int id = Integer.parseInt(jo.get("id").toString());
			String organizationId = jo.getString("organization_id");
			String type = jo.getString("type"); 
			String breed = jo.getJSONObject("breeds").getString("primary"); 
			String size =  jo.getString("size"); 
			String gender =  jo.getString("gender");
			String age =  jo.getString("age");
			String status =  jo.getString("status");
			String name =  jo.getString("name");
			String organization = jo.getString("organization_id");
			boolean goodWithChildren = Boolean.parseBoolean( jo.getJSONObject("environment").get("children").toString()); 
			boolean goodWithDogs = Boolean.parseBoolean( jo.getJSONObject("environment").get("dogs").toString());
			boolean goodWithCats = Boolean.parseBoolean( jo.getJSONObject("environment").get("cats").toString()); 
			String location =  jo.getJSONObject("contact").getJSONObject("address").getString("city") + jo.getJSONObject("contact").getJSONObject("address").getString("state") +  jo.getJSONObject("contact").getJSONObject("address").getString("postcode")  ; 
			double distance = Double.parseDouble( jo.get("distance").toString()); 
			boolean spayedNeutered = Boolean.parseBoolean( jo.getJSONObject("attributes").get("spayed_neutered").toString()); 
			boolean houseTrained = Boolean.parseBoolean( jo.getJSONObject("attributes").get("house_trained").toString());
			boolean declawed = Boolean.parseBoolean( jo.getJSONObject("attributes").get("declawed").toString()); 
			String photosUrl = jo.getJSONArray("photos").toString(); 
			ArrayList<String> tags = null;
	
			animals[i] = new Animal(id, organizationId, type, breed, size, gender, age,
					status, name, organization, goodWithChildren, goodWithDogs,
					goodWithCats, location, distance, spayedNeutered, houseTrained,
					declawed, photosUrl, tags);
			}
		
		return animals;
		
	}
	

}