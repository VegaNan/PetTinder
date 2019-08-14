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
	int imageNum = 0;
	static Animal[] currentAnimalArray;
	
	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton;

	public void setUser(User user) {
		SwipeyPageController.currentUser = user;
		currentAnimalArray = dbc.getAnimalsBy("species", currentUser.getAnimalPref());
		currentAnimal = currentAnimalArray[inArraySlot];
	}
	
	public void profilePage() {
		
	}
	
	public void matchesPage() {
		
	}
	
	public void yesAction() {
		currentUser.addAnimalToMatched(currentAnimal.getId(), currentAnimal.getName());
		dbc.updateUser(currentUser);
		newPet();
	}
	
	public void maybeAction() {
		currentUser.addAnimalToMaybe(currentAnimal.getId(), currentAnimal.getName());
		dbc.updateUser(currentUser);
		newPet();
	}
	
	public void noAction() {
		currentUser.addAnimalToNo(currentAnimal.getId(), currentAnimal.getName());
		dbc.updateUser(currentUser);
		newPet();
	}
	
	public void newPet() {

		imageNum = 0;
		System.out.println(currentAnimal.toString());
		inArraySlot++;
		if(inArraySlot < currentAnimalArray.length) {
			currentAnimal = currentAnimalArray[inArraySlot];
			Image image = new Image(currentAnimalArray[inArraySlot].getPhotosUrl()[0]);
			System.out.println(currentAnimal.toString());
			animalView.setImage(image);
			primaryStage.show();
		}else {
			String url = "https://www.nomore.org.au/sites/all/themes/nomore/img/noMore.jpg";
			Image image = new Image(url);
			animalView.setImage(image);
			primaryStage.show();
		}
	}
	
	public void changeImage(int imageNum) {
		if (currentAnimal.getPhotosUrl().length > imageNum) {
			Image image = new Image(currentAnimalArray[inArraySlot].getPhotosUrl()[imageNum]);
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
