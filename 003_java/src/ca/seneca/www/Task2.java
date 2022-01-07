//Name: Nigel Seecharan StudentID: 043679042 

package ca.seneca.www;

import java.util.Scanner;

public class Task2 {

	Bank_location a[];
	
	static double asset_limit;
	double balance,borrowed;
	int numOfBanks,loans,bankID;//n

	public static void main(String[] args) {

		Task2 tsk2 = new Task2();
		Scanner input = new Scanner(System.in);

		tsk2.setNumOfBanks(input);
		tsk2.setLimit(input);
		//System.out.println(String.format("%15s", Task2.asset_limit+""));
		//System.out.print("2000");
		tsk2.storeID();
		tsk2.bankInfo(input);
		tsk2.display();
		tsk2.warnings();
	
		input.close();
	}
	
	//*********METHODS*************//
	
	void setNumOfBanks(Scanner in) {

		boolean loop = true;
		int num = 0;
		
		System.out.print("Number of banks [positive values only]: ");
		
		while(loop){
			
				num = checkI(in);
				
				if(num > 0) {
					
					a = new Bank_location[num];
					
					loop = false;
				}
				else if(num == 0){
					
					System.out.println("\n**There must be at least 1 bank**\n");
					System.out.print("Number of banks [positive values only]: ");	
				}
				else{

					System.out.print("Number of banks [positive values only]: ");
				}
		}	
	}
	
	//set the asset limit; all functions circumvent static/non static errors
	void setLimit(Scanner in) {

		boolean loop = true;

		while(loop){
			
			System.out.print("Minimum asset limit [positive values only]: ");
				
				double num = checkD(in);
				
				if(num > 0.00) {
					
					Task2.asset_limit = num;
					//eclipse suggested to use class Task2 instead of using 'this'
					
					loop = false;
					
				}
				else if(num == 0){
					
					System.out.println("\n**A limit must be be specified**\n");
					System.out.print("Minimum asset limit [positive values only]: ");
				}
		}	
	}
	
	//auto generate bank ID's
	void storeID() {

		int i = 0;
			
		while(i<a.length){
				
			a[i] = new Bank_location(i);
			
			i++;
		}		
	}

	void bankInfo(Scanner in) {
		
		double bal = 0, brw = 0;
		int loan = 0, id = 0;
	
		for(int b = 0; b < a.length; b++) {
			
			System.out.print("\n--Bank # " + a[b].bankID + "--\nCurrent balance:");	
			bal = checkD(in);
			
			a[b].balance = bal;
			a[b].totalassets = bal;
			
			System.out.print("Number of loans given: ");
			loan = checkI(in);

			//testing input for id
			a[b].numLoans = loan;	
			
			if(loan > 0) {
				
				for(int l = 0; l < loan; l++) {

					boolean loop = true;
					
					while(loop) {	
						System.out.print("Bank ID: ");
						id = checkI(in);

						try {
							if(id != b && id == a[id].bankID) {
								loop = false;
							}
							else if(id == b) {
								System.out.println("\n**A bank can not loan money to itself. Choose another ID**\n");	
							}
						}
						catch(Exception bounds) {
							System.out.println("\n**Bank ID not found**\n");
						}
					}

					if(a[b].borrowed == null) {
						
						a[b].borrowed = new double[loan];
					}
					if(a[b].borrowedBank == null) {
						
						a[b].borrowedBank = new int[loan];	
					}
						
					System.out.print("Borrowed amount: ");
					brw = checkD(in);
					
					a[b].borrowedBank[l] = id;
					a[b].borrowed[l] = brw;
					a[b].totalassets += brw;

				}	
			}		
		}	
	}
	
	//display loan report in a table like presentation
	void display() {

		System.out.println("\n--REPORT--");
		
		for(int i =0; i < a.length; i++) {
	
			System.out.println("*******************************************");
			System.out.println("Bank # " +i + "| Balance: " + a[i].balance + "| Loans given: " + a[i].numLoans + "|");
			
			if(a[i].borrowed != null) {
				
				for(int j =0; j < a[i].borrowed.length; j++) {
					
					if(a[i].borrowed != null) {
						
						System.out.print("-- loaned to bank # " + a[i].borrowedBank[j]);
						System.out.println(":\t" + String.format("%10s", a[i].borrowed[j]+""));
					}
				}	
			}
			
			System.out.println("Total Assets: " + a[i].totalassets);
		}
		
		System.out.println("*******************************************");
	}	
		
	void warnings() {

		System.out.println("\n--WARNINGS--");

		int unsafe = -1;
		double deductions = 0, temptotal = 0;
		
		//searches for initial conflicts
		for(int i = 0; i < a.length; i++) { 
			
			if(a[i].borrowedBank != null) {
				
				if(a[i].totalassets< Task2.asset_limit) {

					System.out.println("Bank # " +a[i].bankID + " is unsafe: Below Mininum Limit");
					
					unsafe = a[i].bankID;
					}
				}
			}
		if(unsafe != -1) {
			
			//search for secondary conflicts
			for(int j =0; j < a.length; j++) { 
				
				for(int k = 0; k < a[j].borrowedBank.length; k++) {
					
					if(a[j].borrowedBank[k] == unsafe) {
						
						deductions = a[j].borrowed[k];
						temptotal = a[j].totalassets;//previous assets
						a[j].totalassets = temptotal - deductions;//Readjustments
						
						if(a[j].totalassets < Task2.asset_limit) {
							//String.format("%15s", Task2.asset_limit+"")
							System.out.println("Bank # " +a[j].bankID + " is unsafe: Bank # " + unsafe + " defaulted");
							System.out.println("\n--Bank # " +a[j].bankID + " Reassessment Breakdown--");	
							System.out.println("Loaned bank # "+ unsafe + ":\t\t" + String.format("%10s", a[j].borrowed[k]+""));
							System.out.println("Bank # "+ unsafe + " deductions:\t\t"+ String.format("%10s", deductions+""));
							System.out.println("Assets before deductions:\t"+ String.format("%10s", temptotal+""));
							System.out.println("Assets after deductions:\t"+ String.format("%10s", a[j].totalassets+""));
							}
						}
					}
				}
		}
		else {
			
			System.out.println("None");
		}
	

		}
	
	//the methods below check for incorrect input
	double checkD(Scanner in) {
		
		double dvalue = -1;
		
		try {
			
			dvalue = in.nextDouble();
			
		}
		catch(Exception ex) {
			
			System.out.println("\n**Invalid input. Please enter a positive number with or without decimals**\n");	
			in.next();
		}
		
		return dvalue;	
	}
	
	int checkI(Scanner in) {
		
		int ivalue = -1;
		
		try {
			
			ivalue = in.nextInt();
			
		}
		catch(Exception ex) {
			
			System.out.println("\n**Invalid input. Please enter a positive whole number**\n");
			in.next();
		}
		
		return ivalue;	
	}	
}

