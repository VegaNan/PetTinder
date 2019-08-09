
package objects;

import java.util.HashMap;

public class User {

	private String firstName;
	private String lastName;
	private String password;
	private String location;
	private String email;
	private String pref;
	private HashMap<Integer, String> matchedMap = new HashMap<Integer, String>();
	private HashMap<Integer, String> maybeMap = new HashMap<Integer, String>();
	private HashMap<Integer, String> noMap = new HashMap<Integer, String>();
	
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
	
	public void addAnimalToMatched(int id, String animalName) {
		matchedMap.put(id, animalName);
	}
	
	public HashMap<Integer, String> getMatchedMap() {
		return matchedMap;
	}
	
	public void addAnimalToMaybe(int id, String animalName) {
		maybeMap.put(id, animalName);
	}
	
	public HashMap<Integer, String> getMaybeMap(){
		return maybeMap;
	}
	
	public void addAnimalToNo(int id, String animalName) {
		noMap.put(id, animalName);
	}
	
	public HashMap<Integer, String> getNoMap(){
		return noMap;
	}
	
}