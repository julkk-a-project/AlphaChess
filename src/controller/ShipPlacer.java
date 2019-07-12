package controller;

//TODO: This should probably be used as an object created in main...

public class ShipPlacer {
	

	private static int x;
	private static int y;
	
	public static void shipPlacer() {
		//public boolean placeShip(int x, int y, int length, boolean vertical) 
		int[]ships = {5,4,3,3,2};
		controller.Controller.waker();
		for(int i = 0; i<ships.length; i++) {
			individualShipPlacer(ships[i]);
		}
		//TODO: window.Window.showNewGame();
	}
	
	private static void individualShipPlacer(int length){
		
		while(main.Main.gameMode == "2") {
			
			
			
			
			//TODO: Get inparameters for "place ship" via gui when "open" is called on tile.
			//open would then wake the system.
			//MAKE SURE IT CAN ONLY HAPPEN WHEN IN GAMEMODE 2
			controller.Controller.waiter();
			/*
			if(main.Main.myMatrix.placeShip(x, y, length, !main.Main.vertical)) {
				window.Window.drawLower();
				//window.Window.drawLower(); //TODO: should this be instead of draw()???
				return;
				//controller.Controller.waker();
			}	
			*/
		}
	}

	
	public static void setCords(int x, int y){
		ShipPlacer.x = x;
		ShipPlacer.y = y;
	}

}

