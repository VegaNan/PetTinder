package objects;

import java.util.HashMap;

public class User {

	private String username;
	private String password;
	private String location;
	private HashMap<Integer, String> matchedMap;
	private HashMap<Integer, String> maybeMap;
	private HashMap<Integer, String> noMap;
	
	User(){
		
	}
	
	User(String username, String password, String location){
		setUsername(username);
		setPassword(password);
		setLocation(location);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public void addAnimalToMatched(int id, String animalInfo) {
		
	}
	
	public void addAnimalToMaybe(int id, String animalInfo) {
		
	}
	
	public void addAnimalToNo(int id, String animalInfo) {
		
	}
	
	
	
	
	
	
}
