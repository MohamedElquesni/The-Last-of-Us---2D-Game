package views;

import java.awt.Point;





import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.javafx.geom.Rectangle;




















import engine.Game;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class StartedGame {
    static Label heroAttributes = new Label("");
	static GridPane GridMap = new GridPane();
	static Label Label1= new Label("");
	static Label Label2= new Label("");
	static Label Label3= new Label("");
	static Label Label4= new Label("");
	static Label Label5= new Label("");
	static Label Label6= new Label("");
	
	public static Scene CreateMap(Stage s,  Hero h ) {
		Game.startGame(h);
		Vaccine Vaccine1=new Vaccine();
		Vaccine1.pickUp(h);
		Supply supply1=new Supply();
		supply1.pickUp(h);
		s.setTitle("map");
		Image image;
		try {
			image = new Image( new FileInputStream("MainGameMap.jpg"));
			 ImageView imageView = new ImageView(image);
			    imageView.setImage(image);
			    imageView.setFitWidth(1920);
			    imageView.setFitHeight(1080);
			    imageView.setPreserveRatio(true);
			    Group root = new Group(imageView);
		        GridMap.setVgap(3);
		        GridMap.setHgap(3);
				GridMap.setPadding(new Insets(5));
				GridMap.setGridLinesVisible(false);
				for(int i=0; i<15; i++){
					RowConstraints row1 = new RowConstraints(60);
					GridMap.getRowConstraints().add(row1);
				}
				for(int i=0; i<2; i++){
					RowConstraints row = new RowConstraints(70);
					GridMap.getRowConstraints().add(row);
				}
				
				for(int i=0; i<15; i++){
					ColumnConstraints column = new ColumnConstraints(125.4);
					GridMap.getColumnConstraints().add(column);
				}
				
				GridMap.add(heroAttributes,0,15);
				getNodeFromGridPane(GridMap, 0,15).setStyle("-fx-font-fill: black");
				
				Button b= new Button();
				b.setPrefSize(70, 125.4);
				
				for(int i=0; i<15; i++){
					for(int j=0; j<15; j++){
						b= new Button();
						b.setStyle("-fx-background-color: black");
						GridMap.add(b,i,j);
						b.setPrefSize(125.4, 70);
					}
				}
				SelectHero(GridMap,s);
				
				
				GridMap.autosize();
				UpdateMap(GridMap,s);
				root.getChildren().add(GridMap);
				Scene scene = new Scene(root, 1920,1080);
				s.setFullScreen(true);
				s.setScene(scene);
				s.setFullScreen(true);
				s.show();
				return scene;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return new Scene(GridMap); 
	}
	public static void UpdateMap(GridPane gridmap, Stage s)
	{
		Image Zombie,Fighter,Medic,Explorer,Vaccine,Supply,Default;
		try {
			Zombie = new Image(new FileInputStream("ZombieIcon.jpg"));
			ImageView zombieView = new ImageView(Zombie);
		    zombieView.setFitHeight(50);
		    zombieView.setFitWidth(50); 
		    zombieView.setPreserveRatio(true);
		    
		    Fighter = new Image(new FileInputStream("Fighter.jpg"));
			ImageView FighterView = new ImageView(Fighter);
			FighterView.setFitHeight(50);
			FighterView.setFitWidth(50); 
			FighterView.setPreserveRatio(true);

		    Medic = new Image(new FileInputStream("Medic.jpg"));
			ImageView MedicView = new ImageView(Medic);
			MedicView.setFitHeight(50);
			MedicView.setFitWidth(50); 
			MedicView.setPreserveRatio(true);
			
			Explorer = new Image(new FileInputStream("Explorer.jpg"));
			ImageView ExplorerView = new ImageView(Explorer);
			ExplorerView.setFitHeight(50);
			ExplorerView.setFitWidth(50); 
			ExplorerView.setPreserveRatio(true);
			
			Supply = new Image(new FileInputStream("Supply.jpg"));
			ImageView SupplyView = new ImageView(Supply);
			SupplyView.setFitHeight(50);
			SupplyView.setFitWidth(50); 
			SupplyView.setPreserveRatio(true);
			
			Vaccine = new Image(new FileInputStream("Vaccine.jpg"));
			ImageView VaccineView = new ImageView(Vaccine);
			VaccineView.setFitHeight(50);
			VaccineView.setFitWidth(50); 
			VaccineView.setPreserveRatio(true);
			
			Default = new Image(new FileInputStream("Default.jpg"));
			ImageView DefaultView = new ImageView(Default);
			DefaultView.setFitHeight(50);
			DefaultView.setFitWidth(50); 
			DefaultView.setPreserveRatio(true);
		   
		    SelectHero(gridmap,s);
		    Button b= new Button();
			b.setPrefSize(70, 125.4);
			
			for(int x=0;x!=15;x++)
			{
				for(int y=0;y!=15;y++)
				{
					model.world.Cell mapcell = Game.map[x][y];
					if(mapcell.isVisible())
					{
						if(mapcell instanceof CharacterCell)
						{
							
						
							if(((CharacterCell) mapcell).getCharacter() instanceof Medic){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(MedicView);
							}
							if(((CharacterCell) mapcell).getCharacter() instanceof Explorer ){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(ExplorerView);
							}
							if(((CharacterCell) mapcell).getCharacter() instanceof Fighter){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(FighterView);
							}
							if(((CharacterCell) mapcell).getCharacter() instanceof Zombie){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(zombieView);
							}
							if(((CharacterCell) mapcell).getCharacter() ==null)
							{
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(DefaultView);
							}	
						}
						else if(mapcell instanceof CollectibleCell)
						{
							if(((CollectibleCell) mapcell).getCollectible() instanceof Vaccine){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(VaccineView);
							}
							if(((CollectibleCell) mapcell).getCollectible() instanceof Supply){
								((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(SupplyView);
							}
						
					    }
						
							
						
					}
					
				
				else
				{
					((Button) getNodeFromGridPane(gridmap, x, 14-y)).setGraphic(DefaultView);
				}
					
					
				}
			}
			UpdateAvailableHeroes(gridmap);
			
			 
		}
		catch (FileNotFoundException e) {
			System.out.print("NotReached");
			AttackAndCure.DisplayAlertBox(e,s);
		}
	    
		
	
		
		
	}
	public static Node getNodeFromGridPane(GridPane gridmap, int col, int row) 
	{
		for (Node node : gridmap.getChildren()) 
		{
		    if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
		    {
		        return node;
		    }
		}
		return null;
	}
	public static void DesignUnderGrid(GridPane g, Hero h){
		Font under = Font.font("Impact" , FontWeight.BOLD,14);
		heroAttributes.setStyle("-fx-text-fill :white ");
		heroAttributes.setFont(under);
		String s= "Type:";
		if(h instanceof Fighter){
			s+= "Fighter";
		}
		if(h instanceof Explorer){
			s+= "Explorer";
		}
		if(h instanceof Medic){
			s+= "Medic";
		}
		
		String sLabel= h.getName() +"\n"+ s+ "\n" + "Hp:"+ h.getCurrentHp()+ "\n"+ "Actions:" + h.getActionsAvailable()+ "\n"+ "AttackDMG:"+ h.getAttackDmg()+"\n";
		if(h.getSupplyInventory().size()>0)
		{
			sLabel+= "Supply:"+ h.getSupplyInventory().size() +"\n";
		}
		if(h.getVaccineInventory().size()>=0)
		{
			sLabel+= "Vaccine:"+ h.getVaccineInventory().size();
		}
		heroAttributes.setText(sLabel);  
		heroAttributes.setPrefWidth(200);
		
		GridPane.setColumnSpan(heroAttributes,2); 
		GridPane.setRowSpan(heroAttributes,2); 
		//heroAttributes.setStyle("-fx-font-fill: white");
		heroAttributes.setWrapText(true);
	}
	 public static void SelectHero(GridPane g, Stage s) //lesa hatdos 3al hero  
	 {
		 Button setTarget = CreateButton("Set Target");
		 Button useSpecial = CreateButton("Use Special");
		 Button EndTurn = CreateButton("End Turn");
		 g.add(EndTurn,14 ,16);
		 Button Attack = CreateButton("Attack");
		// Attack.setStyle(value);
		 Button Cure = CreateButton("Cure");
		 
		 for(int i=0; i<15; i++){
			 for(int j=0; j<15; j++)
			 {
				if(Game.map[i][14-j] instanceof CharacterCell)
				{
					if(((CharacterCell) Game.map[i][14-j]).getCharacter() instanceof Hero)
					{
						
						((Button) getNodeFromGridPane(g,i,j)).setOnAction(e-> {
							Hero h= (Hero) ChosenCharacter(g, (Button) e.getTarget());   //got the instance of the hero selected
							DesignUnderGrid(g,h);     //display the data 
							checkKey(g,h,s);
							
							g.add(setTarget, 3, 15);  // add buttons
							g.add(useSpecial, 3, 16);
							//when setTarget selected 
							setTarget.setOnAction(v ->  
							{
								ChoosingTarget(h,g);
								g.add(Attack,4 , 15);
								g.add(Cure ,4 , 16);
								
							
							});
							useSpecial.setOnAction(v -> 
							{
								try {
									applySpecialAction(h,g,s);
									
								} catch (Exception e1) {
									AttackAndCure.DisplayAlertBox(e1,s);
								}
								DesignUnderGrid(g,h);   
							});
							
							Attack.setOnAction(c-> AttackAndCure.Attack(h,g,s));
							Cure.setOnAction(c-> AttackAndCure.Cure(h,g,s));
							DesignUnderGrid(g,h); 
							
						});
					 EndTurn.setOnAction(c-> EndTurn(g,s));
					}
			 }
		 }
		 }
		 }
	 
	 
	public static Character ChosenCharacter(GridPane g ,Button b){
		int x= GridPane.getColumnIndex(b);
		int y= GridPane.getRowIndex(b);
		Hero h= (Hero) ((CharacterCell) Game.map[x][14-y]).getCharacter();
		return h;
	}
	public static void ChoosingTarget(Hero h, GridPane g){
		for(int i=0; i<15; i++){
			for(int j=0 ; j<15; j++){
				//if(Game.map[i][14-j] instanceof CharacterCell){
					((Button) getNodeFromGridPane(g,i,j)).setOnAction(e-> {
						SettingTarget(h, (Button)e.getTarget(), g);
					});
					//}
					
				}
			}
		DesignUnderGrid(g,h); 
		}
	public static void checkKey(GridPane g, Hero h, Stage s)
	{
		g.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent k){
				int intialHp=h.getCurrentHp();
				if (k.getText().equals("s")){
					try {
						h.move(Direction.LEFT);
					} catch (MovementException | NotEnoughActionsException e) {
						AttackAndCure.DisplayAlertBox(e,s);
					}
				}
				if (k.getText().equals("d")){
					try {
						
						h.move(Direction.UP);
					} 
					catch (MovementException | NotEnoughActionsException e) {
						AttackAndCure.DisplayAlertBox(e,s);
					}
				}
				if (k.getText().equals("a")){
					try {
						h.move(Direction.DOWN);
					} catch (MovementException | NotEnoughActionsException e) {
						AttackAndCure.DisplayAlertBox(e,s);
				}
				}
				if (k.getText().equals("w")){
					try {
						h.move(Direction.RIGHT);
					} catch (MovementException | NotEnoughActionsException e) {
						AttackAndCure.DisplayAlertBox(e,s);
					}
				}
				if(h.getCurrentHp()<intialHp)
				{
					Stage alert = new Stage();
					
					Timeline timeline = new Timeline(
			                new KeyFrame(Duration.ZERO, event -> {
			                	
								alert.setAlwaysOnTop(true);
								alert.initModality( Modality.NONE );
								alert.initOwner(s);
								alert.setWidth(400);
								alert.setHeight(150);
								
								Label message = new Label("You have been Damaged by a TrapCell!");

					            alert.setTitle("Invalid Target Exception");
					            StackPane root = new StackPane(message);

					            // center the label
					            root.setAlignment(Pos.CENTER);

					            VBox l = new VBox();
								l.setAlignment(Pos.CENTER);
								l.getChildren().add(message);
								Scene scene= new Scene(l);
								alert.setScene(scene);
								alert.show();
			                }),
			                new KeyFrame(Duration.seconds(2), event -> {
			                    
			                    alert.close();
			                })
			        );

			        // play the timeline
			        timeline.play();
				}
				
				DesignUnderGrid(g,h);
				UpdateMap(g,s);
				AttackAndCure.Checkwin(s);
			
			}
		});
	}
	public static void applySpecialAction(Hero h, GridPane g, Stage s) throws NoAvailableResourcesException, InvalidTargetException,FileNotFoundException
	{
		try {
			
			
			
		h.useSpecial();
		UpdateMap(g,s);
		DesignUnderGrid(g,h);
		UpdateAvailableHeroes(g);
		}
		catch (GameActionException e) 
		{
			AttackAndCure.DisplayAlertBox(e,s);
			
		}
	    
	}
		
	
	public static void SettingTarget(Hero h, Button b, GridPane g){
		int x= GridPane.getColumnIndex(b);
		int y= GridPane.getRowIndex(b);
		Character Target =((CharacterCell) Game.map[x][14-y]).getCharacter();
		h.setTarget(Target);
		DesignUnderGrid(g,h); 
	}
	public static Button CreateButton(String s)
	{
		Button b = new Button(s);
		//b.setShape(new r(100));
		
		return b;
	}	 
	public static void EndTurn(GridPane g, Stage s){
		try {
			Game.endTurn();
			heroAttributes.setText("");
			StartedGame.UpdateMap(g,s);
			AttackAndCure.GameOver(s);
		} catch (NotEnoughActionsException e) {
			AttackAndCure.DisplayAlertBox(e,s);
			
		} catch (InvalidTargetException e) {
			AttackAndCure.DisplayAlertBox(e,s);
		}
		
	}
	public static void UpdateAvailableHeroes(GridPane g){
		Font under = Font.font("Impact" , FontWeight.BOLD,14);
		 if(Game.heroes.size()==1){
		 if(Game.heroes.get(0)!=null){
			    Hero h= Game.heroes.get(0);
			    
			 	Label1.setStyle("-fx-text-fill :red ");
				Label1.setFont(under);
				Label1.setText(showHeroDetails(h));  
				Label1.setPrefWidth(200);
			
				
				//GridPane.setColumnSpan(heroAttributes,2); 
				GridPane.setRowSpan(Label1,2); 
				//heroAttributes.setStyle("-fx-font-fill: white");
				Label1.setWrapText(true);
				g.add(Label1, 7,15);
		 }
		 }
		 if(Game.heroes.size()==2){
		 if(Game.heroes.get(1)!=null){
			 Hero h= Game.heroes.get(1);
				
				Label2.setStyle("-fx-text-fill :white ");
				Label2.setFont(under);
				Label2.setText(showHeroDetails(h));
				Label2.setPrefWidth(200);
				
				//GridPane.setColumnSpan(heroAttributes,2); 
				GridPane.setRowSpan(Label2,2); 
				//heroAttributes.setStyle("-fx-font-fill: white");
				Label2.setWrapText(true);
				g.add(Label2, 8,15);
		 }
		 }
		 if(Game.heroes.size()==3){
		 if(Game.heroes.get(2)!=null){
			 Hero h= Game.heroes.get(2);
				
				
				Label3.setStyle("-fx-text-fill :white ");
				Label3.setFont(under);
				Label3.setText(showHeroDetails(h)); 
				Label3.setPrefWidth(200);
				
				//GridPane.setColumnSpan(heroAttributes,2); 
				GridPane.setRowSpan(Label3,2); 
				//heroAttributes.setStyle("-fx-font-fill: white");
				Label3.setWrapText(true);
				g.add(Label3, 9,15);
				}
		 }
	    if(Game.heroes.size()==4){
		 if(Game.heroes.get(3)!=null){
			 Hero h= Game.heroes.get(3);
				
				Label4.setStyle("-fx-text-fill :white ");
				Label4.setFont(under);
				Label4.setText(showHeroDetails(h)); 
				Label4.setPrefWidth(200);
				
				//GridPane.setColumnSpan(heroAttributes,2); 
				GridPane.setRowSpan(Label1,2); 
				//heroAttributes.setStyle("-fx-font-fill: white");
				Label4.setWrapText(true);
				g.add(Label4, 10,15);
		 }
	    }
	    if(Game.heroes.size()==5){
	   	 if(Game.heroes.get(4)!=null){
				 Hero h= Game.heroes.get(4);
					
					
					Label5.setStyle("-fx-text-fill :white ");
					Label5.setFont(under);
					Label5.setText(showHeroDetails(h)); 
					Label5.setPrefWidth(200);
					
					//GridPane.setColumnSpan(heroAttributes,2); 
					GridPane.setRowSpan(Label5,2); 
					//heroAttributes.setStyle("-fx-font-fill: white");
					Label5.setWrapText(true);
					g.add(Label5, 11,15);
			 }
	    }
	    if(Game.heroes.size()==6){
	   	 if(Game.heroes.get(5)!=null){
				 Hero h= Game.heroes.get(5);
				 
					
					Label6.setStyle("-fx-text-fill :white ");
					Label6.setFont(under);
					Label6.setText(showHeroDetails(h)); 
					Label6.setPrefWidth(200);
					
					//GridPane.setColumnSpan(heroAttributes,2); 
					GridPane.setRowSpan(Label6,2); 
					//heroAttributes.setStyle("-fx-font-fill: white");
					Label6.setWrapText(true);
					g.add(Label6, 12,15);
			 }
	    }
		 
	}
	    public static String showHeroDetails(Hero h){
	    Font UpperDisplay = Font.font("Courier New" , FontWeight.BOLD,16);
		String s= "Type:";
		if(h instanceof Fighter){
			s+= "Fighter";
		}
		if(h instanceof Explorer){
			s+= "Explorer";
		}
		if(h instanceof Medic){
			s+= "Medic";
		}
		
		String sLabel= h.getName() +"\n"+ s+ "\n" + "Hp:"+ h.getCurrentHp()+ "\n"+ "Actions:" + h.getActionsAvailable()+ "\n"+ "AttackDMG:"+ h.getAttackDmg();
		return sLabel;
		
	}
	
	        
	    private static void showPopup(Label messageLabel) {

	        // create a timeline to show and hide the popup
	        Timeline timeline = new Timeline(
	                new KeyFrame(Duration.ZERO, event -> {
	                    // show the popup
	                    messageLabel.setVisible(true);
	                    messageLabel.setOpacity(1);
	                }),
	                new KeyFrame(Duration.seconds(3), event -> {
	                    // hide the popup
	                    messageLabel.setVisible(false);
	                    messageLabel.setOpacity(0);
	                })
	        );

	        // play the timeline
	        timeline.play();

	    }
	
}

