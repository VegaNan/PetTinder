package controllers;

public class MainController {

	public static void main(String[] args) {
		DatabaseController dc = new DatabaseController();
		APIController.GetAccessToken();
		String info = APIController.animalRequest();
		dc.insertAnimalRecords(info);
		System.out.println("Done");
//		System.out.println(dc.getAnimalById(1));
//		APIController.organizationRequest();
	}
}
