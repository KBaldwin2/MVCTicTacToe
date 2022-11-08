package Ex5.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientUI {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	public ClientUI(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	public void play() {
        String line="";
		String response = "";
        while(true) {
            try {
                line = socketIn.readLine();
                if(line.contains("Message:")) {
                    System.out.println(line);
                    //System.out.println("Message Line");
                }
                else if(line.contains("Board")) {
                    while(!line.contains("\0")) {
                        line = socketIn.readLine();
                        System.out.println(line);
                        // System.out.println("Board Line");
                    }
                    //System.out.println(i);
                }
                else if(line.contains("QUIT")) {
                    break;
                }
                else {
                    //Assumes it requires an input from user
                    //System.out.println("Other stuff line");
                    System.out.println(line);
                    response = stdIn.readLine();
                    socketOut.println(response);
                    
                }
            }catch(IOException e) {
				e.getStackTrace();
			}
        }
        try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
    }


	public static void main(String[] args) throws IOException  {
		ClientUI aClient = new ClientUI("localhost", 9898);
        aClient.play();
		
	}
}

