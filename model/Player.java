package Ex5.model;
import java.io.*;
import java.util.*;
/**
* A class to create a Player object that sets all the attributes of the game player
*
* @author Kim Baldwin
* @version 1.0
* @since September 25, 2022
*/

public class Player {

    /**
	* The name of the player
	*/
    private String name;

    /**
	* The board the player is playing on
	*/
    private Board board;

    /**
	* The Player object that the player is playing against
	*/
    private Player opponent;

    /**
	* The mark that the player is using
	*/
    private char mark;

    private PrintWriter socketOut;
	private BufferedReader socketIn;

    /** 
	 * Constructs a Player Object with the specified avlues of name and mark
	 * @param name is the name of the Player
	 * @param mark is the character that the player is using
	 */
    public Player(String name, char mark, PrintWriter socketOut, BufferedReader socketIn) {
        this.name = name;
        this.mark = mark;
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }
  
    /** 
     * Retrieves the name of the Player
     * @return the name of the Player
     */
    public String getName() {
        return name;
    }

    /** 
     * Sets the name of the Player
     * @param name is the name of the Player
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * Retrieves the Board the player is playing on
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /** 
     * Sets the board the player is playing on
     * @param board is the board the player is using
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /** 
     * Retrieves the players current opponent
     * @return the opponent which is another player object
     */
    public Player getOpponent() {
        return opponent;
    }

    
    /** 
     * Sets the opponent of the current Player
     * @param opponent is the Player the current player is playing against
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    
    /** 
     * Retrieves the character symbol the player is using
     * @return char is the symbol the player is using
     */
    public char getMark() {
        return mark;
    }

    /** 
     * Sets the mark the player will use
     * @param mark is the mark the player is using
     */
    public void setMark(char mark) {
        this.mark = mark;
    }

    /** 
     * Checks to make sure there is no winner or tie. If not, calls makeMove() and 
     * displays the board after move has been made. Then passes turn to opponent
     * @throws IOException
     */
    public void play() {
        // try {
            while(getBoard().xWins() == false && getBoard().oWins() == false && getBoard().isFull() == false) {

                getOpponent().socketOut.println("Message: It is  your opponent's turn. Please wait..");
                this.makeMove();
                socketOut.println(getBoard().display());
                opponent.socketOut.println(getBoard().display());
                
                if(getBoard().xWins() == true || getBoard().oWins() == true) {
                    socketOut.println(getName()+ " wins!");
                    opponent.socketOut.println(getName()+ " wins!");
                }
                else if(getBoard().isFull() == true) {
                    socketOut.println("It's a tie!");
                    opponent.socketOut.println("it's a tie!");
                }
                else {
                    opponent.play();
                }
            }

        // }catch (IOException err) {
        //     err.getStackTrace();
        }
    //}

    /** 
     * Checks to make sure there is no winner or tie. If not, calls makeMove() and 
     * displays the board after move has been made. Then passes turn to opponent.
     */
    public void makeMove() {
       try {
            socketOut.println("Please enter row (between 0 and 2): ");
            //System.out.println("CHECKPOINT 2");
            int row = Integer.parseInt(socketIn.readLine());
            //System.out.println("CHECKPOINT 3");
            // int row = Integer.parseInt(row_string);
    
            socketOut.println("Please enter col (between 0 and 2): ");
            String col_string = socketIn.readLine();
            int col = Integer.parseInt(col_string);
    
            getBoard().addMark(row, col, getMark());

        }catch (IOException e) {
            e.getStackTrace();
        }
    }
}


