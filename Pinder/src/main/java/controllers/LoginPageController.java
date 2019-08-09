package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginPageController {

	private Stage primaryStage;
	
	@FXML
	Button loginButton, signUpButton;
	
	
	
	public void loginPage() throws IOException {

	}
	
	public void signUpPage() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/SignUp.fxml"));
		Scene newContactScene = new Scene(parent);
		Stage window = primaryStage;
		window.setScene(newContactScene);
		window.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
}
