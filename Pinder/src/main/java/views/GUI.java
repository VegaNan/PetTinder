package views;

import controllers.APIController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		APIController.GetAccessToken();	
		APIController.animalRequest();
	}

}
