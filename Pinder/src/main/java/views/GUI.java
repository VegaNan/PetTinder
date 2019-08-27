package views;

import controllers.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI extends Application {	
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GUI.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			LoginPageController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			Scene scene = new Scene(root, 450, 700);
			scene.getStylesheets().add("LoginPageStyle.css");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}