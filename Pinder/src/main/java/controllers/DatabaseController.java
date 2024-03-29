package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String animal = "";
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			animal+=doc.toJson();
			animal+=doc.toJson() + "~";
		}
		Animal animalFin = createAnimalObjects(animal)[0];
		return animalFin;
	}
	
	public Organization getOrganizationById(String id) {
		MongoCollection<Document> collectionResults = organizationCollection;
		BasicDBObject fields = new BasicDBObject();
		fields.put("id", id);
		String dbString = "";
		Iterable<Document> cursor = collectionResults.find(fields);
		for(Document doc: cursor) {
			dbString+=doc.toJson();
			dbString+=doc.toJson() + "~";

		}
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
			animals+= doc.toJson() + "~";
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
		
		int animalNum = dbAnimals.length;
		Animal[] animals = new Animal[animalNum];
		
		for(int i = 0; i < animalNum; i++) {
			JSONObject jo = new JSONObject(dbAnimals[i]);
			String id = jo.get("id").toString();
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
			
			String description = "";
			if(jo.get("description").toString() != null){
				description = jo.get("description").toString();
				if(jo.get("description").toString().contains("null")) {
					description = "No description available \uD83D\uDE2D \n contact the organization for more information";
				}
			}

			
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
					declawed, photosUrl, tags, description, dbString);
			}
		return animals;
	}

	public Organization[] createOrganizationObjects(String dbString){
		String[] dbOrgs = dbString.split("~");
		
		int dbLength = dbOrgs.length;
		Organization[] orgArr = new Organization[dbLength];
		
		System.out.println(dbString);
		for(int i = 0; i < dbLength; i++) {
			JSONObject jo = new JSONObject(dbOrgs[i]);

			String organizationId = jo.getString("id"); 
			String name = jo.getString("name");

			String location = jo.getJSONObject("address").get("address1").toString();
			String state = jo.getJSONObject("address").get("state").toString();
			String zipcode = jo.getJSONObject("address").get("postcode").toString();
			String country = jo.getJSONObject("address").get("country").toString();
			
			String contactEmail = jo.get("email").toString();
			String contactPhone = jo.get("phone").toString();
			String websiteUrl = jo.get("website").toString();
	
			orgArr[i] = new Organization(organizationId, name, location, state, country, contactEmail, contactPhone, zipcode, websiteUrl);
		}
		
		return orgArr;
	}
	
	public User userLogin(String email, String password) {

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
		
		List<String> matchedArr = user.getMatched();
		List<String> newMatchedArr = new ArrayList<>();
		List<String> maybeArr = user.getMaybe();
		List<String> newMaybeArr = new ArrayList<>();
		List<String> noArr = user.getNo();
		List<String> newNoArr = new ArrayList<>();
		
		for(String animal : matchedArr) {
			newMatchedArr.add(animal);
		}
		document.append("matchedArr", newMatchedArr);

		for(String animal : maybeArr) {
			newMaybeArr.add(animal);
		}
		document.append("maybeArr", newMaybeArr);


		for(String animal : noArr) {
			newNoArr.add(animal);
		}
		document.append("noArr", noArr);
		
		userCollection.insertOne(document);
		System.out.println("Document inserted successfully"); 
	}
	
	public void updateUser(User user) {
		BasicDBObject documentDelete = new BasicDBObject();
		documentDelete.put("email", user.getEmail());
		userCollection.deleteOne(documentDelete);
		
		Document document = new Document("firstName", user.getFirstName())
				.append("lastName", user.getLastName())
				.append("password", user.getPassword())
				.append("location", user.getLocation())
				.append("email", user.getEmail())
				.append("pref", user.getAnimalPref());
		
		List<String> matchedArr = user.getMatched();
		List<String> newMatchedArr = new ArrayList<>();
		List<String> maybeArr = user.getMaybe();
		List<String> newMaybeArr = new ArrayList<>();
		List<String> noArr = user.getNo();
		List<String> newNoArr = new ArrayList<>();
		
		
		for(String animal : matchedArr) {
			newMatchedArr.add(animal);
		}
		document.append("matchedArr", newMatchedArr);

		for(String animal : maybeArr) {
			newMaybeArr.add(animal);
		}
		document.append("maybeArr", newMaybeArr);


		for(String animal : noArr) {
			newNoArr.add(animal);
		}
		document.append("noArr", noArr);
		
		userCollection.insertOne(document);
		System.out.println("Document inserted successfully");	

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
				user = new User(doc.getString("firstName"), doc.getString("lastName"), doc.getString("password"), doc.getString("email"), doc.getString("location"), doc.getString("pref"));

				user.setMatched((ArrayList<String>) doc.get("matchedArr"));
				user.setMaybe((ArrayList<String>) doc.get("maybeArr"));
				user.setNo((ArrayList<String>) doc.get("noArr")); 
			}
		}
		
		return user;
	}
	
	public ArrayList<String> createAnimalList(ArrayList<String> array){
		final String idRegEx = "^\\{\"ID\"=([\\d]*)\\}$";
		Pattern p = Pattern.compile(idRegEx);
		System.out.println(array);
		String result = "";
//		for(int i = 0; i< array.size(); i++) {
//			JSONObject jo = new JSONObject(array.get(i).toString());
//			System.out.println(jo.toString());
//		}
		
		System.out.println(result);
		Matcher m = p.matcher(result);
		
		ArrayList<String> animals = new ArrayList<>();
		while(m.find()) {
			
			animals.add(m.group(1));
		}
		
//		System.out.println(dbAnimals[i]);
//		JSONObject jo = new JSONObject(dbAnimals[i]);
//
//		String id = jo.get("id").toString();
//		String organizationId = jo.getString("organization_id");
//		String type = jo.getString("type"); 
//		String breed = jo.getJSONObject("breeds").getString("primary"); 
//		String size =  jo.getString("size"); 
//		String gender =  jo.getString("gender");
//		String age =  jo.getString("age");
//		String status =  jo.getString("status");
//		String name =  jo.getString("name");
//		String organization = jo.getString("organization_id");
//		boolean goodWithChildren = Boolean.parseBoolean( jo.getJSONObject("environment").get("children").toString()); 
//		boolean goodWithDogs = Boolean.parseBoolean( jo.getJSONObject("environment").get("dogs").toString());
//		boolean goodWithCats = Boolean.parseBoolean( jo.getJSONObject("environment").get("cats").toString()); 
//		String location =  jo.getJSONObject("contact").getJSONObject("address").getString("city") + jo.getJSONObject("contact").getJSONObject("address").getString("state") +  jo.getJSONObject("contact").getJSONObject("address").getString("postcode")  ; 
//		double distance = Double.parseDouble( jo.get("distance").toString()); 
//		boolean spayedNeutered = Boolean.parseBoolean( jo.getJSONObject("attributes").get("spayed_neutered").toString()); 
//		boolean houseTrained = Boolean.parseBoolean( jo.getJSONObject("attributes").get("house_trained").toString());
//		boolean declawed = 
		
		
		return animals;
	}
	
}