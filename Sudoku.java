package Sudoku;
import java.util.Scanner;

public class Sudoku {
	
	//static int[][] board = {{5,3,0,0,7,0,0,0,0}, {6,0,0,1,9,5,0,0,0}, {0,9,8,0,0,0,0,6,0}, {8,0,0,0,6,0,0,0,3}, {4,0,0,8,0,3,0,0,1}, {7,0,0,0,2,0,0,0,6},
		//					{0,6,0,0,0,0,2,8,0}, {0,0,0,4,1,9,0,0,5}, {0,0,0,0,8,0,0,7,9}};
	static int[][] board = new int[9][9];
	
	//Allows user to input numbers
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
	
	//Prints the board
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
	
	//Checks if the given parameter 'num' is repeated anywhere in that row
	public static boolean repeatedInRow(int row, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	//Checks if the given parameter 'num' is repeated anywhere in that column
	public static boolean repeatedInCol(int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num) {
				return true;
			}
		}
		return false;
	}
	//Checks if the given parameter 'num' is repeated anywhere in a 3x3 box
	public static boolean repeatedInBox(int row, int col, int num) {
		//rowStart and colStart find the difference between the row or column number and the modulus of 3 to determine which 3x3 box to focus on
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
	//Checks if 'num' is allowed in a certain cell 
	public static boolean numAllowed(int row, int col, int num) {
		return !(repeatedInRow(row, num) || repeatedInCol(col, num) || repeatedInBox(row, col, num));
		
	}
	
	//Solves the  board
	public static boolean fillBoard() {
		//Iterates through the board
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				//Checks if there is an empty cell
				if (board[row][col] == 0) {
					//Tries numbers 1-9 on the empty cell
					for (int i = 1; i < 10; i++) {
						//Check if the number is allowed, if it is set it in the cell
						if (numAllowed(row, col, i)) {
							board[row][col] = i;
							//Runs fillboard again
							if (fillBoard()) {
								return true;
							}
							//If fillboard() doesn't go through the no more numbers are available to be set and it returns false
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
		sudoku.enterBoard();
		sudoku.printBoard();
		sudoku.fillBoard();
		System.out.println("_____________________");
		sudoku.printBoard();
	}
}
