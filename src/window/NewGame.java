package window;

import javax.swing.JOptionPane;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewGame extends Application {
	
	RadioButton computer, host, join;
	TextField name, ipAddress;
	static Button startGame;
	Button cancel;
	private static Scene scene;
	
	/*public NewGame() {
		//border.setCenter(load());	
		
	}*/
	
	
	//For testing only
	public static void main(String[] args) {
		launch(NewGame.class, args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane border = new BorderPane();
		border.setCenter(load());
		//Sets everything visible
	    scene = new Scene(border);
        primaryStage.setScene(scene);
        primaryStage.setTitle("New Game");
        primaryStage.show();	
	}
	
	
	
	public Parent load() {
		
		Text text = new Text("Create New Game: ");			//Create text
		text.setFont(new Font("Agency FB", 20));			//set font and size		
		
		Text plyrName = new Text("Player Name: ");			//Create text
		plyrName.setFont(new Font("Agency FB", 14));		//set font and size
		
		Text textSubheading2 = new Text("Choose opponent: ");//Create text
		textSubheading2.setFont(new Font("Agency FB", 14));	//set font and size
		
		Text ip = new Text("IP address: ");					//Create text
		ip.setFont(new Font("Agency FB", 14));				//set font and size
		ip.setTranslateY(45);								//Position of RadioButton Y-axis
		
		name = new TextField();								//Create textField
		name.setFont(new Font("Agency FB", 12));			//set font and size
		name.autosize();									//autoSize textField
		
		ipAddress = new TextField();						//Create textField
		//ipAddress.setVisible(false);						//TODO: actionListener for toggleGroup to know when join is selected and then IPaddress should pop up.
		ipAddress.setFont(new Font("Agency FB", 12));		//set font and size
		ipAddress.setTranslateY(45);						//Position of RadioButton Y-axis
		ipAddress.autosize();								//autoSize textField
		
		GridPane root = new GridPane();
		root.setPrefSize(360,310);							//set size of GridPane
		root.setPadding(new Insets(10, 10, 10, 5));			//set padding around	
		//root.setAlignment(Pos.TOP_LEFT);					//Position of Grid
		
		
		/////////////
		// Buttons //
		/////////////
		
		//Ordinary buttons
		startGame = new Button("Start Game");
		startGame.setPrefSize(90, 20);			//set size of Button
		startGame.setTranslateX(0);				//Position of Button X-axis
		startGame.setTranslateY(110);			//Position of Button Y-axis
		
		cancel = new Button("Cancel");
	    cancel.setPrefSize(90, 20);				//set size of Button
	    cancel.setTranslateX(10);				//Position of Button X-axis
	    cancel.setTranslateY(110);				//Position of Button Y-axis
	    cancel.setCancelButton(true);			//Set button to be a 'Cancel-button'
	    
	    Button rePlaceShips = new Button("Re-place ships");
	    rePlaceShips.setPrefSize(100, 20);				//set size of Button
	    rePlaceShips.setTranslateX(0);				//Position of Button X-axis
	    rePlaceShips.setTranslateY(100);				//Position of Button Y-axis
	    
	    
	    //RadioButtons
	    ToggleGroup group = new ToggleGroup();
	    computer = new RadioButton("Computer");
	    computer.setToggleGroup(group);			//Set to toggle group
	    computer.setSelected(true);				//Set autoSelected
	    computer.setTranslateX(0);				//Position of RadioButton X-axis
	    computer.setTranslateY(20);				//Position of RadioButton Y-axis
	    
	    host = new RadioButton("Host");
	    host.setToggleGroup(group);				//Set to toggle group
	    host.setTranslateX(0);					//Position of RadioButton X-axis
	    host.setTranslateY(25);					//Position of RadioButton Y-axis
		
	    join = new RadioButton("Join");
		join.setToggleGroup(group);				//Set to toggle group
		join.setTranslateX(0);					//Position of RadioButton X-axis
		join.setTranslateY(30);					//Position of RadioButton Y-axis
		
		
		
		/////////////////////////////
		// Some Action for Buttons //
		/////////////////////////////
		
		//'Name'-field
		//you can find this action in handleButtonAction()
		
		//'Re-place ships'-button
	    rePlaceShips.setOnAction(e -> {
	    	main.Main.controller.initializeGame();
	    	main.Main.gameMode = "2";
	    	controller.Controller.waker();
	    	Stage stage = (Stage) rePlaceShips.getScene().getWindow();
	    	stage.hide();
	    });
		
		//'New Game'-button
	    startGame.setOnAction(e-> handleButtonAction(e)); 
            	//System.out.println(selectedRadioButton);
            	

	    
	    //'Cancel'-button
	    cancel.setOnAction(e -> {
	    	Stage stage = (Stage) cancel.getScene().getWindow();
		    stage.close();
	    });
	    
	    //'IP'-field
	    //plyrName.setUserData(Object value);	//maybeShould use this to get the userData? Not sure.
	  		
	    
	    
	    
	    
	    //////////////////////////////////
	    //	Adding stuff to GridPane	//
	    //////////////////////////////////
	    
	    root.add(text, 0, 0);
	    root.add(plyrName, 0, 1);
	    root.add(name, 1, 1);
	    root.add(textSubheading2, 0, 3);
	    root.add(computer, 1, 2);
	    root.add(host, 1, 3);
	    root.add(join, 1, 4);
	    root.add(ip, 0, 6);
	    root.add(ipAddress, 1, 6);
	    root.add(rePlaceShips, 1, 7);
	    root.add(startGame, 1, 8);
	    root.add(cancel, 2, 8);
	    
		return root;
	}

	//For handling RadioButton-choices
	private String handleButtonAction(ActionEvent e) {
		String value;
		if(computer.isSelected()) {
			value = "0";
			main.Main.gameMode = value;
			System.out.println("Computer is selected " + value);
		} else if (host.isSelected()) {
			value = "1";
			main.Main.gameMode = value;
			System.out.println("Host is selected " + value);
		} else {
			value = getIp();
			System.out.println("\""+value+"\"");
			if (value.length() < 6) {
				System.out.println("null ip");
				JOptionPane.showMessageDialog(null, "Please enter IP!");
				return value;
			}
			main.Main.gameMode = value;
			System.out.println("Join is selected " + value);
		}
		
		window.Window.text2.setText(name.getText());
		
		
		
		Controller.waker();

    	Stage stage = (Stage) cancel.getScene().getWindow();
	    stage.close();
		
		return value;
	}


	private String getIp() {
		
		return ipAddress.getText();
	}	
	
	@SuppressWarnings("unused")
	public static void showNewGame(Node event) {
		if (/*main.Main.myMatrix.countTiles (new Hull())*/1 <= 0) {
			startGame.setDisable(true);
		} else {
			startGame.setDisable(false);
		}
		
		//TODO: window.Window.showNewGame();
		
	}
}
