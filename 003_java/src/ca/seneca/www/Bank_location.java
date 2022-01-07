package ca.seneca.www;

public class Bank_location extends Bank {
	
	double balance, borrowed[], totalassets;
	int numLoans, borrowedBank[];
	
	public Bank_location() {
		
	};
	
	public Bank_location(int id) {
		
		super.bankID = id;

	};
	
}
