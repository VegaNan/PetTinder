package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private int currentPage = 0;
	private int currentAnimal = 0;
	private static String[] animalIds;

	@FXML
	Button petimg0, petimg1, petimg2, petimg3, petimg4, petimg5, petimg6, petimg7, petimg8, petimg9;
	@FXML
	Label pettxt0, pettxt1, pettxt2, pettxt3, pettxt4, pettxt5, pettxt6, pettxt7, pettxt8, pettxt9, pageNum;
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

	public void setPet() {
		if(currentPage <= animalIds.length/10) {
			currentPage++;
			int slot = 0;
			while(currentAnimal < animalIds.length && slot < 10) {
				int id = Integer.parseInt(animalIds[currentAnimal]);
				Animal animal = dbc.getAnimalById(id);
				String photo = animal.getPhotosUrl()[0];
				String text = animal.getDescription();
				ImageView photoImg = new ImageView(new Image(photo,100, 100 , true, true));
				
				switch(slot){
				case 0:
					petimg0.setGraphic(photoImg);
					pettxt0.setText(text);
					break;
				case 1:
					petimg1.setGraphic(photoImg);
					pettxt1.setText(animal.getDescription());
					break;
				case 2:
					petimg2.setGraphic(photoImg);
					pettxt2.setText(animal.getDescription());
					break;
				case 3:
					petimg3.setGraphic(photoImg);
					pettxt3.setText(animal.getDescription());
					break;
				case 4:		
					petimg4.setGraphic(photoImg);
					pettxt4.setText(animal.getDescription());
					break;
				case 5:
					petimg5.setGraphic(photoImg);
					pettxt5.setText(animal.getDescription());
					break;
				case 6:
					petimg6.setGraphic(photoImg);
					pettxt6.setText(animal.getDescription());
					break;
				case 7:
					petimg7.setGraphic(photoImg);
					pettxt7.setText(animal.getDescription());
					break;
				case 8:
					petimg8.setGraphic(photoImg);
					pettxt8.setText(animal.getDescription());
					break;
				case 9:
					petimg9.setGraphic(photoImg);
					pettxt9.setText(animal.getDescription());
					break;
					
				}
				currentAnimal++;
				slot++;
		
			}
			
			while(currentAnimal == animalIds.length && slot < 10) {

				switch(slot){
				case 0:
					petimg0.setGraphic(null);
					pettxt0.setText("");
					break;
				case 1:
					petimg1.setGraphic(null);
					pettxt1.setText("");
					break;
				case 2:
					petimg2.setGraphic(null);
					pettxt2.setText("");
					break;
				case 3:
					petimg3.setGraphic(null);
					pettxt3.setText("");
					break;
				case 4:		
					petimg4.setGraphic(null);
					pettxt4.setText("");
					break;
				case 5:
					petimg5.setGraphic(null);
					pettxt5.setText("");
					break;
				case 6:
					petimg6.setGraphic(null);
					pettxt6.setText("");
					break;
				case 7:
					petimg7.setGraphic(null);
					pettxt7.setText("");
					break;
				case 8:
					petimg8.setGraphic(null);
					pettxt8.setText("");
					break;
				case 9:
					petimg9.setGraphic(null);
					pettxt9.setText("");
					break;
					
				}
				slot++;
			}
			pageNum.setText(currentPage + " / " + (animalIds.length/10 + 1));
			Stage window = primaryStage;
			window.show();
		}
	}
	public void setPetBack(){
		if(currentPage > 1) {
			currentPage--;
			if(currentAnimal >= 20) {
				currentAnimal = currentPage*10 - 10;
			}
			int slot = 0;
			while(currentAnimal< animalIds.length && slot < 10) {
				int id = Integer.parseInt(animalIds[currentAnimal]);
				Animal animal = dbc.getAnimalById(id);
				String photo = animal.getPhotosUrl()[0];
				String text = animal.getDescription();
				ImageView photoImg = new ImageView(new Image(photo,100, 100 , true, true));
				
				switch(slot){
				case 0:
					petimg0.setGraphic(photoImg);
					petimg0.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
						}
					});
					pettxt0.setText(text);
					break;
				case 1:
					petimg1.setGraphic(photoImg);
					pettxt1.setText(animal.getDescription());
					break;
				case 2:
					petimg2.setGraphic(photoImg);
					pettxt2.setText(animal.getDescription());
					break;
				case 3:
					petimg3.setGraphic(photoImg);
					pettxt3.setText(animal.getDescription());
					break;
				case 4:		
					petimg4.setGraphic(photoImg);
					pettxt4.setText(animal.getDescription());
					break;
				case 5:
					petimg5.setGraphic(photoImg);
					pettxt5.setText(animal.getDescription());
					break;
				case 6:
					petimg6.setGraphic(photoImg);
					pettxt6.setText(animal.getDescription());
					break;
				case 7:
					petimg7.setGraphic(photoImg);
					pettxt7.setText(animal.getDescription());
					break;
				case 8:
					petimg8.setGraphic(photoImg);
					pettxt8.setText(animal.getDescription());
					break;
				case 9:
					petimg9.setGraphic(photoImg);
					pettxt9.setText(animal.getDescription());
					break;
					
				}
				currentAnimal++;
				slot++;
		
			}
			pageNum.setText(currentPage + " / " + (animalIds.length/10 + 1));
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
