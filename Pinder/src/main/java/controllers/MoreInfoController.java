package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.GUI;

public class MoreInfoController {

	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();

	public void matchesPage() throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("/MoreInfoPage.fxml"));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoreInfoPage.fxml"));

		window.setScene(scene);
		window.show();

	}

}
