package views;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
public class AttackAndCure {
	public static void Attack(Character h, GridPane g, Stage s){
		try
		{
			h.attack();
			StartedGame.DesignUnderGrid(g,(Hero) h);
			StartedGame.UpdateAvailableHeroes(g);
			StartedGame.UpdateMap(g,s);
			
			GameOver(s);
			
		}
		catch(GameActionException g1){
			DisplayAlertBox(g1,s);
		}
	}
	public static void Cure(Character h, GridPane g, Stage s){
		try{
			
			((Hero)h).cure();
			StartedGame.DesignUnderGrid(g,(Hero) h);
			StartedGame.UpdateAvailableHeroes(g);
			StartedGame.UpdateMap(g,s);
			
			Checkwin(s);
		}
		catch(GameActionException g1){
			DisplayAlertBox(g1,s);
		}
	}
	
	public static void DisplayAlertBox(Exception x, Stage st){
		Stage alert = new Stage();
		alert.setAlwaysOnTop(true);
		alert.initModality( Modality.APPLICATION_MODAL );
		alert.initOwner(st);
		alert.setWidth(400);
		alert.setHeight(150);
		
		if(x instanceof InvalidTargetException){
			alert.setTitle("Invalid Target Exception");
			String s = x.getMessage();
			Label message = new Label(s);
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.getChildren().add(message);
			Scene scene= new Scene(l);
			alert.setScene(scene);
			
		}
		if(x instanceof NotEnoughActionsException){
			alert.setTitle("No enough Actions Exception");
			String s = x.getMessage();
			Label message = new Label(s);
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.getChildren().add(message);
			Scene scene= new Scene(l);
			alert.setScene(scene);
		}
		if(x instanceof MovementException ){
			alert.setTitle("Movement Exception");
			String s = x.getMessage();
			Label message = new Label(s);
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.getChildren().add(message);
			Scene scene= new Scene(l);
			alert.setScene(scene);
		}
		if(x instanceof NoAvailableResourcesException){
			alert.setTitle("No Available Resources Exception");
			String s = x.getMessage();
			Label message = new Label(s);
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.getChildren().add(message);
			Scene scene= new Scene(l);
			alert.setScene(scene);
		}
		if(x instanceof FileNotFoundException)
		{
			alert.setTitle("No Available Resources Exception");
			String s = x.getMessage();
			Label message = new Label(s);
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.getChildren().add(message);
			Scene scene= new Scene(l);
			alert.setScene(scene);
		}
		alert.showAndWait();
	    } 
	public static void Checkwin(Stage s){
		Image gameWon;
		try {
			if(Game.checkWin())
			{
			gameWon = new Image(new FileInputStream("GameWon.jpg"));
			ImageView GameWonView = new ImageView(gameWon);
			GameWonView.setFitHeight(1080);
			GameWonView.setFitWidth(1920); 
			GameWonView.setPreserveRatio(true);
			
			Group root = new Group(GameWonView);
			
			Button exit = new Button("Quit");
			root.getChildren().add(exit);
			exit.setPrefSize(300,40);
			exit.setOnAction(e-> s.close());
			Scene z= new Scene(root, 1080, 1920);
			s.setScene(z);
			s.setFullScreen(true);
			}
			 
		}
		catch (FileNotFoundException e) {
			System.out.print("NotReached");
		}
	    
		
	}
	public static void GameOver(Stage s){
		Image gameOver;
		try {
			gameOver = new Image(new FileInputStream("GameWon.jpg"));
			ImageView GameOverView = new ImageView(gameOver);
			GameOverView.setFitHeight(1080);
			GameOverView.setFitWidth(1920); 
			GameOverView.setPreserveRatio(true);
			
			Group root = new Group(GameOverView);
			
			Button exit = new Button("Quit");
			root.getChildren().add(exit);
			exit.setPrefSize(300,40);
			exit.setOnAction(e-> s.close());
			Scene z= new Scene(root, 1080, 1920);
			s.setScene(z);
			s.setFullScreen(true);
			 
		}
		catch (FileNotFoundException e) {
			System.out.print("NotReached");
		}
	    
		
		
	}
}

