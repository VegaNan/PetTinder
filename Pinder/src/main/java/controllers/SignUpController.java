package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.AnimalPref;
import objects.User;

public class SignUpController {

	private Stage primaryStage;
	private User newUser;

	@FXML
	TextField firstNameField, lastNameField, locationField, emailField;

	@FXML
	PasswordField passwordField;

	@FXML
	RadioButton dogRadioButton, catRadioButton, otherRadioButton;

	@FXML
	Button submitButton;

	private void changeScene(String filename, ActionEvent event) throws IOException {
		// parent takes in the file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(filename));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);

		SwipeyPageController controller = loader.getController();
		controller.setUser(newUser);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

//	FXMLLoader loader = new FXMLLoader();loader.setLocation(getClass().getResource("SwipeyPage.fxml"));
//	Parent swipeyParent = loader.load();
//	
//	Scene swipeyScene = new Scene(swipeyParent);
//	SwipeyPageController controller = loader.getController();
//	controller.setUser(newUser);
//	
//	Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
//	window.setScene(swipeyScene);
//	window.show();
	
//	// parent takes in the file
//  Parent parent = FXMLLoader.load(getClass().getResource(filename));
//  // makes new scene based on parent
//  Scene scene = new Scene(parent);
//  // takes in the stage of this class
//  Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//  // sets the scene
//  window.setScene(scene);
//  // displays the scene
//  window.show();

	public void submitUser(ActionEvent e) throws IOException {
		if(!firstNameField.getText().trim().isEmpty() & 
				!lastNameField.getText().trim().isEmpty() & 
				!locationField.getText().trim().isEmpty() & 
				!emailField.getText().trim().isEmpty() & 
				!passwordField.getText().trim().isEmpty() & 
				(dogRadioButton.isSelected() | 
						catRadioButton.isSelected() | 
						otherRadioButton.isSelected())) {
			newUser = new User();
			newUser.setFirstName(firstNameField.getText().trim());
			newUser.setLastName(lastNameField.getText().trim());
			newUser.setEmail(emailField.getText().trim());
			newUser.setPassword(passwordField.getText().trim());
			
			if(dogRadioButton.isSelected()) {
				newUser.setAnimalPref(AnimalPref.DOGS);
			} else if(catRadioButton.isSelected()) {
				newUser.setAnimalPref(AnimalPref.CATS);
			} else {
				newUser.setAnimalPref(AnimalPref.OTHER);
			}
			
			changeScene("SwipeyPage.fxml", e);
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
