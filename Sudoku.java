package Sudoku;
import java.util.Scanner;

public class Sudoku {
	
	static int[][] board = {{5,3,0,0,7,0,0,0,0}, {6,0,0,1,9,5,0,0,0}, {0,9,8,0,0,0,0,6,0}, {8,0,0,0,6,0,0,0,3}, {4,0,0,8,0,3,0,0,1}, {7,0,0,0,2,0,0,0,6},
							{0,6,0,0,0,0,2,8,0}, {0,0,0,4,1,9,0,0,5}, {0,0,0,0,8,0,0,7,9}};
//	static int[][] board = new int[9][9];
	public static void enterBoard() {
		Scanner input = new Scanner(System.in);
		
		//Board entry
		System.out.println("Input 9 numbers per row entering a 0 to indicate a missing cell. Do not repeat or input numbers out of the 1-9 range");
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				 System.out.print("Enter a value for col " + col + ": ");
				 board[row][col] = input.nextInt();
			}
		}
	}
	
	public static void printBoard() {
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				if (col == 2 || col == 5)
					System.out.print(board[row][col] + "  ");
				else
					System.out.print(board[row][col] + " ");
				if ((row == 2 && col == 8) || (row == 5 && col == 8)) 
					System.out.println("");
			}
			System.out.println();
		}
	}
	
	public static boolean repeatedInRow(int row, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean repeatedInCol(int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean repeatedInBox(int row, int col, int num) {
		int rowStart = row - row%3;
		int colStart = col - col%3;
		for (int r = rowStart; r < rowStart + 3; r++) {
			for (int c = colStart; c < colStart + 3; c++) {
				if (board[r][c] == num)
					return true;
			}
		}
		return false;
	}
	public static boolean numAllowed(int row, int col, int num) {
		return !(repeatedInRow(row, num) || repeatedInCol(col, num) || repeatedInBox(row, col, num));
		
	}
	
	public static boolean fillBoard() {
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				if (board[row][col] == 0) {																																										
					for (int i = 1; i < 10; i++) {
						if (numAllowed(row, col, i)) {
							board[row][col] = i;
							if (fillBoard()) {
								return true;
							}
							else {
								board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
	//	sudoku.enterBoard();
		sudoku.printBoard();
		sudoku.fillBoard();
		System.out.println("_____________________");
		sudoku.printBoard();
	}
}
