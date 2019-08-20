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
import views.GUI;

public class MoreInfoController {

	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	public static Animal animal;
	public int imageNum = 0;
	@FXML
	Label typeAnimalLabel, milesAwayLabel, organizationLabel, animalNameLabel;
	@FXML
	ImageView animalImage;
	@FXML
	Button leftImage, rightImage;
	
	@FXML
	public void initialize() {
		animal = SwipeyPageController.currentAnimalArray[SwipeyPageController.inArraySlot];
		typeAnimalLabel.setText("Type: "+ animal.getType());
		milesAwayLabel.setText("Location: " + animal.getLocation());
		organizationLabel.setText("Organization: " + animal.getOrganization());
		animalNameLabel.setText(animal.getName());
		animalImage.setImage(new Image(animal.getPhotosUrl()[0]));
		leftImage.setVisible(false);
		if(animal.getPhotosUrl().length < 2) {
			rightImage.setVisible(false);
		}
		
	}
	public void keyPress(KeyEvent key) throws IOException {
		if(key.getCode() == KeyCode.RIGHT) {
			rightPicture();
		} else if(key.getCode() == KeyCode.LEFT) {
			leftPicture();
		} else if(key.getCode() == KeyCode.DOWN) {
			backAction();	
		}else {
			
		}
	}
	
	public void rightPicture() {
		if (animal.getPhotosUrl().length - 1 > imageNum && 0 <= imageNum) {
			imageNum ++;
		}
		changeImage(imageNum);
	}
	
	public void leftPicture() {
		if (animal.getPhotosUrl().length > imageNum && 0 < imageNum) {
			imageNum-=1;
		}
		changeImage(imageNum);
	}
	
	public void changeImage(int imageNum) {
		if(0 > imageNum) {
			leftImage.setVisible(false);
		}else {
			leftImage.setVisible(true);
		}
		if(animal.getPhotosUrl().length <= imageNum) {
			rightImage.setVisible(false);
		}else {
			rightImage.setVisible(true);
		}
		
		if (animal.getPhotosUrl().length > imageNum && 0 <= imageNum) {
			Image image = new Image(animal.getPhotosUrl()[imageNum]);
			animalImage.setImage(image);
			primaryStage.show();
		}
	}
	
	

	public void backAction() throws IOException {
		String filename = "/SwipeyPage.fxml";
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		controller.setPrimaryStage(primaryStage);
		window.setScene(scene);
		window.show();
	}

}
