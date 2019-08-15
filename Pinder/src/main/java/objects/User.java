
package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;

public class User {

	private String firstName;
	private String lastName;
	private String password;
	private String location;
	private String email;
	private String pref;
	private ArrayList<Animal> matchedArr = new ArrayList<Animal>();
	private HashMap<Integer, String> maybeMap = new HashMap<>();
	private HashMap<Integer, String> noMap = new HashMap<>();
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String password, String email, String location){
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		setEmail(email);
		setLocation(location);
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
	
	public void addAnimalToMatched(Animal animal) {
		matchedArr.add(animal);
	}
	
	public ArrayList<Animal> getMatched(){
		return matchedArr;
	}
	
	public void setMatchedMap(List<Document> matchedArr) {
		for(int i = 0; i < matchedArr.size(); i++) {
			
		}
	}
	
	public void addAnimalToMaybe(int id, String animalName) {
		maybeMap.put(id, animalName);
	}
	
	public HashMap<Integer, String> getMaybeMap(){
		return maybeMap;
	}
	
	public void setMaybeMap(List<Document> maybeArr) {
		for(int i = 0; i < maybeArr.size(); i++) {
			
		}
	}
	
	public void addAnimalToNo(int id, String animalName) {
		noMap.put(id, animalName);
	}
	
	public HashMap<Integer, String> getNoMap(){
		return noMap;
	}
	
	public void setNoMap(List<Document> noArr) {
		for(int i = 0; i< noArr.size(); i++) {
			
		}
	}
	
}