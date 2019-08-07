
package controllers;

public class MainController {

	static DatabaseController dbController = new DatabaseController();
	
	enum petInfo{
		SpayedNeutered,
		Status,
		Size,
		Type,
		Age,
		Distance
	}

	public static void main(String[] args) {
		//APIController.GetAccessToken();
		//String info = APIController.animalRequest();
		//dbController.insertAnimalRecords(info);
		//info = APIController.organizationRequest();
		//dbController.insertOrganizationRecords(info);
		/*
		System.out.println("org by id   " + dbController.getOrganizationById("UT202"));
		System.out.println("animal by org   " + dbController.getAnimalsByOrganization("UT202"));
		System.out.println("animal by org val   " + dbController.getAnimalsByOrganizationValue("address.postcode", "84660"));
		System.out.println("test animals by   " + dbController.getAnimalsBy("gender", "Female"));
		*/
		petSearch(petInfo.SpayedNeutered, "false");
	}
	
	private static String petSearch(petInfo petinfo, String value) {
		String key = "";
		
		switch (petinfo) {
		case Age:
			key = "";
			break;
		default:
			System.out.println("invalid");
			break;
		}
		
		dbController.getAnimalsBy(key, value);
		
		return null;
	}
	
}
