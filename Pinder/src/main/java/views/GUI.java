package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


	

public class GUI extends Application{
	
	public static Stage primaryStage;
	@FXML
	Button signUpButton;
	
	private void changeScene(String filename, Stage window) {
		// parent takes in the file
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource(filename));
			Scene scene = new Scene(parent);
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loginPage(primaryStage);
	}
	
	private void matchesPage(Stage primaryStage) {
		changeScene("/views/MatchesPage.fxml", primaryStage);
	}
	
	private void SignUpPage(Stage primaryStage) {
		changeScene("/views/Sign Up.fxml", primaryStage);
	}

	private void loginPage(Stage primaryStage) {
		
		
		changeScene("/views/LoginPage.fxml", primaryStage);
		
		
		
		
	}
	
	

	

}
