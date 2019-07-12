package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import matrix.Matrix;

public class AI {
	List<String> coords;
	List<Boolean> vertical;
	private int coordIndex;
	private Matrix myMatrix;
	private Matrix itMatrix;
	private static Controller controller;
	

	//For testing only
	public static void main(String[] args) {
		controller = new Controller();
		AI ai = new AI(controller);
	}
	
	
	public AI(Controller controller) {
		coords = new ArrayList<>();
		vertical = new ArrayList<>();
        vertical.add(true);
        vertical.add(false);
        
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
		        coords.add(i+","+j);
			}
		}	

		shuffleCoords();
		shuffleVertical();
		
		itMatrix = new Matrix(10,10, controller);
		
		boolean shipsPlaced = false;
		
		//tries to place ships until placed logically
		while(!shipsPlaced) {
			createMyMatrix(); //to clear the field incase of faulty system
			shipsPlaced = shipPlacer(myMatrix); //should place ships according to shuffled coord list			
		}
		System.out.println(myMatrix.drawMatrix());
 
		

		shuffleCoords(); //so that AI is not predictable according to shooting pattern
		
		
		
		
		
	}

	
	
	public void shuffleCoords() {
		Collections.shuffle(coords);
		System.out.println(coords);
		
	}
	
	
	
	public void shuffleVertical() {
		Collections.shuffle(vertical);
		//System.out.println(vertical+"");
		
	}
	
	
	
	
	public String getHit (int[] cords) {
		return myMatrix.hitOrMiss(cords[0]+"", cords[1]+""); //TODO: this is ineffective. the citation marks make the ints to string, then the method makes it back into an int...
	}
	
	
	/*
	 * VERY SIMPLE AI TO SHOOT AT YOUR TILES. ESSENTUALLY IT FIRES AT EVERY TILE IT CAN,
	 * EXCEPT ON ILLOGICAL TILES AND PLACES IT HAS TRIED IN THE PAST
	 */
	public int[] fireAt () {
		int[] cords = getNextCoords();
		boolean notLeagal = true;
		while(notLeagal) {
			if (itMatrix.getTile(cords[0], cords[1]).AICanHit()) {
				notLeagal = false;
			}else {
				cords = getNextCoords();
			}
		}

		
		itMatrix.hitReader(main.Main.myMatrix.hitOrMiss(cords[0]+"", cords[1]+""),cords[0], cords[1]);
		
		
		return cords;
	}
	
	public void handleItMatrix(int[] cords) {
		
	}
	
	
	
	
	 
	public boolean shipPlacer(Matrix matrix) {
		//public boolean placeShip(int x, int y, int length, boolean vertical) 
		int[]ships = {5,4,3,3,2}; //TODO: IF WE MAKE THIS A CHANGING VARIABLE, STORE IN MODEL.
		boolean working = true;
		
		for(int i = 0; i<ships.length; i++) {
			System.out.println("placing "+ships[i]);
			working = individualShipPlacer(ships[i], matrix);
			if (!working) {
				return false;
			}
		}
		return true;
		
		//TODO: window.Window.showNewGame();
	}
	
	private boolean individualShipPlacer(int length, Matrix matrix){
		int i = 0;
		while(i < 200) {
			i++;
			
			int[] coords = getNextCoords();

			shuffleVertical(); //to get random orientation
			
			
			/*
			if(matrix.placeShip(coords[0], coords[1], length, vertical.get(0))) {
				return true;
			}		
			if(matrix.placeShip(coords[0], coords[1], length, vertical.get(1))) {
				return true;
			}
			*/	
		}
		System.out.println("AI SUCKS AT PLACING SHIPS");
		System.out.println(matrix.drawMatrix());
		return false;
	}


	private int[] getNextCoords() {
		if (coordIndex >= coords.size()) {
			System.out.println("looping" + coordIndex);
			coordIndex = 0;
		}
		String[] SCords = coords.get(coordIndex).split(",");
		int cordX = Integer.parseInt(SCords[0]);
		int cordY = Integer.parseInt(SCords[1]);
		int[] ICords = {cordX, cordY};
		coordIndex++;
		System.out.println(ICords[0] + "," + ICords[1]);
		return ICords;
		
	}
	
	private void createMyMatrix() {
		myMatrix = new Matrix(10,10, new Controller());
	}

}
