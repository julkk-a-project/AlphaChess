package tiles;

import matrix.Matrix;

public class Peon extends AbstractTile {

	public Peon(int x, int y, Matrix board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	public Peon(int x, int y, Matrix board, int side) {
		super(x, y, board, side);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getMoves() {
		
		int moves[][] = new int[2][4];
		int pos = 0; //position in moves list
		
		int relativeX = 0;
		int relativeY = 0; 
		
		

		relativeX = 0;
		//TODO: chek if direction is rigth
		if (side == 1) {
			relativeY = 1;
		}
		else if (side == 0) {
			relativeY = -1;
		}
		if (doableNonHostileCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
			if (!hasMoved) {
				if (doableNonHostileCord(relativeX(relativeX*2),relativeY(relativeY*2))) {
					moves[0][pos] = relativeX(relativeX*2);
					moves[1][pos] = relativeY(relativeY*2);
					pos++;
				}
			}
		}
		
		//when it can eat
		relativeX = -1;
		if (doableHostileCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = 1;
		if (doableHostileCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		
		
		int[][] finalMove = new int[2][pos];
		
		for (int i = 0; i < pos; i++) {
			finalMove[0][i] = moves[0][i];
			finalMove[1][i] = moves[1][i];
		}
		return finalMove;
	}

}
