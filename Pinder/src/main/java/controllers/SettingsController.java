package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class SettingsController {

	private Stage primaryStage = GUI.primaryStage;
	private static User currentUser;
	
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

		controller.setUser(currentUser);
		
		window.setScene(scene);
		window.show();
	}
	
	public void setUser(User user) {
		currentUser = user;
	}
	
	public void onSwitchAction() {
		currentUser.setAnimalPref(currentUser.getAnimalPref().equals("Dog") ? "Cat" : "Dog");
	}
	
	public void onLogOutAction() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/SignUp.fxml"));
		Scene scene = new Scene(parent);
//		scene.getStylesheets().add("MatchesPageStyle.css");
		Stage window = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignUp.fxml"));
		SignUpController controller = new SignUpController();
		
		loader.setController(controller);
				
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
}