package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		currentAnimalArray = dbc.getAnimalsBy("type", currentUser.getAnimalPref());
	}
	
	
	public void profilePage() {
		
	}
	
	public void matchesPage() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/MatchesPage.fxml"));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MatchesPage.fxml"));
		MatchesPageController controller = new MatchesPageController();
		controller.setUser(currentUser);
		loader.setController(controller);
		
		controller.setPrimaryStage(primaryStage);
		window.setScene(scene);
		window.show();		
	}
	
	public void yesAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToMatched(currentAnimal.getId(), currentAnimal.getName());
		}
		newPet();
	}
	
	public void maybeAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToMaybe(currentAnimal.getId(), currentAnimal.getName());
		}
		newPet();
	}
	
	public void noAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToNo(currentAnimal.getId(), currentAnimal.getName());
		}
		newPet();
	}
	
	public void rightPicture() {
		changeImage(imageNum + 1);
	}
	
	public void leftPicture() {
		changeImage(imageNum - 1);
	}
	
	public void newPet() {
		imageNum = 0;
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
		if (currentAnimal.getPhotosUrl().length > imageNum && 0 <= imageNum) {
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
