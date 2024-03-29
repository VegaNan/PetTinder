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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class SignUpController {

	private Stage primaryStage = GUI.primaryStage;
	public static User newUser;

	@FXML
	TextField firstNameField, lastNameField, locationField, emailField;

	@FXML
	PasswordField passwordField;

	@FXML
	RadioButton dogRadioButton, catRadioButton, otherRadioButton;

	@FXML
	Button submitButton, backButton;

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
	
	public void keyPress(KeyEvent key) throws IOException {
		if(key.getCode() == KeyCode.ENTER) {
			submitUser();
		}
	}

	public void submitUser() throws IOException {
		if(!firstNameField.getText().trim().isEmpty() & 
				!lastNameField.getText().trim().isEmpty() & 
				!locationField.getText().trim().isEmpty() & 
				!emailField.getText().trim().isEmpty() & 
				!passwordField.getText().trim().isEmpty() & 
				(dogRadioButton.isSelected() | 
						catRadioButton.isSelected())) // | otherRadioButton.isSelected())) 
		{
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
	
	public void back() throws IOException {
		GUI.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		LoginPageController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		Scene scene = new Scene(root, 450, 700);
		scene.getStylesheets().add("LoginPageStyle.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
