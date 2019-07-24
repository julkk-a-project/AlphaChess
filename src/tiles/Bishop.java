package tiles;

import matrix.Matrix;

public class Bishop extends AbstractTile {

	public Bishop(int x, int y, Matrix board) {
		super(x, y, board);
		// TODO Auto-generated constructor stub
	}

	public Bishop(int x, int y, Matrix board, int side) {
		super(x, y, board, side);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getMoves() {

		
		//sublists
		int move7[][] = null;
		//int move8[][] = null;
		int move9[][] = null;
		//int move4[][] = null;
		//int move6[][] = null;
		int move1[][] = null;
		//int move2[][] = null;
		int move3[][] = null;
		
		int pos = 0; //position in moves list
		
		int relativeX;
		int relativeY;
		
		

		relativeX = -1;
		relativeY = -1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move7 = doableRecCord(relativeX, relativeY, getCords());
			pos += move7[0].length;
		}/*
		relativeX = 0;
		relativeY = -1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move8 = doableRecCord(relativeX, relativeY, getCords());
			pos += move8[0].length;
		}*/
		relativeX = 1;
		relativeY = -1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move9 = doableRecCord(relativeX, relativeY, getCords());
			pos += move9[0].length;
		}/*
		relativeX = -1;
		relativeY = 0;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move4 = doableRecCord(relativeX, relativeY, getCords());
			pos += move4[0].length;
		}*//*
		relativeX = 1;
		relativeY = 0;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move6 = doableRecCord(relativeX, relativeY, getCords());
			pos += move6[0].length;
		}*/
		relativeX = -1;
		relativeY = 1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move1 = doableRecCord(relativeX, relativeY, getCords());
			pos += move1[0].length;
		}/*
		relativeX = 0;
		relativeY = 1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move2 = doableRecCord(relativeX, relativeY, getCords());
			pos += move2[0].length;
		}*/
		relativeX = 1;
		relativeY = 1;
		if (doableCord(relativeX(relativeX),relativeY(relativeY))) {
			move3 = doableRecCord(relativeX, relativeY, getCords());
			pos += move3[0].length;
		}
		int[][] finalMove = new int[2][pos];
		
		int count = 0; //used to know where in list after a sublist we are.
		int i = 0;
		for (i = 0; i < move7[0].length; i++) {
			finalMove[0][i+count] = move7[0][i+count];
			finalMove[1][i+count] = move7[1][i+count];
		}
		count += i;/*
		for (i = 0; i < move8[0].length; i++) {
			finalMove[0][i+count] = move8[0][i+count];
			finalMove[1][i+count] = move8[1][i+count];
		}*/
		count += i;
		for (i = 0; i < move9[0].length; i++) {
			finalMove[0][i+count] = move9[0][i+count];
			finalMove[1][i+count] = move9[1][i+count];
		}
		count += i;/*
		for (i = 0; i < move4[0].length; i++) {
			finalMove[0][i+count] = move4[0][i+count];
			finalMove[1][i+count] = move4[1][i+count];
		}
		count += i;*//*
		for (i = 0; i < move6[0].length; i++) {
			finalMove[0][i+count] = move6[0][i+count];
			finalMove[1][i+count] = move6[1][i+count];
		}*/
		count += i;
		for (i = 0; i < move1[0].length; i++) {
			finalMove[0][i+count] = move1[0][i+count];
			finalMove[1][i+count] = move1[1][i+count];
		}
		count += i;/*
		for (i = 0; i < move2[0].length; i++) {
			finalMove[0][i+count] = move2[0][i+count];
			finalMove[1][i+count] = move2[1][i+count];
		}*/
		count += i;
		for (i = 0; i < move3[0].length; i++) {
			finalMove[0][i+count] = move3[0][i+count];
			finalMove[1][i+count] = move3[1][i+count];
		}
		count += i;
					//TODO: count should be == pos.
		System.out.println("DEVHELP - these should be equal: pos = "+ pos +" count = "+ count);
		
		
		return finalMove;
	}

}
