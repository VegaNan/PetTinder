package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application{
	
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
		changeScene("/views/LoginPage.fxml", primaryStage);
		
		
		
	}



	
	

	

}
