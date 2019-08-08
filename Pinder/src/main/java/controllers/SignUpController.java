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
import javafx.stage.Stage;
import objects.AnimalPref;
import objects.User;

public class SignUpController {

	@FXML
	TextField firstNameField, lastNameField, locationField, emailField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	RadioButton dogRadioButton, catRadioButton, otherRaidoButton;
	
	@FXML
	Button submitButton;
	
	public void submitUser(ActionEvent e) throws IOException {
		if(!firstNameField.getText().trim().isEmpty() & 
				!lastNameField.getText().trim().isEmpty() & 
				!locationField.getText().trim().isEmpty() & 
				!emailField.getText().trim().isEmpty() & 
				!passwordField.getText().trim().isEmpty() & 
				(dogRadioButton.isSelected() | 
						catRadioButton.isSelected() | 
						otherRaidoButton.isSelected())) {
			User newUser = new User();
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
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("NewContactStage.fxml"));
			Parent newContactParent = loader.load();
			
			Scene newContactScene = new Scene(newContactParent);
			SwipeyPageController controller = loader.getController();
			controller.setUser(newUser);
			
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			window.setScene(newContactScene);
			window.show();
			
		}
	}
	
}
