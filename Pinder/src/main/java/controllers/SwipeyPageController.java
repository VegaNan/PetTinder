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

public class SwipeyPageController{
	
	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	private static Animal currentAnimal;
	private static User currentUser;
	public int inArraySlot = 0;
	private int imageNum = 0;
	private static Animal[] currentAnimalArray;
	private String url = "https://thumbs.dreamstime.com/z/no-animals-sign-allowed-white-background-86517013.jpg";

	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton;

	public void setUser(User user) {
		currentUser = user;
		currentAnimalArray = dbc.getAnimalsBy("type", currentUser.getAnimalPref());
	}
	
	public void profilePage() {
		
	}
	
	public void matchesPage() throws IOException {
		String[] animals = new String[currentUser.getMatched().size()];
		currentUser.getMatched().toArray(animals);
		
		Parent parent = FXMLLoader.load(getClass().getResource("/MatchesPage.fxml"));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("MatchesPageStyle.css");
		Stage window = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MatchesPage.fxml"));
		MatchesPageController controller = new MatchesPageController();
		
		controller.setUser(currentUser);
		controller.setAnimalIds(animals);
		controller.setPrimaryStage(primaryStage);
		
		loader.setController(controller);
				
		window.setScene(scene);
		window.show();		
	}
	
	public void yesAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToMatched(currentAnimal.getId());
			dbc.updateUser(currentUser);
		}
		newPet();
	}
	
	public void maybeAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToMaybe(currentAnimal.getId());
			dbc.updateUser(currentUser);
		}
		newPet();
	}
	
	public void noAction() {
		if(currentAnimal !=null) {
			currentUser.addAnimalToNo(currentAnimal.getId());
			dbc.updateUser(currentUser);
		}
		newPet();
	}
	
	public void rightPicture() {
		if (currentAnimal.getPhotosUrl().length - 1 > imageNum && 0 <= imageNum) {
			imageNum ++;
		}
		changeImage(imageNum);
	}
	
	public void leftPicture() {
		if (currentAnimal.getPhotosUrl().length > imageNum && 0 < imageNum) {
			imageNum-=1;
		}
		changeImage(imageNum);
	}
	
	public void newPet() {
		//TODO fix this
		imageNum = 0;
		inArraySlot++;
		
		if(inArraySlot < currentAnimalArray.length) {
			if(currentUser.getMatched().contains((currentAnimalArray)[inArraySlot].getId()) 
					|| currentUser.getMaybe().contains((currentAnimalArray)[inArraySlot].getId())
					|| currentUser.getNo().contains((currentAnimalArray)[inArraySlot].getId())) {
				System.out.println("skipping");
				inArraySlot++;
				newPet();
			}else {
				currentAnimal = currentAnimalArray[inArraySlot];
				System.out.println(currentAnimal.getName());
				Image image = new Image(currentAnimalArray[inArraySlot].getPhotosUrl()[0]);
				animalView.setImage(image);
				primaryStage.show();
			}
		}else {
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
