package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.User;
import views.GUI;

public class MatchesPageController {
	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	private static User currentUser;
	private int inArraySlot;
	
	public void backToSwipey() throws IOException {
		String filename = "/SwipeyPage.fxml";
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		System.out.println(controller);
		
		controller.setPrimaryStage(primaryStage);
		controller.setUser(currentUser);
		controller.inArraySlot = inArraySlot;
		
		window.setScene(scene);
		window.show();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setUser(User user) {
		MatchesPageController.currentUser = user;
	}
	
	public int getInArraySlot() {
		return inArraySlot;
	}

	public void setInArraySlot(int inArraySlot) {
		this.inArraySlot = inArraySlot;
	}
	
	
	
}
