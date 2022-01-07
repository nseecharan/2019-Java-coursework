//Name: Nigel Seecharan StudentID: 043679042 
//vr1	1/14/19; completed main program calculations
//vr1.1	1/17/19; implemented try catch setup for bad input handling based off of this tutorial:
//			https://www.youtube.com/watch?v=oljK1ApdeSU
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		boolean again = true;
		
		while(again) {
			
			try {
				
				System.out.println("Task 2: Zeller's congruence\n");
				
				System.out.print("Enter year: ");
				int year = input.nextInt();//k
				System.out.print("Enter month: 1-12: ");
				int month = input.nextInt();//m
				System.out.print("Enter the day of the month: 1-31: ");
				int dom = input.nextInt();//q
				
				//convert 1 and 2 to 13 and 14 for month per formula spec
				if(month == 1) {
					
					month = 13;
					year = year -1;
				}
				else if(month == 2) {
					
					month = 14;
					year = year -1;
				}
				
				String[] dow = {"Saturday","Sunday","Monday","Tuesday"
						,"Wednesday","Thursday","Friday"}; //h = day of week 0-6
				int century = year/100; //j = century
				
				//calculation
				
				var answer = (dom + ((26*(month + 1)) / 10) + year + ((year / 4) + (century / 4)) + (5 * century)) % 7;
	
				System.out.println("Day of the week is " + dow[answer - 1] + "\n");//compensate for answer never being 0
				
				again = false;
				
				input.close();	
			}
			catch(Exception ex) {
				
				System.out.println("Error, only numbers. Try again.\n");
				input.next();
			}	
		}	
	}	
}