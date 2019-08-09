package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.Animal;
import objects.User;
import views.GUI;

public class SwipeyPageController {
	
	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	static Animal currentAnimal;
	static User currentUser;
	int inArraySlot = 0;
	static Animal[] currentAnimalArray;
	
	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton;

	public void setUser(User user) {
		SwipeyPageController.currentUser = user;
		currentAnimalArray = dbc.getAnimalsBy("species", currentUser.getAnimalPref());
		currentAnimal = currentAnimalArray[inArraySlot];

		System.out.println(currentAnimal.toString());
		
	}
	
	public void profilePage() {
		
	}
	
	public void matchesPage() {
		
	}
	
	public void yesAction() {
		System.out.println(currentAnimal.toString());
		currentUser.addAnimalToMatched(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void maybeAction() {
		System.out.println(currentAnimal.toString());
		currentUser.addAnimalToMaybe(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void noAction() {
		System.out.println(currentAnimal.toString());
		currentUser.addAnimalToNo(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void newPet() {
		System.out.println(currentAnimal.toString());
		inArraySlot++;
		if(inArraySlot < currentAnimalArray.length) {
			currentAnimal = currentAnimalArray[inArraySlot];
			String url = currentAnimal.getPhotosUrl();
			Image image = new Image(url);
			animalView.setImage(image);
			primaryStage.show();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
}
