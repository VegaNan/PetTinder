
package controllers;

public class MainController {

	static DatabaseController dbController = new DatabaseController();

	public static void main(String[] args) {
		APIController.GetAccessToken();
		String info = APIController.animalRequest();
		dbController.insertAnimalRecords(info);
		info = APIController.organizationRequest();
		dbController.insertOrganizationRecords(info);
		/*
		System.out.println("org by id   " + dbController.getOrganizationById("UT202"));
		System.out.println("animal by org   " + dbController.getAnimalsByOrganization("UT202"));
		System.out.println("animal by org val   " + dbController.getAnimalsByOrganizationValue("address.postcode", "84660"));
		System.out.println("test animals by   " + dbController.getAnimalsBy("gender", "Female"));
<<<<<<< HEAD
		*/
=======
		*/

>>>>>>> branch 'Brooke' of https://github.com/VegaNan/PetTinder.git
	}
	
	
}
