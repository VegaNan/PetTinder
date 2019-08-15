package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.Animal;
import objects.User;
import views.GUI;

public class MatchesPageController {
	private Stage primaryStage = GUI.primaryStage;
	DatabaseController dbc = new DatabaseController();
	private static User currentUser;
	private int inArraySlot;
	
	@FXML
	ImageView petimg0, petimg1, petimg2, petimg3, petimg4, petimg5, petimg6, petimg7, petimg8, petimg9;
	@FXML
	TextArea pettxt0, pettxt1, pettxt2, pettxt3, pettxt4, pettxt5, pettxt6, pettxt7, pettxt8, pettxt9;
	
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
	
	public void setPet(Animal[] animals) {
		for(int slot = 0; slot < 10; slot++) {
			for(Animal animal : animals) {
				String photo = animal.getPhotosUrl()[0];
				String text = animal.getDescription();
				Image photoImg = new Image(photo);

				switch(slot){
				case 0:
					petimg0.setImage(photoImg);
					pettxt0.setText(text);
					break;
				case 1:
					petimg1.setImage(photoImg);
					pettxt1.setText(animal.getDescription());
					break;
				case 2:
					petimg2.setImage(photoImg);
					pettxt2.setText(animal.getDescription());
					break;
				case 3:
					petimg3.setImage(photoImg);
					pettxt3.setText(animal.getDescription());
					break;
				case 4:		
					petimg4.setImage(photoImg);
					pettxt4.setText(animal.getDescription());
					break;
				case 5:
					petimg5.setImage(photoImg);
					pettxt5.setText(animal.getDescription());
					break;
				case 6:
					petimg6.setImage(photoImg);
					pettxt6.setText(animal.getDescription());
					break;
				case 7:
					petimg7.setImage(photoImg);
					pettxt7.setText(animal.getDescription());
					break;
				case 8:
					petimg8.setImage(photoImg);
					pettxt8.setText(animal.getDescription());
					break;
				case 9:
					petimg9.setImage(photoImg);
					pettxt9.setText(animal.getDescription());
					break;
				}
			}

		Stage window = primaryStage;
		window.show();
		}
	}
	
	
}
