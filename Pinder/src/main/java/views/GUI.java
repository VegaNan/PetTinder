package views;

import controllers.LoginPageController;
import controllers.SignUpController;
import controllers.SwipeyPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.User;

public class GUI extends Application {

//	private void changeScene(String filename, Stage window) {
//		// parent takes in the file
//		Parent parent;
//		try {
//			parent = FXMLLoader.load(getClass().getResource(filename));
//			Scene scene = new Scene(parent);
//			window.setScene(scene);
//			window.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	private void changeScene(String filename, ActionEvent event) throws IOException {
//        // parent takes in the file
//        Parent parent = FXMLLoader.load(getClass().getResource(filename));
//        // makes new scene based on parent
//        Scene scene = new Scene(parent);
//        // takes in the stage of this class
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        // sets the scene
//        window.setScene(scene);
//        // displays the scene
//        window.show();
//    }

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			LoginPageController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			
			Scene scene = new Scene(root, 450, 700);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
