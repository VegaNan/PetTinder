
package controllers;

public class MainController {

	static DatabaseController dbController = new DatabaseController();

	public static void main(String[] args) {
		APIController.GetAccessToken();
		String info = APIController.animalRequest(1);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(2);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(3);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(4);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(5);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(6);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(7);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(8);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(9);
		dbController.insertAnimalRecords(info);
		info = APIController.animalRequest(10);
		dbController.insertAnimalRecords(info);
		
		
		info = APIController.organizationRequest();
		dbController.insertOrganizationRecords(info);
	}
}
