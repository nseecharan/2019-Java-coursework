//Name: Nigel Seecharan StudentID: 043679042 
//vr1	1/14/19; completed main program calculations
//vr1.1	1/17/19; implemented try catch setup for bad input handling based off of this tutorial:
//			https://www.youtube.com/watch?v=oljK1ApdeSU
//vr1.2	1/18/19; implemented an alternate method for bad input

package workshop1_pkg;

import java.util.Scanner;

public class Task3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		boolean again = true;
		
		while(again) {
			
			try {
				
				System.out.println("Task 3: Loan Payment\n");
				System.out.print("Loan Amount: ");
				double loan = input.nextDouble();
				System.out.print("Number of Years: ");
				int years = input.nextInt();
				System.out.print("Annual Interest Rate: ");
				double ann_int_rate = input.nextDouble();
				
				double monthly_int_rate = (ann_int_rate / 1200); //12 * 100 
				double monthlyPayment =(monthly_int_rate / (1 - (Math.pow(1 + monthly_int_rate, (years * 12) * -1)))) * loan;
				//https://brownmath.com/bsci/loan.htm#LoanPayment

				double balance = loan;
				
				double principal, interest; 
						
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
			catch(Exception ex){
				
				System.out.println("Error, only numbers. Try again.\n");
				input.next();//will prevent infinite loop; will not recognize enter key if this is missing	
			}		
		}
		
		System.out.println("\n");
	}	
}
