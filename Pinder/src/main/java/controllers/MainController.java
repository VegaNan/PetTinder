package controllers;

public class MainController {

	public static void main(String[] args) {
		DatabaseController dbController = new DatabaseController();
		APIController.GetAccessToken();
		String info = APIController.animalRequest();
		dbController.insertAnimalRecords(info);
		info = APIController.organizationRequest();
		dbController.insertOrganizationRecords(info);
		System.out.println(dbController.getAnimalsByOrganization("UT148"));
	}
}
