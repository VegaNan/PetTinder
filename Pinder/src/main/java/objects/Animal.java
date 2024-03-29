package objects;

import java.util.ArrayList;

public class Animal {
	private String id;
	private String organizationId;
	private String type;
	private String breed;
	private String size;
	private String gender;
	private String age;
	private String status;
	private String name;
	private String organization;
	private boolean goodWithChildren;
	private boolean goodWithDogs;
	private boolean goodWithCats;
	private String location;
	private double distance;
	private boolean spayedNeutered;
	private boolean houseTrained;
	private boolean declawed;
	private String[] photosUrl;
	private String description;
	private ArrayList<String> tags = new ArrayList<>();
	
	private String dbString;
	
	
	public Animal() {
		
	}
	
	public Animal(String id, String organizationId, String type, String breed, String size, String gender, String age,
			String status, String name, String organization, boolean goodWithChildren, boolean goodWithDogs,
			boolean goodWithCats, String location, double distance, boolean spayedNeutered, boolean houseTrained,
			boolean declawed, String[] photosUrl, ArrayList<String> tags, String description, String dbString) {
		super();
		this.id = id;
		this.organizationId = organizationId;
		this.type = type;
		this.breed = breed;
		this.size = size;
		this.gender = gender;
		this.age = age;
		this.status = status;
		this.name = name;
		this.organization = organization;
		this.goodWithChildren = goodWithChildren;
		this.goodWithDogs = goodWithDogs;
		this.goodWithCats = goodWithCats;
		this.location = location;
		this.distance = distance;
		this.spayedNeutered = spayedNeutered;
		this.houseTrained = houseTrained;
		this.declawed = declawed;
		this.photosUrl = photosUrl;
		this.tags = tags;
		setDescription(description);
		this.dbString = dbString;
	}



	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrganizationId() {
		return organizationId;
	}
	
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public boolean isGoodWithChildren() {
		return goodWithChildren;
	}
	
	public void setGoodWithChildren(boolean goodWithChildren) {
		this.goodWithChildren = goodWithChildren;
	}
	
	public boolean isGoodWithDogs() {
		return goodWithDogs;
	}
	
	public void setGoodWithDogs(boolean goodWithDogs) {
		this.goodWithDogs = goodWithDogs;
	}
	
	public boolean isGoodWithCats() {
		return goodWithCats;
	}
	
	public void setGoodWithCats(boolean goodWithCats) {
		this.goodWithCats = goodWithCats;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isSpayedNeutered() {
		return spayedNeutered;
	}

	public void setSpayedNeutered(boolean spayedNeutered) {
		this.spayedNeutered = spayedNeutered;
	}

	public boolean isHouseTrained() {
		return houseTrained;
	}

	public void setHouseTrained(boolean houseTrained) {
		this.houseTrained = houseTrained;
	}

	public boolean isDeclawed() {
		return declawed;
	}

	public void setDeclawed(boolean declawed) {
		this.declawed = declawed;
	}

	public String[] getPhotosUrl() {
		return photosUrl;
	}

	public void setPhotosUrl(String[] photosUrl) {
		this.photosUrl = photosUrl;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", organizationId=" + organizationId + ", type=" + type + ", breed=" + breed
				+ ", size=" + size + ", gender=" + gender + ", age=" + age + ", status=" + status + ", name=" + name
				+ ", organization=" + organization + ", goodWithChildren=" + goodWithChildren + ", goodWithDogs="
				+ goodWithDogs + ", goodWithCats=" + goodWithCats + ", location=" + location + ", distance=" + distance
				+ ", spayedNeutered=" + spayedNeutered + ", houseTrained=" + houseTrained + ", declawed=" + declawed
				+ ", photosUrl=" + photosUrl + ", tags=" + tags + ", description=" + description+ "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.replace("!", "").replace("@", "")
				.replace("#", "").replace("$", "").replace("%", "").replace("^", "").replace("&", "").replace("*", "")
				.replace("amp", "").replace("44", "").replace("10", "").replace(";;", ",").replace("nbsp", "")
				.replace(";", "").replace("039", "'").replace("39", "").replace("34", "");
	}

	public String getDbString() {
		return dbString;
	}

	public void setDbString(String dbString) {
		this.dbString = dbString;
	}
	
	
	
}
