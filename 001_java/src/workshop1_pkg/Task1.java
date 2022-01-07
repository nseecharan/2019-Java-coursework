//Name: Nigel Seecharan StudentID: 043679042 
//vr1	1/14/19; completed main program calculations
//vr1.1	1/17/19; implemented try catch setup for bad input handling based off of this tutorial:
//			https://www.youtube.com/watch?v=oljK1ApdeSU
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double[] number = new double[6];
		
		boolean again = true;
		
		while(again) {
			
			System.out.println("Task 1: Cramar's rule\n");
			System.out.print("Enter values for a, b, c, d, e, f separated by a single space: ");
			
			try {
			
				for(int i = 0; i <6; i++) {
				
					number[i] = input.nextDouble();
				}
		
				double adbc = ((number[0] * number[3]) - (number[1] * number[2]));
				double x = ((number[4] * number[3]) - (number[1] * number[5])) / adbc;	
				double y = ((number[0] * number[5]) - (number[4] * number[2])) / adbc;
			
				if(adbc == 0) {
					System.out.println("The equation has no solution\n");
				}
				else {
					System.out.println("x is " + x + " and y is " + y + "\n");
				}
				again = false;
				input.close();	
			}
			catch(Exception ex) {
				
				System.out.println("Error, only numbers. Try again.\n");
				input.next();//will prevent infinite loop; will not recognize enter key if this is missing
			}	
		}			
	}	
}
