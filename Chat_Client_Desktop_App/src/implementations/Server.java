package implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
static int clientCount=0;
public static String messagelog ="";
static int linenum=0;
	
public static ArrayList<EchoT> clist = new ArrayList<EchoT>();

	public static void main(String[] args) {
	//used for testing	

		try(ServerSocket server = new ServerSocket(4000)) {

			System.out.println("Connected to server");
			
			while(true) {

				System.out.println("Client Connected");

				clientCount++;
				EchoT t = new EchoT(server.accept(), clientCount);
				t.setName("TClient "+clientCount);
				
				clist.add(t);	
			
				t.start();
			}
		}
		catch (IOException e) {
			
			System.out.println("Failed to connect to server" + e.getMessage());
		}
	}
}

class EchoT extends Thread {
	
	public static String msgString ="";
	private Socket skt;
	static String tstr="";
	int cCount = 0;
	
	BufferedReader input;
	PrintWriter writer;
	String echoString;

	public EchoT(Socket socket, int num) {	
		
		this.skt = socket;	
		this.cCount = num;
	}

	public void run() {

		try {
			input = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			writer = new PrintWriter(skt.getOutputStream(), true);
			
			while(true) {
				
				//received from client
				echoString = input.readLine();
				
				//sent to server
				System.out.println(echoString);
				
				//debug
				Server.messagelog += echoString+"\n";

				int i = 0;
				for(EchoT et : Server.clist) {

					Server.clist.get(i).writer.println(echoString+"\n");				
		
					i++;
				}
							
				System.out.println(Server.clist.stream());	
				
				//writer.println("Client Input " + this.cCount + ": "+ echoString+"\n");	
		
				writeLog(echoString+System.getProperty( "line.separator" ));
				
				//will make option in tool bar
				if(echoString.equalsIgnoreCase("exit")) {
					
					skt.close();

					return;
				}		
			}				
		} catch (IOException e) {
			
			System.out.println("Sorry there was a error: " + e.getMessage());
			System.out.println(Server.clist);
			System.out.println(Thread.currentThread());
			
			Server.clist.remove(Thread.currentThread());
			Server.clientCount--;
			
			try {
				
				Thread.currentThread().join();
				
			} catch (InterruptedException e1) {
	
				System.out.println("Stop thread error: " + e1.getMessage());
			};
		}
		finally {
			
			try {
				
				skt.close();
				
			} catch (IOException e) {
				
				System.out.println("Closing error: " + e.getMessage());
			}
		}	
	}
		
//server writes a chat log	
	public static void writeLog(String s) {
		
		try {
			
			File file = new File("ChatLog.txt");
			FileWriter fw = new FileWriter(file, true);
			fw.append(s);
			fw.flush();
			fw.close();
			
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		
		System.out.println("Write log test:\n"+Server.messagelog);
	}
}