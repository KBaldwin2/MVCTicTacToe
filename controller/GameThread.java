package Ex5.controller;
import Ex5.model.*;
import Ex5.view.*;

import java.io.*;

public class GameThread implements Runnable, Constants{
    
	private Board theBoard;
	private Referee theRef;
	private PrintWriter socketOutX;
	private BufferedReader socketInX;
	private PrintWriter socketOutO;
	private BufferedReader socketInO;
    private Player xPlayer, oPlayer;


    public GameThread(PrintWriter socketOutX, BufferedReader socketInX, PrintWriter socketOutO,
        BufferedReader socketInO) {
        theBoard = new Board();
        this.socketOutX = socketOutX;
        this.socketInX = socketInX;
        this.socketOutO = socketOutO;
        this.socketInO = socketInO;
    }

    public String getxPlayerInfo() {
        String name = "";
        while (true) {
			try {
                socketOutX.println("Please enter your name: ");
				name = socketInX.readLine();
				if (name.equals(null)) {
					name = "Please try again!";
					socketOutX.println(name);
					break;
				}
				return name;
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
        return name;
    }

    public String getoPlayerInfo() {
        String name = "";
        while (true) {
			try {
                socketOutO.println("Please enter your name: ");
				name = socketInO.readLine();
				if (name.equals(null)) {
					name = "Please try again!";
					socketOutO.println(name);
					break;
				}
				return name;
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
        return name;
    }

    @Override
    public void run() {
        Referee theRef;

        while(true) {
            try {
                //Set xPlayer
                socketOutO.println("Message: Waiting for other player to connect..");
                socketOutX.println("Please enter your name!:");
                String xPlayerName = socketInX.readLine();
                socketOutX.println("Message: Hello "+xPlayerName+"! Please wait for other player.");
                System.out.println(xPlayerName);

                this.xPlayer = new Player(xPlayerName, LETTER_X, socketOutX, socketInX);
                xPlayer.setBoard(this.theBoard);

                //Set oPlayer
                socketOutO.println("Please enter your name!:");
                String oPlayerName = socketInO.readLine();

                this.oPlayer = new Player(oPlayerName, LETTER_O, socketOutO, socketInO);
                socketOutX.println("Message: Welcome "+oPlayerName+"!");
                oPlayer.setBoard(this.theBoard);

                socketOutO.println("Message: Lets Play!");
                socketOutO.println(theBoard.display());
                socketOutX.println("Message: Lets Play!");
                socketOutX.println(theBoard.display());
                System.out.println("CHECKPOINT");

                theRef = new Referee();
                theRef.setBoard(this.theBoard);
		        theRef.setoPlayer(oPlayer);
		        theRef.setxPlayer(xPlayer);
                this.appointReferee(theRef);

                //End the game to close the sockets
                socketOutO.println("QUIT");
                socketOutX.println("QUIT");
                break;

            }catch (IOException e) {
				e.getStackTrace();
			}
        }
        try {
			socketInX.close();
            socketInO.close();
			socketOutX.close();
            socketOutO.close();

		}catch(IOException e) {
			e.getStackTrace();
		}

    }

    /** 
	 * Specifies a referee for the game
	 * @param r is the referee object
	 * @throws IOException
	 */
	public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }





    
    
}
