package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import objects.Animal;
import objects.User;
import views.GUI;

public class SwipeyPageController{
	
	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	private static Animal currentAnimal;
	private static User currentUser;
	public static int inArraySlot = 0;
	private int imageNum = 0;
	public static Animal[] currentAnimalArray;

	private String url = "https://thumbs.dreamstime.com/z/no-animals-sign-allowed-white-background-86517013.jpg";

	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton, leftImage, rightImage, moreInfoButton;
	
	@FXML 
	Label animalName;
	
	@FXML
    public void initialize() {
		inArraySlot = 0;
		if(SignUpController.newUser != null) {
			currentUser = SignUpController.newUser;
			currentAnimalArray = new Animal[(dbc.getAnimalsBy("type", currentUser.getAnimalPref())).length];
			currentAnimalArray = dbc.getAnimalsBy("type", currentUser.getAnimalPref());
			newPet();
		}else if(LoginPageController.user !=null ){
			currentUser = LoginPageController.user;
			currentAnimalArray = new Animal[(dbc.getAnimalsBy("type", currentUser.getAnimalPref())).length];
			currentAnimalArray = dbc.getAnimalsBy("type", currentUser.getAnimalPref());
			newPet();
		}
    }

	public void setUser(User user) {
		currentUser = user;
	}
	
	public void settingsPage() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/SettingsPage.fxml"));
		Scene scene = new Scene(parent);
//		scene.getStylesheets().add("MatchesPageStyle.css");
		Stage window = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsPage.fxml"));
		SettingsController controller = new SettingsController();
		
		controller.setUser(currentUser);
		controller.setPrimaryStage(primaryStage);
		
		loader.setController(controller);
				
		window.setScene(scene);
		window.show();
	}
	
	public void moreInfo() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/MoreInfo.fxml"));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;

		scene.getStylesheets().add("MoreInfoStyle.css");
		window.setScene(scene);
		window.show();
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
	
	public void keyPress(KeyEvent key) throws IOException {
		if(key.getCode() == KeyCode.RIGHT) {
			yesAction();
		} else if(key.getCode() == KeyCode.LEFT) {
			noAction();
		} else if(key.getCode() == KeyCode.DOWN) {
			maybeAction();
		} else if(key.getCode() == KeyCode.UP){
			moreInfo();
		}else {
			
		}
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
		imageNum = 0;
		inArraySlot ++;
		
		if(inArraySlot < currentAnimalArray.length) {
			if(currentUser.getMatched().contains((currentAnimalArray)[inArraySlot].getId()) 
					|| currentUser.getMaybe().contains((currentAnimalArray)[inArraySlot].getId())
					|| currentUser.getNo().contains((currentAnimalArray)[inArraySlot].getId())) {
				newPet();
			}else {
				currentAnimal = currentAnimalArray[inArraySlot];
				Image image = new Image(currentAnimalArray[inArraySlot].getPhotosUrl()[0]);
				animalView.setImage(image);
				leftImage.setVisible(false);
				if(currentAnimal.getPhotosUrl().length < 2) {
					rightImage.setVisible(false);
				}
				animalName.setText(currentAnimal.getName());
				primaryStage.show();
			}
		}else {
			Image image = new Image(url);
			animalView.setImage(image);
			primaryStage.show();
			yesButton.setDisable(true); 
			maybeButton.setDisable(true);
			noButton.setDisable(true);
			leftImage.setDisable(true);
			rightImage.setDisable(true);
			moreInfoButton.setDisable(true);
			animalName.setText("");
			primaryStage.show();
		}
	}
	
	public void changeImage(int imageNum) {
		if(imageNum <= 0) {
			leftImage.setVisible(false);
		}else {
			leftImage.setVisible(true);
		}
		
		if(imageNum >= currentAnimal.getPhotosUrl().length - 1) {
			rightImage.setVisible(false);
		}else {
			rightImage.setVisible(true);
		}
		
		if (currentAnimal.getPhotosUrl().length > imageNum && 0 <= imageNum) {
			Image image = new Image(currentAnimal.getPhotosUrl()[imageNum]);
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

	public static User getUser() {
		return currentUser;
	}
	
	public int getInArraySlot() {
		return inArraySlot;
	}

	public void setInArraySlot(int inArraySlot) {
		SwipeyPageController.inArraySlot = inArraySlot;
	}
	
}
