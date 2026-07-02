 package views;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;



import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import engine.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class launch extends Application
{
	
	public static void main(String [] args)
	{
		launch(args);
	}
	public void start(Stage theStage) 
	{
		Image image;
		try {
			image = new Image( new FileInputStream("HomeIntro.jpg"));
			//Creating the image view
		     ImageView imageView = new ImageView(image);
		     //Setting image to the image view
		     imageView.setImage(image);
		     //Setting the image view parameters
		     //imageView.setX(645);
		    // imageView.setY(10);
		     imageView.setFitWidth(1920);
		     imageView.setFitHeight(1080);
		     imageView.setPreserveRatio(true);
		     //Setting the Scene object
		     Group root = new Group(imageView);
	         Scene scene = new Scene(root, 595, 370);
	         theStage.setTitle("The Last of Us");
	         theStage.setScene(scene);
	         Button StartButton=new Button("Single Play");
	         StartButton.setStyle("-fx-text-fill :white");
	         Font FirstPageButtons = Font.font("Courier New" , FontWeight.BOLD,34);
	         StartButton.setTranslateX(75);
	         StartButton.setTranslateY(100);
	         StartButton.setPrefSize(300,40);
	         StartButton.setBackground(Background.EMPTY);
	         StartButton.setFont(FirstPageButtons);
	         StartButton.setOnAction(e -> {
	        	 try {
					Game.loadHeroes("Heroes.csv");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.print("Heroes file not Found");
				}
	        	 try {
					singlePlay(theStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         });
	         Button ExitButton = new Button("Quit");
	         ExitButton.setBackground(Background.EMPTY);
	         ExitButton.setFont(FirstPageButtons);
	         ExitButton.setStyle("-fx-text-fill :white ;");
	         ExitButton.setPrefSize(300,150);
	         ExitButton.setTranslateX(75);
	         ExitButton.setTranslateY(800);
	         ExitButton.setOnAction(e -> theStage.close());
	         Button multiButton = new Button("Multiplayer");
	         multiButton.setBackground(Background.EMPTY);
	         multiButton.setFont(FirstPageButtons);
	         multiButton.setStyle("-fx-text-fill :white ;");
	         multiButton.setPrefSize(300,40);
	         multiButton.setTranslateX(75);
	         multiButton.setTranslateY(150);
	         root.getChildren().addAll(StartButton, ExitButton, multiButton);
	        
	         theStage.setFullScreen(true);
	         theStage.show();
		
		} catch (FileNotFoundException e2) {
			AttackAndCure.DisplayAlertBox(e2, theStage);
		}
	}     
	public void singlePlay(Stage s) throws FileNotFoundException
	{

		s.setTitle("Heroes");
	
		GridPane heroesGrid = new GridPane();
		heroesGrid.setGridLinesVisible(false);
		heroesGrid.setHgap(40);
		heroesGrid.setVgap(10);
		
		heroesGrid.setPadding(new Insets(10));
		Font Header = Font.font("Courier New" , FontWeight.EXTRA_BOLD,26);
		Label chooseHeader=new Label(" Select your starting Hero ! ");
		chooseHeader.setFont(Header);
		chooseHeader.setStyle("-fx-text-fill: white");
		Image background =new Image(new FileInputStream("SelectHeroes.jpg"));
		ImageView view0 = new ImageView(background);
		view0.setFitHeight(1080);
	    view0.setFitWidth(1920); 
	    view0.setPreserveRatio(true);
		Group root = new Group(view0);
		
		heroesGrid.add(chooseHeader, 0, 0);
		Button Character1= new Button();
		Image joel =new Image(new FileInputStream("Joel.jpg"));
	    ImageView view1 = new ImageView(joel);
	    view1.setFitHeight(200);
	    view1.setFitWidth(450); 
	    view1.setPreserveRatio(true);
	    Image Ellie =new Image(new FileInputStream("Ellie.jpg"));
	    ImageView view2 = new ImageView(Ellie);
	    view2.setFitHeight(200);
	    view2.setFitWidth(400); 
	    view2.setPreserveRatio(true);
	    Image Tess =new Image(new FileInputStream("Tess.jpeg"));
	    ImageView view3 = new ImageView(Tess);
	    view3.setFitHeight(250);
	    view3.setFitWidth(450); 
	    view3.setPreserveRatio(true);
	    Image Riley =new Image(new FileInputStream("Riley.jpg"));
	    ImageView view4 = new ImageView(Riley);
	    view4.setFitHeight(200);
	    view4.setFitWidth(450); 
	    view4.setPreserveRatio(true);
	    Image Tommy =new Image(new FileInputStream("Tommy.jpg"));
	    ImageView view5 = new ImageView(Tommy);
	    view5.setFitHeight(200);
	    view5.setFitWidth(450); 
	    view5.setPreserveRatio(true);
	    Image Bill =new Image(new FileInputStream("Bill.jpg"));
	    ImageView view6 = new ImageView(Bill);
	    view6.setFitHeight(200);
	    view6.setFitWidth(450); 
	    view6.setPreserveRatio(true);
	    Image Henry =new Image(new FileInputStream("Henry.jpeg"));
	    ImageView view8 = new ImageView(Henry);
	    view8.setFitHeight(200);
	    view8.setFitWidth(450); 
	    view8.setPreserveRatio(true);
	    Image David =new Image(new FileInputStream("David.jpg"));
	    ImageView view7 = new ImageView(David);
	    view7.setFitHeight(200);
	    view7.setFitWidth(450); 
	    view7.setPreserveRatio(true);
		Button Character2= new Button();
		Button Character3 = new Button();
		Button Character4=new Button();
		Button Character5= new Button();
		Button Character6 =new Button();
		Button Character7 =new Button();
		Button Character8= new Button();
		Character1.setGraphic(view1);
	 	Character2.setGraphic(view2);
		Character3.setGraphic(view3);
		Character4.setGraphic(view4);
		Character5.setGraphic(view5);
		Character6.setGraphic(view6);
		Character7.setGraphic(view7);
	    Character8.setGraphic(view8);
		//Character2.setPrefSize(200,100);
		heroesGrid.add(Character1, 0, 1);
		heroesGrid.add((showData(1)), 1, 1);
		//Character2.setPrefSize(455,255);
		heroesGrid.add(Character2, 0, 2);
		heroesGrid.add(showData(2), 1, 2);
		Character3.setPrefSize(250,255);
		heroesGrid.add(Character3, 0, 3);
		heroesGrid.add((showData(3)), 1, 3);
		//Character4.setPrefSize(455,255);
		heroesGrid.add(Character4, 0, 4);
		heroesGrid.add((showData(4)), 1, 4);
		//Character5.setPrefSize(455,255);
		heroesGrid.add(Character5, 2, 1);
		heroesGrid.add((showData(5)), 3, 1);
		//Character6.setPrefSize(455,255);
		heroesGrid.add(Character6, 2, 2);
		heroesGrid.add((showData(6)), 3, 2);
		//Character7.setPrefSize(455,255);
		heroesGrid.add(Character7, 2, 3);
		heroesGrid.add((showData(7)), 3, 3);
		//Character8.setPrefSize(455,255);
		heroesGrid.add(Character8, 2, 4);
		heroesGrid.add((showData(8)), 3, 4);
		RowConstraints row = new RowConstraints(100);
		heroesGrid.getRowConstraints().add(row);
		for(int i=1; i<4; i++)
		{
			RowConstraints row1 = new RowConstraints(225);
			heroesGrid.getRowConstraints().add(row1);
		}
		
		for(int i=0; i<4; i++){
			ColumnConstraints column = new ColumnConstraints(472);
			heroesGrid.getColumnConstraints().add(column);
		}
		Character1.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(0)));
		Character2.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(1)));
		Character3.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(2)));
		Character4.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(3)));
		Character5.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(4)));
		Character6.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(5)));
		Character7.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(6)));
		Character8.setOnAction(e-> StartedGame.CreateMap(s, Game.availableHeroes.get(7)));
		heroesGrid.autosize();
		root.getChildren().add(heroesGrid);
		Scene sceneHeroes= new Scene(root, 1920,1080);
		s.setScene(sceneHeroes);
		s.setFullScreen(true);
		
	    
		s.show();
	}
	
public Label showData(int i){
		
		int x=i-1;
		String data= "";
		if(Game.availableHeroes.get(x) instanceof Fighter)
		{
		  data= "Name: " + Game.availableHeroes.get(x).getName()+ "\n"+ "Type: Fighter" +"\n"+"MaxHp: "+ Game.availableHeroes.get(x).getMaxHp()+ "\n"+ "AttackDmg: " +Game.availableHeroes.get(x).getAttackDmg()+ "\n" +"Action Points: " +Game.availableHeroes.get(x).getActionsAvailable();
		}
		if(Game.availableHeroes.get(x)instanceof Explorer)
		{
		   data= "Name: " + Game.availableHeroes.get(x).getName()+ "\n"+ "Type: Explorer" + "\n" +"MaxHp: "+ Game.availableHeroes.get(x).getMaxHp()+ "\n"+ "AttackDmg: " +Game.availableHeroes.get(x).getAttackDmg()+ "\n" +"Action Points: " +Game.availableHeroes.get(x).getActionsAvailable();
		}
		if(Game.availableHeroes.get(x) instanceof Medic){
			data= "Name: " + Game.availableHeroes.get(x).getName()+ "\n"+ "Type: Medic"+ "\n" +"MaxHp: "+ Game.availableHeroes.get(x).getMaxHp()+ "\n"+ "AttackDmg: " +Game.availableHeroes.get(x).getAttackDmg()+ "\n" +"Action Points: " +Game.availableHeroes.get(x).getActionsAvailable();
		}
		Label text = new Label(data);
		text.setPrefWidth(472);
		text.setPrefHeight(262);
		//text.setTextAlignment(TextAlignment.CENTER);
		text.setPadding(new Insets(20));
		Font HeroData = Font.font("Courier New", FontWeight.EXTRA_BOLD,26);
		text.setStyle("-fx-text-fill: white");
		text.setFont(HeroData);
		return text;
	}
   
	
	
}
