package controller;

import java.io.File;

import javax.swing.JOptionPane;

import ai.AI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Waiter;
import main.WaiterRunner;
import matrix.Matrix;
import network.Provider3;
import network.Requester3;

public class Controller {
	public static int[] cords;
	
	private static boolean hisTurn;
	private static boolean giveCords;
	private boolean lost;
	private boolean won;
	public static WaiterRunner waiterRunner;

	private static Provider3 server;

	private static Requester3 client;
	
	

	public Controller() {
		@SuppressWarnings("unused")
		boolean lost = false;
		@SuppressWarnings("unused")
		boolean won = false;
		Waiter waiter = new Waiter();
        waiterRunner = new WaiterRunner(waiter);
		
	}
	
	public void initializeGame() {
		//Here should we initialize everything linked to window
		
		//Reset everything to water
		//Place ships
		//Creates your playingField
        main.Main.myMatrix = new Matrix(10,10,this);
        
        
        //creates empty "projection" of enemy field
        main.Main.itMatrix = new Matrix(10,10,this);
        //placeShips();
        
	}
	
	public void placeShips() {
		//Place ships here
		//customize your ship layout here
		ShipPlacer.shipPlacer();
        //main.Main.myMatrix.putHull(3);
        
	}

	public void AIGame() {

		AI ai = new AI(new Controller());
		String message = "0,0,0";
		
        hisTurn = false;
  
        System.out.println("UR TURN");
        main.Main.addToBuffer("UR TURN");

    	draw();

        while (!hasWon() && !hasLost()) {
        	
        	giveCords = true;
	        
        	waiter();
	        
	        message = ai.getHit(cords);

			main.Main.itMatrix.hitReader(message, cords[0], cords[1]);

	        
	    	soundEffect(getSFXPath());
	        System.out.println("HIS TURN");
	        main.Main.addToBuffer("HIS TURN");
	        drawLower();
        	giveCords = false;
        	
	        
	        hisTurn = true;
	        
	        //for singleplayer
	        
	        ai.fireAt();
	        
	        System.out.println("UR TURN");
	        main.Main.addToBuffer("UR TURN");
	    	soundEffect(getSFXPath());
	        drawUpper();
	        
	        
        }
        

        //TODO: MAYBE SOMETHING HAPPENS WHEN YOU WIN
        if(hasWon()) {
            JOptionPane.showMessageDialog(null, "you won.");        	
        }
        if(hasLost()) {
        	JOptionPane.showMessageDialog(null, "you lost :(");
        }
        
	}
	
	public void host() {
		//Host
		//current model of thought says that this should be run when waiting for a response from the other player
		
		hisTurn = true;
        server = new Provider3();
        //Requester3 client = new Requester3();

        //We'll see if this is even needed now :)))
        	//Temporary because i'm lazy and don't know how to do it automatically
        	//server.setServer(JOptionPane.showInputDialog("IP to connect to")); //IP to connect to
                
        boolean connected = false;
        while (!connected) {
        	connected = server.connect();
        }
        
        while (!hasWon() && !hasLost()) {
        	System.out.println("HIS TURN");
        	main.Main.addToBuffer("HIS TURN");
        	hisTurn = true;
	        while(hisTurn){
	            hisTurn = server.run();
	        }

	        
	        System.out.println("UR TURN");
	        main.Main.addToBuffer("UR TURN");
	    	soundEffect(getSFXPath());
	        drawLower();
	        /*
	        int cordX = Integer.parseInt(JOptionPane.showInputDialog("cord X"));
	        int cordY = Integer.parseInt(JOptionPane.showInputDialog("cord Y"));
	        cords[0] = cordX;
	        cords[1] = cordY;
	        */
        	giveCords = true;
	        waiter();    
	        
	        //Check if cords point to a water tile on own map to avoid dumb shooting.
	        if (!hasWon() && !hasLost()) {
		        server.run(cords);	
	        }

	    	soundEffect(getSFXPath());
	        drawUpper();
        	giveCords = false;
        }
        
        //TODO: MAYBE SOMETHING HAPPENS WHEN YOU WIN
        if(hasWon()) {
            JOptionPane.showMessageDialog(null, "you won.");        	
        }
        if(hasLost()) {
        	JOptionPane.showMessageDialog(null, "you lost :(");
        }

        server.disconnect();		
	}
	
	

	public void join(String ip) {

		//Joiner
        client = new Requester3(); // an object wi

        //Connects
        client.setServer(ip); //IP to connect to
        boolean connected = false;
        while (!connected) {
	        connected = client.connect();	        	
        }
        
        hisTurn = false;
  
        System.out.println("UR TURN");
        main.Main.addToBuffer("UR TURN");

    	draw();

        while (!hasWon() && !hasLost()) {
        	
        	giveCords = true;
	        
        	waiter();
	        
	        client.run(cords);
	    	soundEffect(getSFXPath());
	        System.out.println("HIS TURN");
	        main.Main.addToBuffer("HIS TURN");
	        drawUpper();
        	giveCords = false;
        	
	        
	        hisTurn = true;
	        
	        while(hisTurn && !hasWon()){
	            hisTurn = client.run();
	        }

	        System.out.println("UR TURN");
	        main.Main.addToBuffer("UR TURN");
	    	soundEffect(getSFXPath());
	        drawLower();
	        
	        
        }
        

        //TODO: MAYBE SOMETHING HAPPENS WHEN YOU WIN
        if(hasWon()) {
            JOptionPane.showMessageDialog(null, "you won.");        	
        }
        if(hasLost()) {
        	JOptionPane.showMessageDialog(null, "you lost :(");
        }
        
        client.disconnect();
	}
	

	public static void disconnectAny() {
		try {
			server.disconnect();
		}catch(Exception e) {
			
		}
		
		try {
			client.disconnect();
			
		}catch(Exception e) {
			
		}
	}

	private void draw() {
        window.Window.draw();
	}
	private void drawUpper() {
        window.Window.drawUpper();
	}
	private void drawLower() {
        window.Window.drawLower();
	}

	public static void waiter() {
		waiterRunner.run();
	}
	
	public static void waker() {
		waiterRunner.runWaker();
	}
	public static boolean canCord() {
    	return giveCords;
	}
	public void loose() {
		System.out.println("DEVHELP: LOST CALLED");
		lost = true;
	}
	public boolean hasLost() {
		return lost;
	}
	public void win() {
		System.out.println("DEVHELP: WIN CALLED");
		won = true;
	}
	public boolean hasWon() {
		return won;
	}
	
	public void resetGameState() {
		won = false;
		lost = false;
	}
	

	/*
	 * SFX. USE RELATIVE PATH IN STRING FORMAT
	 */
	public void soundEffect(String mp3Path) {
		
		//hack: (prevents crashes if there is no path in lastEvent.)
		if (mp3Path.equals("nullSound.mp3")) {
			return;
		}

		Media sound = new Media(new File(mp3Path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	public void setLastEvent(String hit, String sunk) {
		
	}
	
	
	public String getSFXPath() {
		String mp3File;
		String lastEvent = main.Main.model.getLastEvent();
		try {

			if(lastEvent.equals("1,0")) {
				mp3File = "hit.mp3";
			} else if (lastEvent.equals("1,1")) {
				mp3File = "sunk.mp3";
			} else {
				mp3File = "miss.mp3";
			}

			//hack: (prevents crashes if there is no path in lastEvent.)
		}catch(Exception e) {
			mp3File = "nullSound.mp3";
		}
		return mp3File;
	}

	
	
		


}
