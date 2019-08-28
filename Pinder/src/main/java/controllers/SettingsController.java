package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class SettingsController {

	@FXML
	Label animalPrefLabel;
	
	@FXML
	Button backButton, saveButton, switchButton, logoutButton;

	private Stage primaryStage = GUI.primaryStage;
	private static User currentUser;

	public void setUser(User user) {
		currentUser = user;
//		animalPrefLabel.setText("Your animal preference is "
//				+ (currentUser.getAnimalPref().equals("Dog") ? "Dog" : "Cat") + ".\n Would you like to switch?");
	}

	public void onSwitchAction() {
		currentUser.setAnimalPref(currentUser.getAnimalPref().equals("Dog") ? "Cat" : "Dog");
		animalPrefLabel.setText("Your animal preference is "
				+ (currentUser.getAnimalPref().equals("Dog") ? "Dog" : "Cat") + ".\n Would you like to switch?");
	}

	public void onLogOutAction() throws IOException {
		currentUser = null;
		Parent parent = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("LoginPageStyle.css");
		Stage window = primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUp.fxml"));
		LoginPageController controller = new LoginPageController();

		loader.setController(controller);
		controller.setPrimaryStage(primaryStage);

		window.setScene(scene);
		window.show();
	}

	public void onBackAction() throws IOException {
		String filename = "/SwipeyPage.fxml";
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		controller.setPrimaryStage(primaryStage);
		controller.setUser(currentUser);
		window.setScene(scene);
		window.show();
	}

	public void onSaveAction() throws IOException {
		DatabaseController dbc = new DatabaseController();
		dbc.storeUser(currentUser);
		onBackAction();
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}