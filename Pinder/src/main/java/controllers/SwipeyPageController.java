package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.User;

public class SwipeyPageController {
	
	User currentUser;
	private Stage primaryStage;
	
	@FXML
	ImageView animalView;
	
	@FXML
	Button profileButton, matchesButton, yesButton, maybeButton, noButton;

	public void setUser(User user) {
		this.currentUser = user;
	}
	
	public void profilePage(ActionEvent e) {
		
	}
	
	public void matchesPage(ActionEvent e) {
		
	}
	
	public void yesAction(ActionEvent e) {
		
	}
	
	public void maybeAction(ActionEvent e) {
		
	}
	
	public void noAction(ActionEvent e) {
		
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
}
