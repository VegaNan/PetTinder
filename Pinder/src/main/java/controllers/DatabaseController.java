package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import objects.Animal;
import objects.Organization;
import objects.User;

public class DatabaseController {
	private static MongoDatabase database;
	private static MongoCollection<Document> animalCollection;
	private static MongoCollection<Document> organizationCollection;
	private static MongoCollection<Document> userCollection;
	private String databaseName;
	private MongoClient mongoClient;
	
	public DatabaseController() {
		databaseName = "Pinder";
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase(databaseName);
		animalCollection = database.getCollection("animals");
		organizationCollection = database.getCollection("organizations");
		userCollection = database.getCollection("users");
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
	
	public Animal getAnimalById(int id) {
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		String dbString = cursor.first().toString();
		
		Animal animal = createAnimalObjects(dbString)[0];
		return animal;
	}
	
	public Organization getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = organizationCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		FindIterable<Document> cursor = collectionResults.find(fields);
		String dbString = cursor.first().toString();
		Organization org = createOrganizationObjects(dbString)[0];
		return org;
	}
	
	public Animal[] getAnimalsByOrganization(String id) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("organization_id", id);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animals+=doc.toJson();
			animals+=doc.toJson() + "~";

		}
		Animal[] animalArr = createAnimalObjects(animals);
		return animalArr;
	}
	
	public Animal[] getAnimalsBy(String key, String value) {
		String animals = "";
		MongoCollection<Document> collectionResults = animalCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put(key, value);
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {

			System.out.println(doc.toString());
			animals+=doc.toJson() + "~";

		}
		Animal[] animalArr = createAnimalObjects(animals);
		return animalArr;
	}
	
	public Animal[] getAnimalsByOrganizationValue(String orgKey, String orgValue) {

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
				animals+=doc2.toJson();
			}
		}
		
		Animal[] animalsObj =  createAnimalObjects(animals);
		return animalsObj;
	}
	
	public Animal[] createAnimalObjects(String dbString) {
		String[] dbAnimals = dbString.split("~");
		System.out.println(dbString);
		
		int animalNum = dbAnimals.length;
		Animal[] animals = new Animal[animalNum];
		
		for(int i = 0; i < animalNum; i++) {
			System.out.println(dbAnimals[i]);
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

			
			String photosUrl[] = new String[jo.getJSONArray("photos").length()];
			if(!jo.getJSONArray("photos").isEmpty()) {	
				for(int j = 0; j < jo.getJSONArray("photos").length(); j++) {
					photosUrl[j] = jo.getJSONArray("photos").getJSONObject(j).getString("full");
				}
			}else {
				photosUrl = new String[1];
				photosUrl[0] = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png";
			}
			ArrayList<String> tags = null;
	
			animals[i] = new Animal(id, organizationId, type, breed, size, gender, age,
					status, name, organization, goodWithChildren, goodWithDogs,
					goodWithCats, location, distance, spayedNeutered, houseTrained,
					declawed, photosUrl, tags);
			}
		return animals;
	}

	public Organization[] createOrganizationObjects(String dbString){
		String[] dbOrgs = dbString.split("~");
		System.out.println(dbString);
		
		int dbLength = dbOrgs.length;
		Organization[] orgArr = new Organization[dbLength];
		
		
		for(int i = 0; i < dbLength; i++) {
			System.out.println(dbOrgs[i]);
			JSONObject jo = new JSONObject(dbOrgs[i]);

			String organizationId = jo.getString("id"); 
			String name = jo.getString("name");
			String location = jo.getString("address1");
			String state = jo.getString("state");
			String zipcode = jo.getString("postcode");
			String country = jo.getString("country");
			String contactEmail = jo.getString("email");
			String contactPhone = jo.getString("phone");
			String websiteUrl = jo.getString("website");
	
			orgArr[i] = new Organization(organizationId, name, location, state, country, contactEmail, contactPhone, zipcode, websiteUrl);
		}
		
		return orgArr;
	}
	
	public User userLogin(String username, String password) {
		User user = new User();
		
		
		return user;
	}
	
	public void storeUser(User user) {
		Document document = new Document("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("password", user.getPassword())
				.append("location", user.getLocation())
				.append("email", user.getEmail())
				.append("pref", user.getAnimalPref());
		
		List<Document> matchedArr = new ArrayList<>();
		List<Document> maybeArr = new ArrayList<>();
		List<Document> noArr = new ArrayList<>();
		
		for(Map.Entry<Integer, String> matchedMap : user.getMatchedMap().entrySet()) {
			Document documentMatched = new Document();
			documentMatched.append("\"" + matchedMap.getKey() + "\"", (Object)matchedMap.getValue());
			matchedArr.add(documentMatched);
		}
		
		document.append("matchedMap", matchedArr);
		
		for(Map.Entry<Integer, String> maybeMap : user.getMaybeMap().entrySet()) {
			Document documentMaybe = new Document();
			documentMaybe.append("\"" + maybeMap.getKey() + "\"", (Object)maybeMap.getValue());
			maybeArr.add(documentMaybe);
		}

		document.append("maybeMap", maybeArr);
		
		for(Map.Entry<Integer, String> noMap : user.getNoMap().entrySet()) {
			Document documentNo = new Document();
			documentNo.append("\"" + noMap.getKey() + "\"", (Object)noMap.getValue());
			noArr.add(documentNo);
		}
		
		document.append("noMap", noArr);
		
		userCollection.insertOne(document);
		System.out.println("Document inserted successfully"); 
	}
	
	public void updateUser(User user) {
		List<Document> matchedArr = new ArrayList<>();
		List<Document> maybeArr = new ArrayList<>();
		List<Document> noArr = new ArrayList<>();
		
		for(Map.Entry<Integer, String> matchedMap : user.getMatchedMap().entrySet()) {
			Document documentMatched = new Document();
			documentMatched.append("\"" + matchedMap.getKey() + "\"", (Object)matchedMap.getValue());
			matchedArr.add(documentMatched);
		}
		
		for(Map.Entry<Integer, String> maybeMap : user.getMaybeMap().entrySet()) {
			Document documentMaybe = new Document();
			documentMaybe.append("\"" + maybeMap.getKey() + "\"", (Object)maybeMap.getValue());
			maybeArr.add(documentMaybe);
		}
		
		for(Map.Entry<Integer, String> noMap : user.getNoMap().entrySet()) {
			Document documentNo = new Document();
			documentNo.append("\"" + noMap.getKey() + "\"", (Object)noMap.getValue());
			noArr.add(documentNo);
		}
//		System.out.println(matchedArr);		
		
		BasicDBObject matchedDocument = new BasicDBObject();
		matchedDocument.append("$set", new BasicDBObject().append("matchedMap", matchedArr));		
		
		BasicDBObject searchQuery = new BasicDBObject().append("matchedArr", matchedArr);
		
		userCollection.updateOne(searchQuery, matchedDocument);
		
		BasicDBObject maybeDocument = new BasicDBObject();
		maybeDocument.append("$set", new BasicDBObject().append("maybeMap", maybeArr));
		
		searchQuery = new BasicDBObject().append("maybeArr", maybeArr);
		
		userCollection.updateOne(searchQuery, maybeDocument);
		
		BasicDBObject noDocument = new BasicDBObject();
		noDocument.append("$set", new BasicDBObject().append("noMap", noArr));
		
		searchQuery = new BasicDBObject().append("noArr", noArr);
		
		userCollection.updateOne(searchQuery, noDocument);
		
	}
	
	public User getUser(String email, String password) {
		User user = null;
		String correctPassword = null;
		
		MongoCollection<Document> userResults = userCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("email", email);
		Iterable<Document> cursor = userResults.find(fields);
		
		for(Document doc : cursor) {
			correctPassword = (String) doc.get("password");
			if(password.equals(correctPassword)) {
				user = new User(doc.getString("firstName"), doc.getString("lastName"), doc.getString("password"), doc.getString("email"), doc.getString("location"));
//				user
			}
		}
		
		
		return user;
	}
	
}