package matrix;

import controller.Controller;
import tiles.AbstractTile;


/*
 * Used to save a matrix in data.
 * 
 * i will be used as a holder for "tile" information.
 * 
 * information can be changed in me, and i can be used to retrieve information.
 * 
 */


public class Matrix {
	
	private Controller controller;
	private AbstractTile[][] matrix;
	private int x;
	private int y;
	
	/*
	 * create a matrix with x*y tiles of tile type "Water"
	 */
	public Matrix(int x, int y, Controller controller){
		this.controller = controller;
		this.x = x;
		this.y = y;
		matrix = new AbstractTile[x][y];

		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				//matrix[i][j] = new AbstractTile();
			}
		}
	}
	
	
	
	public AbstractTile getTile(int x, int y) {
		return matrix[x][y];
	}
	
	
	
	/*
	 * replaces tile on cordinate x,y to 3rd parameter (AbstractTile). make sure to make it a new object.
	 */
	public void setTile(int x, int y, AbstractTile replacement) {
		matrix[x][y] = replacement;	
	}
	
	
	
	/*
	 * get a matrix with only integer defining tile rep as an integer matrix
	 */
	public int[][] getAllTileReps() {
		int[][] xyMatrix = new int[x][y];
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; i++) {
				xyMatrix[i][j] = getTile(x,y).getRep();
			}
		}
		return xyMatrix;
	}

	/*
	 * use to get total amount of tiles
	 */
	public int countTiles() {
		return x*y;
	}
	
	/*
	 * use to count number of tiles of specific type
	 */
	public int countTiles(AbstractTile tile) {
		int total = 0;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(getTile(i,j).getRep() == tile.getRep()) {
					total++;	
				}
			}
		}
		return total;
	}
	
	/*
	 * temporary autoFill for testing purposes
	 *
	public void putHull(int modula) {
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(((i+j) % modula) == 0)
				matrix[i][j] = new Hull();
			}
		}
	}
	*/
	
	/*
	 * ShipPlacementChekker. cheks if a ship can be placed where you want it to be placed.
	 * first two parameters for north-western corner
	 * 3rd for how long the ship is in tiles
	 * 4th boolean for if the ship is vertical, else horizontal.
	 */
	@SuppressWarnings("unused")
	private boolean canPlaceShip(int x, int y, int length, boolean vertical) {
		boolean anserw = false;
		
		
		//Chek if it can fit where you want it to
		if (vertical) {
			//when vertical
			if ((this.y - y) >= length) {
				anserw = true;
			}else {
				return false;
			}
		}else {
			//when horizontal
			if ((this.x - x) >= length) {
				anserw = true;
			}else {
				return false;
			}
		}
		
		
		//Chek if it intersects only "Water"
		if (vertical) {
			//when vertical
			for (int i = 0; i < length; i++) {
				if (!(getTile(x,y+i).getRep() == 0)) {
					anserw = false;
				}
			}
		}else {
			//when horizontal
			for (int i = 0; i < length; i++) {
				if (!(getTile(x+i,y).getRep() == 0)) {
					anserw = false;
				}
			}
		}
		
		return anserw;
	}
	
	
	/*
	 * ShipPlacer
	 * Returns true if it placed the ship, false if it can't place.
	 * first two parameters for north-western corner
	 * 3rd for how long the ship is in tiles
	 * 4th boolean for if the ship is vertical, else horizontal.
	 *
	public boolean placeShip(int x, int y, int length, boolean vertical) {

		//tests if can place...
		boolean test = canPlaceShip(x,y,length,vertical);
		if (!test) {
			return false;
		}
		
		//Places ship on matrix if can place.
		if (vertical) {
			//when vertical
			for (int i = 0; i < length; i++) {
				setTile(x,y+i, new Hull());
			}
		}else {
			//when horizontal
			for (int i = 0; i < length; i++) {
				setTile(x+i,y, new Hull());
			}
		}
		
		//places illogical from origin point to prevent you from placing illeagal ships :)
		ilogicalPlacer(x,y);
		
		return true;
	}
	*/
	
	
	
	
	
	/*
	 * IlogicalPlacer
	 * use me to place illogical, aka borders for ships, so that ships can not be placed illeagally, and AI won't shoot where no ships can be placed :)
	 * inparameters are supposed to be any part of a ship, destroyed or alive.
	 * can be used on actual board and 
	 *
	public void ilogicalPlacer(int x, int y) {
		AbstractTile target = getTile(x,y);
		if (target.getRep() == 2 || target.getRep() == 3) { //not sure if this is actually needed. prob good to have.
			
			ilogicalPlacerRec(x,y+1, target);
			ilogicalPlacerRec(x,y-1, target); 
			ilogicalPlacerRec(x+1,y, target);
			ilogicalPlacerRec(x-1,y, target);
			 
			if(target.getRep() == 2) {
				//setTile(x,y, new Hit()); //IDK WHY tHsi IS HERE :S				
			}

		}
		
		
	}
	
	
	
	private void ilogicalPlacerRec(int x, int y, AbstractTile parent) {
		try {
			AbstractTile target = getTile(x,y);
			
			if(target.getRep() == 2 || target.getRep() == 3) {

				
				try {
					if (getTile(x,y+1) != parent) {
						ilogicalPlacerRec(x,y+1, target);
					}
				} catch(IndexOutOfBoundsException e) {
					System.out.println("Matrix: x,y+1 indexoutofboundsexception");
				}
				try {
					if (getTile(x,y-1) != parent) {
						ilogicalPlacerRec(x,y-1, target);
					}
				} catch(IndexOutOfBoundsException e) {
					System.out.println("Matrix: x,y-1 indexoutofboundsexception");
				}
				try {

					if (getTile(x+1,y) != parent) {
						ilogicalPlacerRec(x+1,y, target);			
					}
				} catch(IndexOutOfBoundsException e) {
					System.out.println("Matrix: x+1,y indexoutofboundsexception");
				}
				try {

					if (getTile(x-1,y) != parent) {
						ilogicalPlacerRec(x-1,y, target);			
					}
				} catch(IndexOutOfBoundsException e) {
					System.out.println("Matrix: x-1,y indexoutofboundsexception");
				}
					
			}
			else if (target.getRep() == 0) {
				setTile(x,y,new Ilogical());
			}
			
			
			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Matrix: x,y+1 indexoutofboundsexception");
			
		}
	}
	*/
	
	
	
	/*
	 * Cheks if you can leagally shoot at a specific tile
	 */
	public boolean isLeagalCord(int x, int y) {
		if((this.x >= x && x > -1) && (this.y >= y && y > -1)) {
			//getTile(x,y).getRep() //Add tile checker?
			return true;			
		}
		else {
			return false;
		}
		
	}
	

	/*
	 * HitEffectHandler
	 *
	private boolean hitEffectHandler(int x, int y){
		AbstractTile target = getTile(x,y);
		if(target.hit()) {
			setTile(x,y, new Hit());
			return true;
		}else {
			setTile(x,y, new Miss());
			return false;
		}
	}
	*/

	/*
	 * Cheks if ship still floating
	 */
    private boolean chekShip(int x, int y) {
    	
    	boolean shipSunk = false;
    	
    	
    	//system that branches to all the four directions.
    	
    	//stops when finds ILOGICAL or something unexpected
    	
    	//if finds only hit and no Hull, then shipSunk should be true.
    	

    	//down
    	boolean shouldContinue = true;
    	int tempX = x;
    	int tempY = y;
    	try {
        	while(shouldContinue) {
        		tempY++;
        		int tempRep = getTile(tempX, tempY).getRep();
        		if (tempRep == 2 || tempRep == 3) {
        			if (tempRep == 2) {
        				return false;
        			}else {
        				shipSunk = true;
        			}
        		}else {
        			shouldContinue = false;
        		}
        	}	
    	}catch(IndexOutOfBoundsException e){
    		
    	}
    	//up
    	shouldContinue = true;
    	tempX = x;
    	tempY = y;
    	try {
        	while(shouldContinue) {
        		tempY--;
        		int tempRep = getTile(tempX, tempY).getRep();
        		if (tempRep == 2 || tempRep == 3) {
        			if (tempRep == 2) {
        				return false;
        			}else {
        				shipSunk = true;
        			}
        		}else {
        			shouldContinue = false;
        		}
        	}	
    	}catch(IndexOutOfBoundsException e){
    		
    	}
    	//left
    	shouldContinue = true;
    	tempX = x;
    	tempY = y;
    	try {
        	while(shouldContinue) {
        		tempX--;
        		int tempRep = getTile(tempX, tempY).getRep();
        		if (tempRep == 2 || tempRep == 3) {
        			if (tempRep == 2) {
        				return false;
        			}else {
        				shipSunk = true;
        			}
        		}else {
        			shouldContinue = false;
        		}
        	}	
    	}catch(IndexOutOfBoundsException e){
    		
    	}
    	//right
    	shouldContinue = true;
    	tempX = x;
    	tempY = y;
    	try {
        	while(shouldContinue) {
        		tempX++;
        		int tempRep = getTile(tempX, tempY).getRep();
        		if (tempRep == 2 || tempRep == 3) {
        			if (tempRep == 2) {
        				return false;
        			}else {
        				shipSunk = true;
        			}
        		}else {
        			shouldContinue = false;
        		}
        	}	
    	}catch(IndexOutOfBoundsException e){
    		
    	}
    	
    	
		return shipSunk;
	}
    	

	/*
	 * Handle hits on main board
	 */
	@SuppressWarnings("unused")
	public String hitOrMiss(String xString, String yString) {
		String anserw = "";
		
		//parse
		int x = Integer.parseInt(xString);
		int y = Integer.parseInt(yString);
		
		
		//AbstractTile target = this.getTile(x, y); //idk if usefull?
		
		
		//Was it a hull?
		
		
				//boolean hitOrMiss = hitEffectHandler(x,y);

				
		//Was there a boat that got sunk?
		
				//Handle that shit here :S AAMUJA!!!!
		
				
				//appends hit and shipSunk text
				
				if (/*hitOrMiss*/true) {
					anserw += "1,";
					if (chekShip(x,y)) {
						anserw += "1,";
				    	main.Main.model.setLastEvent("1", "1");
					}else {
						anserw += "0,";
				    	main.Main.model.setLastEvent("1", "0");
					}
					
				}else {
					anserw += "0,0,";
			    	main.Main.model.setLastEvent("0", "0");
				}
				
		
				
				
		
		//did you just loose?
		
				if (/*countTiles(new Hull())*/1 <= 0) {
					anserw += "1";
					controller.loose();
				}else {
					anserw += "0";
				}

		
		return anserw;
	}
	
	




	public void hitReader(String message, int x, int y) {

    	//handle message here
    	
    	String[] response = message.split(",");
    					//HIT?,SHIPSUNK?,WON?
    	
    	main.Main.model.setLastEvent(response[0], response[1]);

		//parse (can be boolean if optimizing)
		int hit = Integer.parseInt(response[0]);
		int shipSunk = Integer.parseInt(response[1]);
		int won = Integer.parseInt(response[2]);
    	
    	
    	
    	System.out.println(message);
    	//System.out.println(response[0]);
    	if(hit == 1) {
    		System.out.println("hit");
    		//setTile(x, y, new Hit());
    	}
    	else {
    		System.out.println("miss");
    		//setTile(x, y, new Miss());
    	}
    	
    	if(shipSunk == 1) {
    		//ilogicalPlacer(x, y);
    	}
    	
    	if(won == 1) {
    		controller.win();
    	}
    	
    	
	}
	
	/*
	 * used for devTesting pourposes
	 * gives you a string that should show what the matrix looks like
	 */
	public String drawMatrix() {
		String string = "";

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				string += getTile(i,j).getRep() + ",";
				
			}
			string += "\n";
		}
		return string;
	}
	
	
}
