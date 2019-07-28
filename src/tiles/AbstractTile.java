package tiles;

import matrix.Matrix;

/*
	 *  overview:			rep		AICanHit	use
	 *  	Empty 		= 	0		true		empty tile
	 * 		Illogical	= 	1		false		for AI logic
	 * 		Hull 		=	2		false		ship part
	 * 		Hit 		=	3		false		broken ship part
	 * 		Miss 		=	4		false		a shot that landed in water
	 * 
	 * 
	 * 
	 */



public abstract class AbstractTile {
	
	

	/*
	 * side:	-1 = default (For empty)
	 * 			0 = black
	 * 			1 = white
	 */

	
	public static int BLACK_SIDE = 0;
	public static int WHITE_SIDE = 1;
	public static int DEFAULT_SIDE = -1; //for empty.
	
	
	protected int rep; //used to know what type of representation item(?) to show
	protected boolean hasMoved = false;
	protected boolean canMove; //for tiles that can move. aka anything but Empty.
								//should be true for everything but Empty, unless special piece.
	private int[] cords;
	protected int side = -1;
	
	Matrix board;
	
	

	public AbstractTile(int x, int y, Matrix board) {
		rep = 0;
		canMove = true;
		cords = new int[2];
		cords[0] = x;
		cords[1] = y;
		this.board = board;
		
	}
	public AbstractTile(int x, int y, Matrix board, int side) {
		rep = 0;
		canMove = true;
		cords = new int[2];
		cords[0] = x;
		cords[1] = y;
		this.side = side;
		this.board = board;
	}
	public int relativeX(int relativeX) {
		return cords[0] + relativeX;
	}
	public int relativeY(int relativeY) {
		return cords[1] + relativeY;
	}

	
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public void flipSide() {
		if (this.side == 1) {
			this.side = 0;	
		}
		else if (this.side == 0) {
			this.side = 1;	
		}
	}
	
	public boolean notOwn(int side) {
		if (this.side != side) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hostile(int side) {
		if ((this.side == 1 && side == 0) || (this.side == 0 && side == 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean empty() {
		if (this.side == -1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean doableCord(int x, int y) {

		try {
			if (board.getTile(x,y).notOwn(side)) {
				return true;
			}
			else {
				return false;
			}
		}catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	public boolean doableNonHostileCord(int x, int y) {

		try {
			if (board.getTile(x,y).notOwn(side) && !board.getTile(x,y).hostile(side)) {
				return true;
			}
			else {
				return false;
			}
		}catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public boolean doableHostileCord(int x, int y) {

		try {
			if (board.getTile(x,y).hostile(side)) {
				return true;
			}
			else {
				return false;
			}
		}catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	
	/*
	 * gives you all possibilities in a streigth line, untill it eats a hostile piece or can't move no more.
	 */
	public int[][] doableRecCord(int directionX, int directionY, int[] relCords){
		int[][] futureMoves = null;
		if (doableCord(relCords[0]+directionX, relCords[1]+directionY)) {
			if (board.getTile(relCords[0]+directionX, relCords[1]+directionY).empty()) {
				int[] nextRelCords = {relCords[0]+directionX, relCords[1]+directionY};
				futureMoves = doableRecCord(directionX, directionY, nextRelCords);
			}
			
			
			int leng = futureMoves[0].length;
			
			int[][] thisMove = new int[2][leng+1]; //thisMove == this and future moves.
			thisMove[0][0] = relCords[0]+directionX;
			thisMove[1][0] = relCords[1]+directionY;
			for (int i = 0; i <= leng; i++) {			//TODO: chek if needs an IF statement to prevent crash or failiure and shuch. PROB WORKS t. fransman
				thisMove[0][i+1] = futureMoves[0][i];
				thisMove[1][i+1] = futureMoves[1][i];
			}
			return thisMove;
		}
		else {
			return null;
		}
		
	}
	
	
	public int[] getCords(){
		return cords;
	}
	
	/*
	 * Updates piece cord reference, and moves piece in matrix to replace destination with self, and old pos with Empty.
	 */
	public void move(int x, int y) {
		hasMoved = true;
		board.setTile(cords[0], cords[1], new Empty(cords[0], cords[1], board));
		cords[0] = x;
		cords[1] = y;
		board.setTile(x,y,this);
	}
	
	
	public abstract int[][] getMoves(); //int[0 = x, 1 = y][n (n:th possible move)]


	public int getRep() {
		return rep;
	}
	
	public boolean canMove() {
		return canMove;
	}
	
}
