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
	protected int rep; //used to know what type of representation item(?) to show
	protected boolean canMove; //for tiles that can move. aka anything but Empty.
								//should be true for everything but Empty, unless special piece.
	private int[] cords;
	private boolean side;
	
	Matrix board;
	
	
	
	public AbstractTile(int x, int y, Matrix board) {
		rep = 0;
		canMove = true;
		cords = new int[2];
		cords[0] = x;
		cords[1] = y;
		this.board = board;
	}
	
	public boolean getSide() {
		return side;
	}
	
	
	public int[] getCords(){
		return cords;
	}
	
	/*
	 * Updates piece cord reference, and moves piece in matrix to replace destination with self, and old pos with Empty.
	 */
	public void move(int x, int y) {
		board.setTile(cords[0], cords[1], new Empty(cords[0], cords[1], board));
		cords[0] = x;
		cords[1] = y;
		
		
	}
	
	
	public abstract int[][] getMoves(); //x = x, y = y, z = volume of moves
														//aka the amount of moves the piece can excecute


	public int getRep() {
		return rep;
	}
	
	public boolean canMove() {
		return canMove;
	}
	
}
