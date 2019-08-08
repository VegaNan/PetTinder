package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SignUpController {

	@FXML
	TextField firstNameField, lastNameField, locationField, emailField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	RadioButton dogRadioButton, catRadioButton, otherRaidoButton;
	
}
