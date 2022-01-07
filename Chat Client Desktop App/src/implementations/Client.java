package implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.paint.Color;

public class Client{
	
	static String echoString ="";
	public static String response ="";
	static BufferedReader echoes;
	static PrintWriter stringToEcho;
	static Socket cSocket;
	static Scanner input;
	static String line="";
	public static String name = "";
	
	public static boolean connectstat = false;
	
	public static void Cline(String str) {
		
		if(str !=null) {
			
			line = str;
		}
	}
	
	public static void ClientInput() throws NoSuchElementException {
			
		try {
			echoes = new BufferedReader( new InputStreamReader(cSocket.getInputStream()));
			stringToEcho = new PrintWriter(cSocket.getOutputStream(), true);
			
			input = new Scanner(line);

			while(input.hasNext()) {
				echoString = input.nextLine();

				line ="";
				
				//sent to server which get's sent back here
				stringToEcho.println(name +": " + echoString);

				response += echoes.readLine()+System.getProperty( "line.separator");
	
				System.out.println("Client response:\n" + response);		
			}
			
			//Gui.lbMessage.setText(response);
			

		} catch (Exception e2) {

			e2.printStackTrace();
		}
	}	
				
	public static void runClient() {
			
		try{
			
			cSocket = new Socket("localhost", 4000);

			connectstat = true;
			
			name = Gui.name.getText();
		}
		catch(IOException e) {
			
			Gui.lbFeedback.setText(e.getMessage());
			System.out.println("Client Error: " + e.getMessage());
			}
		}
}

