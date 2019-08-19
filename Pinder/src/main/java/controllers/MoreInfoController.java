package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.Animal;
import views.GUI;

public class MoreInfoController {

	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	@FXML
	Label typeAnimalLabel, milesAwayLabel, organizationLabel, animalNameLabel;
	@FXML
	ImageView animalImage;
	
	@FXML
	public void initialize() {
		Animal animal = SwipeyPageController.currentAnimalArray[SwipeyPageController.inArraySlot];
		typeAnimalLabel.setText("Type: "+ animal.getType());
		milesAwayLabel.setText("Location: " + animal.getLocation());
		organizationLabel.setText("Organization: " + animal.getOrganization());
		animalNameLabel.setText(animal.getName());
		animalImage.setImage(new Image(animal.getPhotosUrl()[0]));
		
	}
	
	public void matchesPage() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/MoreInfoPage.fxml"));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoreInfoPage.fxml"));
		window.setScene(scene);
		window.show();

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
