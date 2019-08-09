package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
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
	
	public void loginPage(ActionEvent e) {
		
	}
	
	public void signUpPage(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SwipeyPage.fxml"));
		Parent newContactParent = loader.load();
		
		Scene newContactScene = new Scene(newContactParent);
		
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
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
