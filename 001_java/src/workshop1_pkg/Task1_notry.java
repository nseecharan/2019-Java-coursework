//Name: Nigel Seecharan StudentID: 043679042 
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task1_notry {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		double[] number = new double[6];
		
		boolean again = true;
		
		while(again) {
			
			System.out.println("Task 1: Cramar's rule\n");
			System.out.print("Enter values for a, b, c, d, e, f separated by a single space: ");
			
			for(int a = 0; a < number.length; a++) {
				
				if(input.hasNextDouble()) {
					
					number[a] = input.nextDouble();	
				}	
			}
			
			double adbc = ((number[0] * number[3]) - (number[1] * number[2]));
			double x = ((number[4] * number[3]) - (number[1] * number[5])) / adbc;	
			double y = ((number[0] * number[5]) - (number[4] * number[2])) / adbc;
		
			if(adbc == 0) {
				System.out.println("The equation has no solution, try again.\n");
				input.next();
			}
			else {
				System.out.println("x is " + x + " and y is " + y + "\n");
				again = false;
				input.close();
			}		
		}				
	}		
}
