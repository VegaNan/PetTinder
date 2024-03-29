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
import objects.Organization;
import views.GUI;

public class MoreInfoController {

	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	public static Animal animal;
	public int imageNum = 0;
	
	@FXML
	Label typeAnimalLabel, milesAwayLabel, organizationLabel, animalNameLabel, petDescriptionLabel;
	@FXML
	ImageView animalImage;
	@FXML
	Button leftImage, rightImage;

	@FXML
	public void initialize() {
		animal = SwipeyPageController.currentAnimalArray[SwipeyPageController.inArraySlot];
		Organization org = dbc.getOrganizationById(animal.getOrganizationId());
		System.out.println(org.toString());
		
		typeAnimalLabel.setText("Type: " + animal.getType());
		milesAwayLabel.setText("Location: " + animal.getLocation());		
	
		organizationLabel.setText("Organization: " + org.getName());
		petDescriptionLabel.setText("Description: " + animal.getDescription().replace("!", "").replace("@", "")
				.replace("#", "").replace("$", "").replace("%", "").replace("^", "").replace("&", "").replace("*", "")
				.replace("amp", "").replace("44", "").replace("10", "").replace(";;", ",").replace("nbsp", "")
				.replace(";", "").replace("039", "'").replace("39", ""));
		animalNameLabel.setText(animal.getName());
		animalImage.setImage(new Image(animal.getPhotosUrl()[0]));
		leftImage.setVisible(false);
		if (animal.getPhotosUrl().length < 2) {
			rightImage.setVisible(false);
		}
	}
	
	public void setAnimal(Animal a) {
		animal = a;
		typeAnimalLabel.setText("Type: " + animal.getType());
		milesAwayLabel.setText("Location: " + animal.getLocation());
	
		organizationLabel.setText("Organization: " + animal.getOrganizationId());
		petDescriptionLabel.setText("Description: " + animal.getDescription().replace("!", "").replace("@", "")
				.replace("#", "").replace("$", "").replace("%", "").replace("^", "").replace("&", "").replace("*", "")
				.replace("amp", "").replace("44", "").replace("10", "").replace(";;", ",").replace("nbsp", "")
				.replace(";", "").replace("039", "'"));
		animalNameLabel.setText(animal.getName());
		animalImage.setImage(new Image(animal.getPhotosUrl()[0]));
		leftImage.setVisible(false);
		if (animal.getPhotosUrl().length < 2) {
			rightImage.setVisible(false);
		}
		
		Stage window = primaryStage;
		window.show();
	}

	public void keyPress(KeyEvent key) throws IOException {
		if (key.getCode() == KeyCode.RIGHT) {
			rightPicture();
		} else if (key.getCode() == KeyCode.LEFT) {
			leftPicture();
		} else if (key.getCode() == KeyCode.DOWN) {
			backAction();
		} else {

		}
	}

	public void rightPicture() {
		if (animal.getPhotosUrl().length - 1 > imageNum && 0 <= imageNum) {
			imageNum++;
		}
		changeImage(imageNum);
	}

	public void leftPicture() {
		if (animal.getPhotosUrl().length > imageNum && 0 < imageNum) {
			imageNum -= 1;
		}
		changeImage(imageNum);
	}

	public void changeImage(int imageNum) {
		if (imageNum <= 0) {
			leftImage.setVisible(false);
		} else {
			leftImage.setVisible(true);
		}
		if (imageNum >= animal.getPhotosUrl().length - 1) {
			rightImage.setVisible(false);
		} else {
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

		scene.getStylesheets().add("SwipeyPageStyle.css");
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		controller.setPrimaryStage(primaryStage);
		window.setScene(scene);
		window.show();
	}

}
