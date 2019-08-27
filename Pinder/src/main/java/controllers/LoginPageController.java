package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class LoginPageController {

	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	public static User user;
	
	@FXML
	TextField username, password;
	
	@FXML
	Label loginLabel;
	
	@FXML
	Button loginButton, signUpButton;

	private void changeScene(String filename, User newUser) throws IOException {
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
			login();
		}
	}
	
	public void login() throws IOException{
		if(!username.getText().trim().isEmpty() & !password.getText().trim().isEmpty()) {
			//check that username and password are in database and match
			user = dbc.getUser(username.getText(), password.getText());
			
			if(user == null) {
				//TODO show this on the GUI
				loginLabel.setText("try again");
				System.out.println("incorrect login credentials");
			}else {
				changeScene("/SwipeyPage.fxml", user);
			}
		}
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
