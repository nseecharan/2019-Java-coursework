package implementations;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException; 

public class Hangman {

	static int incorrect =0;//counts all correct/incorrect attempts
	static String[] words;//= {"program","binary","arrays"};
	static char [] dspWRD;//this is the masked out word 
	static char[] letterbin = new char[26];
	static boolean again = true;
	static boolean startmsg=true;

	//methods
	
	public static void run() throws IOException{
		
		BufferedReader in = null;
		try {
			
			in = new BufferedReader(new FileReader("you'll_never_guess.txt"));
			String buffer;
			
			while ((buffer = in.readLine()) != null) {
				words = buffer.split(" ");
				}	
		}
		finally {
			
			if (in != null) {
				in.close();
				} 
		}
		
		Scanner input = new Scanner(System.in);
		int count =0;
		
		while(again) {
			//Displays the first prompt message and populates a char array that will gradually display the word
			if(startmsg) {
				
				count = (int)(Math.random()*3);
				dspWRD = new char[words[count].length()];
				
				//System.out.println("DEBUG PURPOSES: " + words[count]);//debug
				
				System.out.println("Task 1: Hang Man\n");
				System.out.print("(Guess) Enter a letter in word "); 
				
				//initial censored word display
				for(int i=0;i<words[count].length();i++) {

						dspWRD[i]= '*';//populate char array with *
						System.out.print("*");//or could have used dspWRD
						}
				
				System.out.print(" >"); 
				
				startmsg=false;
			}
	
			hangman(words[count],input);
		}			
	}
	
	
	//the hangman prompt
	public static void hangman(String arg, Scanner input) {
		
		char letter = input.next().charAt(0);	
		String _word;

		if(lettercheck(letter) == true) {
			
			System.out.print("That letter has already been chosen!\n");	
		}
		
		//checking for incorrect letters
		if(miss(letter, arg) == true) {
			
			System.out.print("Miss! try again!\n");
		}
		
		System.out.print("(Guess) Enter a letter in word ");
		
		for(int i=0;i<arg.length();i++) {

			if(arg.charAt(i) == letter) {
			
				dspWRD[i] = arg.charAt(i);
				}
		}
		
		_word = new String(dspWRD);//converted char array to string for compare condition
		
		System.out.print(_word);
		System.out.print(" >");
		
		//use of compareTo to create an escape condition
		int val = _word.toUpperCase().compareTo(arg.toUpperCase());
		
		if(val == 0) {
			
			retry(_word);
		}
		else {
			
			input.hasNextLine();//solves new line error	
		}
	}
	
	//check for duplicate choices
	public static boolean lettercheck(char c) {
		
		for(int i= 0; i < dspWRD.length; i++) {
			
			if(c == dspWRD[i]) {
				
				return true;
			}	
		}
		
		return false;
	}
	
	//count misses
	public static boolean miss(char c, String s) {
	
	int count = 0;
	
	for(int i =0; i < s.length(); i++) {
			
		if(c != s.charAt(count)) {

			if(count < s.length()) {
				
				count++;
			}	
		}	
	}	
	if(count >= s.length()) {
		
		incorrect++;
		count = 0;
		return true;
	}
	
	return false;	
	}
	
	//will prompt the user with the 
	public static boolean retry(String c_word) {
		
		System.out.println("\nThe word is " + c_word + ". You missed " + incorrect + " time");
	
		System.out.println("Do you want to guess another word? Enter y or n>");
		Scanner input = new Scanner(System.in);
		
		boolean decision = true;
		boolean canloop = true;
		do {
			
			String prompt = input.nextLine();
			
			switch(prompt) {
			
			case "y":
				decision = false;
				canloop = false;
				startmsg = true;
				break;
			case "n":
				decision = true;
				canloop = false;
				System.out.println("Goodbye!");
				again = false;
				input.close();
				break;
			default:
				canloop = true;
				System.out.println("Please enter y or n >");
				input.hasNextLine();//solves new line error	
				break;	
			}
		}
		while(canloop);
					
		return decision;
	}	
}


	
	


	

