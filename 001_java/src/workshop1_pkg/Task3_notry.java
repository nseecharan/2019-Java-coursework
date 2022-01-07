//Name: Nigel Seecharan StudentID: 043679042 
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task3_notry {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		double loan = -0.0;
		int years = -1;
		double ann_int_rate = -0.0;
		double principal, interest; 
		
		boolean again = true;
		
		while(again) {
			
			System.out.println("Task 3: Loan Payment\n");
			System.out.print("Loan Amount: ");
			loan = checkdouble(input);
			
			if(loan > -0.0) {
				
				System.out.print("Number of Years: ");
				years = checkint(input);	
			}
			
			if(years > -1) {
				
				System.out.print("Annual Interest Rate: ");
				ann_int_rate = checkdouble(input);
			}
			
			if(ann_int_rate > -0.0) {
				
				double monthly_int_rate = (ann_int_rate / 1200); //12 * 100 
				double monthlyPayment =(monthly_int_rate / (1 - (Math.pow(1 + monthly_int_rate, (years * 12) * -1)))) * loan;
				//https://brownmath.com/bsci/loan.htm#LoanPayment

				double balance = loan;

				System.out.print("\nMonthly Payment: " + Math.floor(monthlyPayment * 100d) / 100d + "\n");
				System.out.print("Total Payment: " + Math.round((monthlyPayment * 12) * 100d) / 100d  + "\n\n");
				
				System.out.println("Payment#" + "\t" + "Interest" + "\t" + "Principal" + "\t" + "Balance");
				
				for (int i = 1; i <= years * 12; i++) {
					
					interest = monthly_int_rate * balance;
					principal = monthlyPayment - interest;
					balance = balance - principal;
					
					System.out.println(i + 
							"\t\t" + Math.floor(interest * 100d) / 100d + 
							"\t\t" + Math.ceil(principal * 100d) / 100d + 
							"\t\t" + Math.ceil(balance * 100d) / 100d);
				}
			
				again = false;
				
				input.close();
			}
			else{
				
				System.out.println("\nPlease enter positive numbers greater than 0, try again.\n");
				
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
	
	public static double checkdouble(Scanner input) {
		
		double dvalue = 0.0;

		if(input.hasNextDouble()) {
			
			dvalue = input.nextDouble();	
		}
		else {
			
			dvalue = -0.0;
			input.next();//prevents infinite loop
		}	
		
			return dvalue;
	}	
}


