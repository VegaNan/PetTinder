
package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.w3c.dom.Document;

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
	
	public void addAnimalToMatched(String id) {
		matchedArr.add(id);
	}
	
	public ArrayList<String> getMatched(){
		return matchedArr;
	}
	
	public void setMatched(String id) {
		Animal[] animals = this.createAnimalObjects(dbString);
		
		for(int i = 0; i < animals.length; i++) {
			matchedArr.add(animals[i]);
		}
	}
	
	public void addAnimalToMaybe(String id) {
		maybeMap.add(id);
	}
	
	public ArrayList<String> getMaybe(){
		return maybeMap;
	}
	
	public void setMaybe(String dbString) {
		Animal[] animals = this.createAnimalObjects(dbString);
		
		for(int i = 0; i < animals.length; i++) {
			matchedArr.add(animals[i]);
		}
	}
	
	public void addAnimalToNo(String id) {
		noMap.add(id);
	}
	
	public ArrayList<String> getNo(){
		return noMap;
	}
	
	public void setNo(String dbString) {
		Animal[] animals = this.createAnimalObjects(dbString);
		
		for(int i = 0; i < animals.length; i++) {
			matchedArr.add(animals[i]);
		}
	}
	
}