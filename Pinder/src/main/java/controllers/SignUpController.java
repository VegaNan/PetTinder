package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
	
	public void submitUser(ActionEvent e) {
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
			
		}
	}
	
}
