package window;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
 
public class Window extends Application {
	
	BorderPane border = new BorderPane();		//Adding new borderPane to organize shit
	NewGame newGame = new NewGame(); 
	static RadioButton horizontal;
	RadioButton vertical;
	private ToggleGroup group;
	private static Stage stageNewGame;

	public static Text text2;
	private static Tile[][] myTiles;
	private static Tile[][] itTiles;
	private static TextArea log;
	
	//Our gridPane
	public Parent addGridPane() {
		GridPane root = new GridPane();
		
		root.setPrefSize(300,600);							//set size of GridPane
		root.setPadding(new Insets(10, 10, 10, 5));			//set padding around	
		//root.setStyle("-fx-background-color: #a9a9a9;");	//Color of background
		root.setAlignment(Pos.TOP_CENTER);					//Position of Grid
		
		Text text1 = new Text("Opponent");					//add new text
		text1.setFont(new Font("Agency FB", 16));			//set font and size
		text1.setTranslateX(-100);							//position X-axis
		text1.setVisible(true);								//set visible
		
		text2 = new Text("plyrName");						//add new text
		text2.setFont(new Font("Agency FB", 16));			//set font and size
		text2.setTranslateX(-100);							//position X-axis
		text2.setTranslateY(240);							//position Y-axis
		text2.setVisible(true);								//set visible
		
		createBoard1(root, -100, 20);						//Creates board1
		createBoard2(root, -100, 260);						//Creates board2
		
		//add to GridPane
		root.add(text1, 0, 0);								
		root.add(text2, 0, 0);		
		
		return root;
	}

	
	
	/*Constructor should replace this:
	public static void main(String[] args) {
		
		//TEMPORARY
		myMatrix = new Matrix(10,10);
		myMatrix.putHull(2);
		
		itMatrix = new Matrix(10,10);
		itMatrix.putHull(3);
		
		launch(Window.class, args);
		System.out.println(javafx.scene.text.Font.getFamilies());			//Gets what fonts available
		}*/
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		border.setCenter(addGridPane());
		border.setLeft(addVBoxLeft());				//Adding vBox to the left
		border.setRight(addVBoxRight());			//Adding vBox to right 
		border.setTop(addMenuBar());				//Adding menuBar to top
		
		//Makes everything visible
		Scene scene = new Scene(border);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BattleShip");
        primaryStage.show();
	}
	
	
	
	//Creating an VBox (VBox arranges a series of nodes in a single column)
	public VBox addVBoxLeft() {
		
		Text text = new Text("Log: ");
		text.setFont(new Font("Agency FB", 20));				//set font and size of text
		
		log = new TextArea();
		log.setEditable(false);									//Log not editable
	    
		VBox vBoxLeft = new VBox(text,log);						//Putting text and log into the VBox
	    vBoxLeft.setPadding(new Insets(15, 15, 15, 15));		//Padding around the vBox
	    vBoxLeft.setPrefSize(200, 600);							//Preferred size
	    vBoxLeft.setSpacing(1);									//Adding space between stuff
	    vBoxLeft.setStyle("-fx-background-color: #f0f8ff;");	//Set background color
	    
	    return vBoxLeft;
	}
	
	
	
	/*//Creating FlowPane (In FlowPane the nodes are laid out consecutively and wrap at the boundary set for the pane.)
	public FlowPane addFlowPane() {
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(15, 15, 15, 15));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(170); // preferred width allows for two columns
		flow.setStyle("-fx-background-color: DAE6F3;");

		ImageView pages[] = new ImageView[5];
		for (int i=0; i<5; i++) {
			//i = subHeading[i];
			pages[i] = new ImageView(
						new Image(Window.class.getResourceAsStream(
								"Boat"+(i+1)+".gif")));
			flow.getChildren().add(pages[i]);
		}

		return flow;
	}*/
	
	public VBox addVBoxRight() {
		
		Text ships = new Text("Ships: ");							//Set heading for ships
		ships.setFont(new Font("Agency FB", 20));					//Set font and size for heading
		
		//Should be an easier way to add lots of subHeadings, but this works
		Text subHeading1 = new Text("Aircraft Carrier (5 tiles) ");	//Create subHeadings for ships
	    Text subHeading2 = new Text("Battleship (4 tiles) ");
	    Text subHeading3 = new Text("Submarine (3 tiles) ");
	    Text subHeading4 = new Text("Destroyer (3 tiles) ");
	    Text subHeading5 = new Text("Torpedo Boat (2 tiles) ");
	    Text subHeading6 = new Text("Choose direction: ");
	    
	    subHeading1.setFont(new Font("Agency FB", 14));				//Set font and size for subHeadings
		subHeading2.setFont(new Font("Agency FB", 14));
		subHeading3.setFont(new Font("Agency FB", 14));
		subHeading4.setFont(new Font("Agency FB", 14));
		subHeading5.setFont(new Font("Agency FB", 14));
		subHeading6.setFont(new Font("Agency FB", 14));
		subHeading6.setTranslateY(20);
		
	    
	    //Should be an easier way to add lots of pics, but this works
	    Image aircraftCarrier = new Image("AircraftCarrier.gif", 115, 115, true, true);		//Create image (inputStream, requestedWidth, requestedHeight, preserveRatio, smooth)
	    Image battleship = new Image("Battleship.gif", 100, 100, true, false);
	    Image submarine = new Image("Submarine.gif", 85, 85, true, false);
	    Image destroyer = new Image("Destroyer.gif", 85, 85, true, false);
	    Image torpedoBoat = new Image("TorpedoBoat.gif", 70, 70, true, false);
	    
	    group = new ToggleGroup();
	    horizontal = new RadioButton("Horizontal");
		horizontal.setToggleGroup(group);			//Set to toggle group
		horizontal.setSelected(true);				//Set autoSelected
		horizontal.setTranslateX(0);				//Position of RadioButton X-axis
		horizontal.setTranslateY(20);				//Position of RadioButton Y-axis
		
		vertical = new RadioButton("Vertical");
		vertical.setToggleGroup(group);			//Set to toggle group
		vertical.setTranslateX(0);				//Position of RadioButton X-axis
		vertical.setTranslateY(20);				//Position of RadioButton Y-axis
		
		Button returnNewGame = new Button("Done placing");
		returnNewGame.setPrefSize(90, 20);				//set size of Button
		returnNewGame.setTranslateX(0);
		returnNewGame.setTranslateY(30);
		
		
	    VBox vBoxRight = new VBox(ships, subHeading1, new ImageView(aircraftCarrier), 		//Putting heading, subHeadings and images into the vBox
	    								 subHeading2, new ImageView(battleship), 
	    								 subHeading3, new ImageView(submarine), 
	    								 subHeading4, new ImageView(destroyer), 
	    								 subHeading5, new ImageView(torpedoBoat),
	    								 subHeading6, horizontal, vertical, returnNewGame);
	    
	    
	    
	    vBoxRight.setPadding(new Insets(15, 15, 15, 15));				//Padding around the vBox
	    vBoxRight.setPrefSize(200, 600);								//Preferred size
	    vBoxRight.setSpacing(5);										//Adding space between stuff
	    vBoxRight.setStyle("-fx-background-color: #f0f8ff;");			//Set background color
	    
	    //////////////////////////
	    //	Action for buttons	//
	    //////////////////////////
	    
	    //Radiobuttons
	    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	    @Override
	    public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
	    	 if (newToggle.isSelected()) {
	    		 handleRadioButtons();
	    	 } else {
            	 handleRadioButtons();
             }   
	        }
	    });
	    
	    //Return to new game
	    
	    /*returnNewGame.setOnAction((event) -> {
		    //try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("NewWindow.fxml"));
		         
		         // if "fx:controller" is not set in fxml
		         // fxmlLoader.setController(NewWindowController);
		         
		        Scene scene = new Scene(newGame.load(), 600, 400);
		        Stage stage = new Stage();
		        stage.setTitle("New Game");
		        stage.setScene(scene);
		        stage.show();
		    //} catch (IOException e) {
		        //Logger logger = Logger.getLogger(getClass().getName());
		        //logger.log(Level.SEVERE, "Failed to create new Window.", e);
		    //}
		});*/
		
	    
	    return vBoxRight;
	}
	
	
	
	private void createBoard1(GridPane root, int xOffSet, int yOffSet) {

		itTiles = new Tile[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				itTiles[i][j] = new Tile(i, j);

				itTiles[i][j].setTranslateX(j * 20 + xOffSet);
				itTiles[i][j].setTranslateY(i * 20 + yOffSet);

				root.getChildren().add(itTiles[i][j]);
			}
		}
	}


	
	private void createBoard2(GridPane root, int xOffSet, int yOffSet) {
		myTiles = new Tile[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				myTiles[i][j] = new Tile(i, j);

				myTiles[i][j].setTranslateX(j * 20 + xOffSet);
				myTiles[i][j].setTranslateY(i * 20 + yOffSet);

				root.getChildren().add(myTiles[i][j]);
			}
		}
	}

	
	
	//Creating MenuBar
	public BorderPane addMenuBar() {
		
		BorderPane pane = new BorderPane();
		MenuBar menu = new MenuBar();
		
		//File
		Menu file = new Menu("_File");					//Creates heading "File"
		MenuItem itmNew = new MenuItem("New Game");		//Creates subHeading "New"
		MenuItem itmOpen = new MenuItem("Open...");		//Creates subHeading "Open"
		MenuItem itmSave = new MenuItem("Save");		//Creates subHeading "Save"
		MenuItem itmQuit = new MenuItem("Quit");		//Creates subHeading "Quit"
		
		
		//Settings
		Menu settings = new Menu("_Settings");						//Creates heading "Settings"
		CheckMenuItem itmSound = new CheckMenuItem("Sound");		//Creates checkMenu "Sound"
		CheckMenuItem itmWhatever = new CheckMenuItem("Whatever");	//Creates checkMenu "Whatever"
		itmSound.setSelected(true);
		

		//Help
		Menu help = new Menu("Help");				//Creates heading "Help"		
		MenuItem itmHelp = new MenuItem("Help");	//Creates subHeading "Help"
		
		//Add items to each menu
		file.getItems().addAll(itmNew, itmOpen, itmSave, new SeparatorMenuItem(), itmQuit);
		settings.getItems().addAll(itmSound, new SeparatorMenuItem(), itmWhatever);
		help.getItems().addAll(itmHelp);	
		
		//Add Menus to MenuBar
		menu.getMenus().addAll(file,settings,help);
		pane.setTop(menu);
		
		//Cool stuff
		file.setMnemonicParsing(true);				//Use Alt+F to open 'File' or Alt+S ti open 'Settings'
		
		
		/* 
		 * Could have used CONTROL_DOWN for the code that follows, but if someone is using Mac this would cause problem
		 * so therefore SHORTCUT_DOWN is a better solution. In PC it's CTRL, mac CMND etc...
		 */
		
		itmNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));			//CTRL+N for new game	
		itmOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));		//CTRL+O for open	
		itmSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));		//CTRL+S for save
		itmQuit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));		//CTRL+Q for Exit

		itmSound.setAccelerator(new KeyCodeCombination(KeyCode.M, 
													   KeyCombination.SHIFT_DOWN, 
													   KeyCombination.SHORTCUT_DOWN));					//CTRL+SHIFT+M for selecting sound on/off
		
		itmHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN));		//CTRL+H for help
		
		
		//////////////////////////////////
		//  Some action to 'File'-menu	//
		//////////////////////////////////
		
		//ActionEvent for click on 'New Game'
		itmNew.setOnAction((event) -> {
		    //try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("NewWindow.fxml"));
		        /* 
		         * if "fx:controller" is not set in fxml
		         * fxmlLoader.setController(NewWindowController);
		         */
		        Scene scene = new Scene(newGame.load(), 600, 400);
		        stageNewGame = new Stage();
		        stageNewGame.setTitle("New Game");
		        stageNewGame.setScene(scene);
		        stageNewGame.show();
		   /* } catch (IOException e) {
		        Logger logger = Logger.getLogger(getClass().getName());
		        logger.log(Level.SEVERE, "Failed to create new Window.", e);
		    }*/
		});
		
		
		
		
		//ActionEvent for click on 'Quit'
		itmQuit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("BattleShip");
            	alert.setHeaderText("WARNING!!");
            	alert.setContentText("Are you sure you wanna quit?");
            	
            	ButtonType buttonTypeOK = new ButtonType("Quit");
            	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
          	
            	Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOK){
					controller.Controller.disconnectAny();
					System.exit(0);
				} else {
					alert.close();
				}	
			}
		});
		
		

		//////////////////////////////////////
		//  Some action to 'Settings'-menu	//
		//////////////////////////////////////
		
		//TODO: Finish these if-else-statements:
		itmSound.selectedProperty().addListener(new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					if(itmSound.isSelected()) {
						//Write what happens
					}else {
						//write what happens
					}
				} else {
					//Write what happens
				}
			}
		});
		
		return pane;
	} //addMenuBar() end
	
	

	public static void draw() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				myTiles[i][j].draw(main.Main.myMatrix);
				itTiles[i][j].draw(main.Main.itMatrix);
			}
		}
	}
	
	public static void drawUpper() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				//myTiles[i][j].draw(main.Main.myMatrix);
				itTiles[i][j].draw(main.Main.itMatrix);
			}
		}
	}	
	
	public static void drawLower() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				myTiles[i][j].draw(main.Main.myMatrix);
				//itTiles[i][j].draw(main.Main.itMatrix);
			}
		}
	}	
	
	
	
	public static void appendLog(String string) {
		log.appendText(string+"\n");
	}
	
	
	
	public void stop() throws Exception{
		controller.Controller.disconnectAny();
		System.exit(0);
	}
	
	
	
	public void handleRadioButtons() {
		if(horizontal.isSelected()) {
			main.Main.vertical = false;
		}
		else {
			main.Main.vertical = true;
		}
	}
	
	//TODO:
	/*public static void showNewGame() {
		((Node)stageNewGame).getScene().show();
	}*/
}