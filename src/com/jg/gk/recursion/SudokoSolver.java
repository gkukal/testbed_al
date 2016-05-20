package com.jg.gk.recursion;

import com.jg.gk.util.Util;

public class SudokoSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = { { 0, 6, 0, 1, 0, 4, 0, 5, 0 }, { 0, 0, 8, 3, 0, 5, 6, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 8, 0, 0, 4, 0, 7, 0, 0, 6 }, { 0, 0, 6, 0, 0, 0, 3, 0, 0 }, { 7, 0, 0, 9, 0, 1, 0, 0, 4 },
				{ 5, 0, 0, 0, 0, 0, 0, 0, 2 }, { 0, 0, 7, 2, 0, 6, 9, 0, 0 }, { 0, 4, 0, 5, 0, 8, 0, 7, 0 } };

		new SudokoSolver().sudokoCall(board, 0, 0, 0);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Need to go on each cell and try all options one by one
	 * 
	 * @param matrix
	 * @param level
	 * @return
	 */

	public boolean sudokoCall(int[][] matrix, int startX, int startY, int level) {

		// base condition
		if (startX == 8 && startY == 8) {
			return true;
		}
		// When cell is reached in the matrix and either it is filled

		// Find the next open slot
		while (startY < 9 && startX < 9 && matrix[startX][startY] != 0) {
			if ( startY == 8 && startX < 8) {
				startX = startX + 1;
				startY = 0;
			} else {
				startY = startY + 1;
			}
		}
		//if( startX > 8  ) startX = 8;
		//Util.print("Adding to X: "+startX+" y: "+startY , level);

		for (int k = 1; k <= 9; k++) { // try all numbers
			if (matrix[startX][startY] == 0) {
				Util.print("Adding to X: "+startX+" y: "+startY +" K :"+k, level);
				matrix[startX][startY] = k;
				//Util.print("Adding to X: "+startX+" y: "+startY +" K :"+k, level);
				boolean canbeAdded = canThisNumberBeAdded(matrix, k, startX, startY);
				if (canbeAdded == false) {
					matrix[startX][startY] = 0;
				} else {
					Util.print("Adding to X: "+startX+" y: "+startY +" K :"+k, level);
					boolean notworking = sudokoCall(matrix, startX, startY ,level + 1 );
					if (notworking) {
						matrix[startX][startY] = 0;
					} else {
						return true;
					}
				}
			} else {
				boolean notworking = sudokoCall(matrix, level + 1, startX, startY);
				return notworking;
			}

		}

		return false;
	}

	private boolean canThisNumberBeAdded(int[][] matrix, int num, int x, int y) {

		for (int i = 0; i < 9; i++) {
			if (matrix[x][i] == num && y != i)
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (matrix[i][y] == num && x != i)
				return false;
		}
		// Is it in the same zone
		/**
		 * y / 3 = column to start x /3 = row to start
		 */
		int startColumn = (y / 3) * 3;
		int startRow = (x / 3) * 3;
		for (int i = startRow; i < 3; i++) {
			for (int j = startColumn; j < 3; j++) {
				if (matrix[i][j] == num && ((x != i) || (y != j)))
					return false;
			}
		}
		return true;
	}

}
