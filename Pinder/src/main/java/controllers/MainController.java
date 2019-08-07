package controllers;

public class MainController {

	public static void main(String[] args) {
		DatabaseController dc = new DatabaseController();
		APIController.GetAccessToken();
//		String info = APIController.animalRequest();
//		dc.insertAnimalRecords(info);
//		System.out.println("Done");
//		System.out.println(dc.getAnimalById(45189636));
		APIController.organizationRequest();
		System.out.println(dc.getOrganizationById("UT189"));

		DatabaseController dbController = new DatabaseController();
		//APIController.GetAccessToken();
		//String info = APIController.animalRequest();
		//dbController.insertAnimalRecords(info);
		//info = APIController.organizationRequest();
		//dbController.insertOrganizationRecords(info);
		System.out.println("org by id   " + dbController.getOrganizationById("UT189"));
		System.out.println("animal by org   " + dbController.getAnimalsByOrganization("UT189"));
		System.out.println("animal by org val   " + dbController.getAnimalsByOrganizationValue("postcode", "84660"));

	}
}
