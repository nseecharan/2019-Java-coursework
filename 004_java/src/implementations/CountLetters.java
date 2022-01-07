package implementations;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import  java.io.FileNotFoundException;
public class CountLetters {
	
	static Scanner input = new Scanner(System.in);
	static String filename;
	static FileReader in = null;
	static int[] lettercount = new int[26];
	static char[] LETTERS = {'A','B','C','D','E','F','G','H','I',
			'J','K','L','M','N','O','P','Q','R','S','T','U','V','W',
			'X','Y','Z'};
	// wanted to do a 2d array but could not figure out how to get the
	// first dimension to be letters that would store a number. Maybe I should
	// have created a new datatype; revisit this later!

	public static void run() throws IOException {
		
		GetInput(input);
		Analyze();
		Display();
		
	}
	
	public static void GetInput(Scanner input) throws IOException {
		
		boolean again = true;
		
		while(again == true) {
			
			System.out.print("<Type checkme.txt to use the demo file>\n"
					+ "Enter the name of the file: ");
			filename = input.next();	
		
			try {

				in = new FileReader(filename);
				again = false;
	
				if(in.ready()) {
					
					input.close();
				}
			}
			catch(FileNotFoundException ex) {
				
				System.out.println("\n**There was an error**\n");	
			}				
		}	
	}
	
	public static void Analyze() throws IOException {
		
		try {
			
			int c;
			char let;
			while ((c = in.read()) != -1) {
					
				let = Character.toUpperCase((char)c);
				
				for(int i =0;i<LETTERS.length;i++) {
					
					if(let == LETTERS[i]) {
						
						lettercount[i] +=1;						
					}	
				}		
			}	
		}
		finally {
			
			if (in != null) {
				in.close();
			}					
		}		
	}
	
	public static void Display() {
		
		for(int i=0;i<LETTERS.length;i++) {
			
			if(lettercount[i] !=0) {
				System.out.println("Number of "+ LETTERS[i] + "'s: " + lettercount[i]);	
			}	
		}	
	}
}
