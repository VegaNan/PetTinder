package objects;

import java.util.ArrayList;
import org.json.JSONObject;

public class User {

	private String firstName;
	private String lastName;
	private String password;
	private String location;
	private String email;
	private String pref;
	private ArrayList<String> matchedArr = new ArrayList<>();
	private ArrayList<String> maybeMap = new ArrayList<>();
	private ArrayList<String> noMap = new ArrayList<>();
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String password, String email, String location, String pref){
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		setEmail(email);
		setLocation(location);
		setAnimalPref(pref);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAnimalPref() {
		return pref;
	}
	
	public void setAnimalPref(String pref) {
		this.pref = pref;
	}
	
	public void addAnimalToMatched(String id) {
		matchedArr.add(id);
	}
	
	public ArrayList<String> getMatched(){
		return matchedArr;
	}
	
	public void setMatched(ArrayList<String> matchedArr) {
		this.matchedArr = matchedArr;
	}
	
	public void addAnimalToMaybe(String id) {
		maybeMap.add(id);
	}
	
	public ArrayList<String> getMaybe(){
		return maybeMap;
	}
	
	public void setMaybe(ArrayList<String> maybeArr) {
		this.maybeMap = maybeArr;
	}
	
	public void addAnimalToNo(String id) {
		noMap.add(id);
	}
	
	public ArrayList<String> getNo(){
		return noMap;
	}
	
	public void setNo(ArrayList<String> noArr) {
		this.noMap = noArr;
	}
	
	public Animal[] createAnimalObjects(String dbString) {
		String[] dbAnimals = dbString.split("~");
		System.out.println(dbString);
		
		int animalNum = dbAnimals.length;
		Animal[] animals = new Animal[animalNum];
		
		for(int i = 0; i < animalNum; i++) {
			System.out.println(dbAnimals[i]);
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
	
}