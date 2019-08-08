
package controllers;

import objects.Animal;

public class MainController {

	static DatabaseController dbController = new DatabaseController();
	
	/*		
	 	attributes.spayed_neutered
		status
		size
		type
		age
		distance
	*/
	
	/*
		Baby
		Young
		Adult
		Senior
	*/

	public static void main(String[] args) {
		/*APIController.GetAccessToken();
		String info = APIController.animalRequest();
		dbController.insertAnimalRecords(info);
		info = APIController.organizationRequest();
		dbController.insertOrganizationRecords(info);
		System.out.println("org by id   " + dbController.getOrganizationById("UT202"));
		System.out.println("animal by org   " + dbController.getAnimalsByOrganization("UT202"));
		System.out.println("animal by org val   " + dbController.getAnimalsByOrganizationValue("address.postcode", "84660"));
		System.out.println("test animals by   " + dbController.getAnimalsBy("gender", "Female"));
	
		System.out.println("test pet search   " + petSearch("gender", "Female"));
		*/
		
		Animal[] animals = dbController.createAnimalObjects(dbController.getAnimalById(45201542));
		for(Animal animal : animals) {
			System.out.println(animal.toString());
		}
		System.out.println("done");
	}
	
	private static String petSearch(String petInfo, String value) {
		String results = dbController.getAnimalsBy(petInfo, value);
		return results;
	}
	
}
