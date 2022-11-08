package Ex5.model;

/**
* A class to create Board object which has a Game and tracks and displays
* the progress of the game
*
* @author Kim Baldwin
* @version 1.0
* @since September 25, 2022
*/
public class Board implements Constants {
	/**
	* The board grid
	*/
	private char theBoard[][];

	/**
	* The amount of x's and o's that are on the board / the number of turns that have happened
	*/	
	private int markCount;

	/** 
	 * Constructs a Board object with a markCount of 0 and 
	 * a grid of 3x3
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/** 
	 * Gets the placement of the mark on the board
	 * @param row is the row index of the mark
	 * @param col is the column index of the mark
	 * @return char is the mark on the board
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/** 
	 * @return True if all spaces on the board are full
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	
	/** 
	 * @return True if the xPlayer is the winner
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	
	/** 
	 * @return True is the oPlayer is the winner
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/** 
	 * Prints the Board with all the marks on it
	 */
	public String display() {
		String boardString ="Board \n";
		boardString+=displayColumnHeaders();
		boardString+=addHyphens();
		for (int row = 0; row < 3; row++) {
			boardString+=addSpaces();
			boardString+=("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				boardString+=("|  " + getMark(row, col) + "  ");
			boardString+=("|\n");
			boardString+=addSpaces();
			boardString+=addHyphens();
			
		}
		System.out.println(boardString);
		boardString+="\n \0";
		return boardString;
	}
	
	/** 
	 * Adds a mark to the specified spot and increases mark count by one
	 * @param row is the row index of the mark
	 * @param col is the column index of the mark
	 * @param mark is the mark at the specified locatoin
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/** 
	 * Clears all the squares on the board
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}


	/** 
	 * @param mark is either an X or an O
	 * @return 1 if 3 in a row has been achieved by a specific mark
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/** 
	 * Prints columns headers
	 */
	String displayColumnHeaders() {
		String colString =("          ");
		for (int j = 0; j < 3; j++)
			colString+=("|col " + j);
		colString+=('\n');
		return colString;
	}

	/** 
	 * Prints lines on the board
	 */
	String addHyphens() {
		String hypString = "           ";
		for (int j = 0; j < 3; j++)
			hypString+="+-----";
		hypString+="+\n";
		return hypString;
	}

	/** 
	 * Prints the spaces for the squares
	 */
	String addSpaces() {
		String spaceString =("          ");
		for (int j = 0; j < 3; j++)
			spaceString+=("|     ");
		spaceString+="|\n";
		return spaceString;
	}
}
