//Name: Nigel Seecharan StudentID: 043679042 
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task2_notry {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		boolean again = true;
		int year = -1;
		int month = -1;
		int dom = -1;
		
		while(again) {
				
			System.out.println("Task 2: Zeller's congruence\n");	
			System.out.print("Enter year: ");
			year = checkint(input);//k
				
			if(year > -1) {
				
				System.out.print("Enter month: 1-12: ");
				month = checkint(input);//m	
			}
			
			if(month > -1) {
				
				System.out.print("Enter the day of the month: 1-31: ");
				dom = checkint(input);//q	
			}

			if(dom > -1) {
				
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
			else {
				
				System.out.println("\nPlease enter positive numbers, try again.\n");
				
				input.hasNextLine();//solves new line error		
			}	
		}
	}
	
	public static int checkint(Scanner input) {
			
		int value = 0;
	
		if(input.hasNextInt()) {
			
			value = input.nextInt();	
		}
		else {
			
			value = -1;
			input.next();//prevents infinite loop
		}	
		
			return value;
	}
}
