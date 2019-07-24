package tiles;

import java.util.ArrayList;

import matrix.Matrix;

public class Empty extends AbstractTile {

	public Empty(int x, int y, Matrix board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
		canMove = false;
	}

	@Override
	public int[][] getMoves() {
		// TODO Auto-generated method stub
		return null;
	}


}
