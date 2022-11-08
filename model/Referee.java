package Ex5.model;
import java.io.IOException;

/**
* A class to create a Referee object that initializes the players of the game,
* displays the board and begins the game
*
* @author Kim Baldwin
* @version 1.0
* @since September 25, 2022
*/

public class Referee {

	/**
	* the Player objects in the game that play against each other
	*/
    private Player xPlayer, oPlayer;

    /**
	* The board object
	*/
    private Board board;

    /** 
	 * Constructs a Referee object 
	 */
    public Referee() {
        
    }

    /** 
	 * Sets the opponents to be xPLayer and oPlayer
     * then displays the board and initiates the play method of xPlayer
     * @throws IOException
	 */
    public void runTheGame() throws IOException {
        getxPlayer().setOpponent(getoPlayer());
        getoPlayer().setOpponent(getxPlayer());
        getBoard().display();
        getxPlayer().play();
    }
  
    /** 
     * Retrieves the xPlayer
     * @return xPlayer
     */
    public Player getxPlayer() {
        return xPlayer;
    }

    
    /** 
     * Sets the xPlayer
     * @param xPlayer is the Player object that will play with x mark 
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    
    /** 
     * Retrieves the oPlayer
     * @return oPlayer
     */
    public Player getoPlayer() {
        return oPlayer;
    }

    
    /** 
     * sets the oPlayer
     * @param oPlayer is the Player object that will play with o mark
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    
    /** 
     * retrieves the Board object
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    
    /** 
     * Sets the Board object
     * @param board is the board object of specified size and headings given in Board class
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    

    
}
