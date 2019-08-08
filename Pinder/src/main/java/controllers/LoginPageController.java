package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginPageController {

	private Stage primaryStage;
	
	@FXML
	Button loginButton, signUpButton;
	
	public void loginPage(ActionEvent e) {
		
	}
	
	public void signUpPage(ActionEvent e) {
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
}
