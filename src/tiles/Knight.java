package tiles;

import matrix.Matrix;

public class Knight extends AbstractTile {

	public Knight(int x, int y, Matrix board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getMoves() {
		
		int moves[][] = new int[2][8];
		int pos = 0; //position in moves list
		
		int relativeX;
		int relativeY;
		
		

		relativeX = -1;
		relativeY = 2;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = 1;
		relativeY = 2;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = -1;
		relativeY = -2;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = 1;
		relativeY = -2;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = -2;
		relativeY = 1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = -2;
		relativeY = -1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = 2;
		relativeY = 1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			moves[0][pos] = relativeX(relativeX);
			moves[1][pos] = relativeY(relativeY);
			pos++;
		}
		relativeX = 2;
		relativeY = -1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
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
