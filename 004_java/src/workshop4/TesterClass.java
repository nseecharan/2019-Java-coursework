package workshop4;

import java.io.IOException;
import java.util.Scanner;
import implementations.CountLetters;
import implementations.Hangman;
import implementations.PhoneNumberCombo;

public class TesterClass {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		boolean again = true;
		
		System.out.println("Nigel Seecharan ID: 043679042"
				+ "\n\n"
				+ "||Workshop 4 Tester Program||\n");
		
		while(again) {
			
			try {
				System.out.println("Please choose an option from the list:\n"
						+ "1: Hangman\n"
						+ "2: CountLetters\n"
						+ "3: PhoneNumberCombo");
				System.out.print("-->");
				
				int choice;
				choice = input.nextInt();
				
				if(choice > 3 || choice < 1) {
					
					System.out.println("\n**Pick either options 1, 2, or 3**\n");
				}
				else {
					
					switch(choice) {
					
					case 1:
						again = false;	
						System.out.println("");
						Hangman.run();
						break;
					case 2:
						again = false;
						System.out.println("");
						CountLetters.run();
						break;
					case 3:
						again = false;	
						System.out.println("");
						PhoneNumberCombo.run();
						break;
					}
				}
			}
			catch(Exception ex) {
				
				System.out.println("\n**Use numbers please**\n");
				
				input.next();	
			}			
		}
		
		System.out.println("\nProgram has ended\n");
		input.close();
	}	
}
