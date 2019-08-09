package objects;

public class Organization {

	private int organizationId;
	private String name;
	private String location;
	private String state;
	private String country;
	private String contactEmail;
	private String contactPhone;
	private String zipcode;
	private boolean hasWebsite;
	private String websiteUrl;
	
	public Organization(){
		
	}

	public Organization(int organizationId, String name, String location, String state, String country,
			String contactEmail, String contactPhone, String zipcode, boolean hasWebsite, String websiteUrl) {
		super();
		this.organizationId = organizationId;
		this.name = name;
		this.location = location;
		this.state = state;
		this.country = country;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.zipcode = zipcode;
		this.hasWebsite = hasWebsite;
		this.websiteUrl = websiteUrl;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isHasWebsite() {
		return hasWebsite;
	}

	public void setHasWebsite(boolean hasWebsite) {
		this.hasWebsite = hasWebsite;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
}
