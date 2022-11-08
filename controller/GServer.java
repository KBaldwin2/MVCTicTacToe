package Ex5.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import GServer;

public class GServer {

    private BufferedReader socketInX;
	private BufferedReader socketInO;
	private PrintWriter socketOutX;
	private PrintWriter socketOutO;
	private ServerSocket serverSocket;
	private Socket xSocket;
	private Socket oSocket;
	private ExecutorService executor;

    /**
	 * Construct a Server with Port 9090
	 */
	public GServer(int port) {
		//Set the serverSocket
		try {
			serverSocket = new ServerSocket(port);
			executor = Executors.newFixedThreadPool(10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void runServer() {
		try {
			while (true) {
				//Accept the connections
				xSocket = serverSocket.accept();
				System.out.println("X Connection accepted by server!");
                socketInX = new BufferedReader (new InputStreamReader (xSocket.getInputStream()));
				socketOutX = new PrintWriter((xSocket.getOutputStream()), true);
				
                socketOutX.println("Message: Waiting for game to start....");

				oSocket = serverSocket.accept();
				System.out.println("O Connection accepted by server!");


				socketInO = new BufferedReader (new InputStreamReader (oSocket.getInputStream()));
				socketOutO = new PrintWriter((oSocket.getOutputStream()), true);
				
				GameThread game = new GameThread (socketOutX, socketInX, socketOutO, socketInO);
				executor.execute(game);
			}
			
		}catch (IOException e) {}
		executor.shutdown();
		
		try {
			socketInX.close();
			socketInO.close();
			socketOutX.close();
			socketOutO.close();
			xSocket.close();
			oSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void main (String [] args){
		GServer gameServer = new GServer (9898);
		gameServer.runServer();
	
	}
    
}
