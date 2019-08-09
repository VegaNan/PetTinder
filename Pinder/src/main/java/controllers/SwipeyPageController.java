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
	
	Animal currentAnimal;
	User currentUser;
	int inArraySlot = 0;
	Animal[] currentAnimalArray;
	
	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton;

	public void setUser(User user) {
		this.currentUser = user;
		currentAnimalArray = dbc.getAnimalsBy("species", currentUser.getAnimalPref());
		currentAnimal = currentAnimalArray[inArraySlot];
		String url = currentAnimal.getPhotosUrl();
		Image image = new Image(url);
	}
	
	public void profilePage() {
		
	}
	
	public void matchesPage() {
		
	}
	
	public void yesAction() {
		currentUser.addAnimalToMatched(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void maybeAction() {
		currentUser.addAnimalToMaybe(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void noAction() {
		currentUser.addAnimalToNo(currentAnimal.getId(), currentAnimal.getName());
		newPet();
	}
	
	public void newPet() {
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
