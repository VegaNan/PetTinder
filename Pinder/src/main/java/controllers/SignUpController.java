package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class SignUpController {

	private Stage primaryStage = GUI.primaryStage;
	private User newUser;

	@FXML
	TextField firstNameField, lastNameField, locationField, emailField;

	@FXML
	PasswordField passwordField;

	@FXML
	RadioButton dogRadioButton, catRadioButton, otherRadioButton;

	@FXML
	Button submitButton;

	private void changeScene(String filename) throws IOException {
		// parent takes in the file
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		
		System.out.println(controller);
		controller.setPrimaryStage(primaryStage);

		controller.setUser(newUser);
		
		window.setScene(scene);
		window.show();
	}

	public void submitUser() throws IOException {
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
			newUser.setLocation(locationField.getText().trim());
			
			if(dogRadioButton.isSelected()) {
				newUser.setAnimalPref("Dog");
			} else if(catRadioButton.isSelected()) {
				newUser.setAnimalPref("Cat");
			} else {
				newUser.setAnimalPref("");
			}
			DatabaseController dbc = new DatabaseController();
			dbc.storeUser(newUser);
			changeScene("/SwipeyPage.fxml");
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
