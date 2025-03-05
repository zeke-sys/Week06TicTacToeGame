package week06Lab;

import java.util.Scanner;

public class GameBoard {
	
	private static final char EMPTY = '-';
	private static final char PLAYER_X = 'X';
	private static final char PLAYER_O = 'O';
	private static char[][] board = new char[3][3];
	private static char currentPlayer = PLAYER_X;
	private static int moves = 0;

	public static void main(String[] args) {
		
		//Scanner to input the user's choice of square/spot
		Scanner scanner = new Scanner(System.in);
		initialize(); //sets up empty board

		boolean gameRunning = true;
		 
		//loop to get the game going
		while (gameRunning) { //while loop 
			display(); //shows the current state of the board
			int row, col;
			while (true) {
				System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (0-2): ");
				row = scanner.nextInt();
				col = scanner.nextInt();
				
				//error checking to ensure valid input
				if (row < 0 || row > 2 || col < 0 || col > 2) {
					System.out.println("Invalid input. Please enter values between 0 and 2.");
				} else if (board[row][col] != EMPTY) { //prevents selection of occupied spot
					System.out.println("Spot already occupied. Pick a different spot.");
				} else {
					break;
				}
			} //end of nested while loop 
			
			
			board[row][col] = currentPlayer; //updates board with the player's move
			moves++;
			
			if (checkWinStatus(row, col)) { //checks if the move resulted in a win
				display();
				System.out.println("Player " + currentPlayer + " wins!");
				gameRunning = false;
			} else if (moves == 9) { //checks for draw if all spots are filled
				display();
				System.out.println("It's a draw!");
				gameRunning = false;
			} else {
				currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X; //switches turn
			}
			
		} //end of while loop
		
		scanner.close();
			
	} //end of main


	//Methods
	
	//initialize method to fill board with empty characters
	private static void initialize() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = EMPTY;
			}
		}	
	}
	
	//display method to display current state of the board
	private static void display() {
		System.out.println("Current Board:");
		for (char[] row : board) {
			for (char cell: row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
	
	//checkWinStatus method to check if current move resulted in a win
	private static boolean checkWinStatus(int row, int col) {
		return checkRow(row) || checkColumn(col) || checkDiagonals();
	}
	
	//checks if given row contains a win condition
	private static boolean checkRow(int row) {
		return board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer;
	}
	
	//checks if given column contains a win condition
	private static boolean checkColumn(int col) {
		return board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer;
	}
	
	//checks both diagonals for a win condition
	private static boolean checkDiagonals() {
		return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) || 
				(board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
	}
	
	
	
	//-----------------------------------------
	
	/*
	 * If instead of using 2 arrays, we use an array of length 9
	 * to represent the board. The different declarations and methods
	 * would look like the following:
	 * 
	 *  private static String[] board = new String[9];
	 *  
	 *  int position instead of int row, col in the while loop
	 *  
	 *  The if statement would be:
	 *  if (position < 0 || position > 8) {
                    System.out.println("Invalid input. Please enter values between 0 and 8.");
                } else if (!board[position].equals(EMPTY)) {
                    System.out.println("Spot already occupied. Pick a different spot.");
                } else {
                    break;
                }
	 * 
	 * 	board[position] = currentPlayer;
	 * 
	 *  private static void initialize() {
        for (int i = 0; i < 9; i++) {
            board[i] = EMPTY;
        }
    }

     *
     * private static void display() {
        System.out.println("Current Board:");
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
	 * 
	 * 
	 *  private static boolean checkRow() {
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(currentPlayer) && board[i + 1].equals(currentPlayer) 
            && board[i + 2].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

     *
     * private static boolean checkColumn() {
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(currentPlayer) && board[i + 3].equals(currentPlayer) && board[i + 6].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

     *
     * private static boolean checkDiagonals() {
        return (board[0].equals(currentPlayer) && board[4].equals(currentPlayer) && board[8].equals(currentPlayer)) ||
               (board[2].equals(currentPlayer) && board[4].equals(currentPlayer) && board[6].equals(currentPlayer));
    }
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	

} //end of class
