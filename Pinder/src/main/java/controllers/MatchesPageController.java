package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	private int currentPage = 0;
	private int currentAnimal = 0;
	private static String[] animalIds;

	@FXML
	ImageView petimg0, petimg1, petimg2, petimg3, petimg4, petimg5, petimg6, petimg7, petimg8, petimg9;
	@FXML
	Label pettxt0, pettxt1, pettxt2, pettxt3, pettxt4, pettxt5, pettxt6, pettxt7, pettxt8, pettxt9;
	 @FXML
     public void initialize() {
		 currentUser = SwipeyPageController.getUser();
		 animalIds= new String[currentUser.getMatched().size()];
		 currentUser.getMatched().toArray(animalIds);
		 setPet();
     }
	
	public void backToSwipey() throws IOException {
		String filename = "/SwipeyPage.fxml";
		Parent parent = FXMLLoader.load(getClass().getResource(filename));
		Scene scene = new Scene(parent);
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
		SwipeyPageController controller = new SwipeyPageController();
		loader.setController(controller);
		controller.setPrimaryStage(primaryStage);
		controller.setUser(currentUser);
		controller.inArraySlot = inArraySlot - 1;
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
	
	public String[] getAnimalIds() {
		return animalIds;
	}
	
	public void setAnimalIds(String[] animalIds) {
		MatchesPageController.animalIds = animalIds;
	}
	
	public int getInArraySlot() {
		return inArraySlot;
	}

	public void setInArraySlot(int inArraySlot) {
		this.inArraySlot = inArraySlot;
	}

	public void setPet() {
		currentPage++;
		int slot = 0;
		while(currentAnimal < animalIds.length && slot < 10) {
			int id = Integer.parseInt(animalIds[currentAnimal]);
			Animal animal = dbc.getAnimalById(id);
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
			currentAnimal++;
			slot++;
	
		Stage window = primaryStage;
		window.show();
		}
	}
	public void setPetBack() {
		currentPage--;
		if(currentAnimal >= 20) {
			currentAnimal -=20;
		}
		int slot = 0;
		while(currentAnimal< animalIds.length && slot < 10) {
			int id = Integer.parseInt(animalIds[currentAnimal]);
			Animal animal = dbc.getAnimalById(id);
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
			currentAnimal++;
			slot++;
	
		Stage window = primaryStage;
		window.show();
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
