package implementations;

import java.util.Date;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0, pin = -1;
	String fName ="Not Registered", lName ="N/A";
	private double balance = 0, annualInterestRate = 12;
	private Date dateCreated;
	static Account act_arr[] = new Account[10];//will not get stored in data file as is static

	//testing purposes
	public static void main(String[] args) throws IOException, ClassNotFoundException {//will change to writeFile
		
		deserialize();// will need this to display this list
	};
	
	public static void serialize() throws IOException, ClassNotFoundException {

				for(int i = 0; i < act_arr.length; i++) {
					
					act_arr[i] = new Account(i, 100);	
				}
				
				//note to self: do not write out in a for loop
				
				try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("account.dat")));) {
					
					output.writeObject(act_arr);
				}
				catch(IOException ioEX){
					
				    System.err.println("Error opening file. Terminating. A ");
				    System.exit(1);
				}

				catch(Exception ex) {
					
					 System.err.println("Error writing file. Terminating. B " + ex); 
					 System.exit(1);//will change so that user can try again
				}
				
				System.out.println("New file created. Program will continue");
	}
	
	//serialize for registration
	public static void serialize(int pin, int id, String fn, String ln) throws IOException, ClassNotFoundException {

			act_arr[id].registerAcct(fn, ln, pin, id);

		//note to self: do not write out in a for loop
		
		try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("account.dat")));) {
			
			output.writeObject(act_arr);
		}
		catch(IOException ioEX){
			
		    System.err.println("Error opening file. Terminating. A ");
		    System.exit(1);
		}

		catch(Exception ex) {
			
			 System.err.println("Error writing file. Terminating. B " + ex);
			 System.exit(1);//will change so that user can try again
			 }
		}
	
	//serialize for balance updates
	public static void serialize(double _balance, int _id) throws IOException, ClassNotFoundException {

		act_arr[_id].balance = _balance;
	
	//note to self: do not write out in a for loop
	
	try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("account.dat")));) {
		
		output.writeObject(act_arr);
	}
	catch(IOException ioEX){
		
	    System.err.println("Error opening file. Terminating. A ");
	    System.exit(1);
	}

	catch(Exception ex) {
		
		 System.err.println("Error writing file. Terminating. B " + ex);
		 System.exit(1);//will change so that user can try again
		 }
	}
	
	public static void deserialize() throws ClassNotFoundException, IOException {
		
		//second note to self: never in a loop -- Account[] record = (Account[]) input.readObject();
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("account.dat"));) {
			
			int c = 0;
			
			try{
				
				File test = new File("account.dat");
				
				if(test.exists()) {
					
					System.out.println("File exists ");
					act_arr = (Account[]) input.readObject();//re populate array with data from file
					
					while (c < act_arr.length) {
						
						System.out.println(c + ":\tAccount # " + act_arr[c].getID() + "\t| Owner: " + act_arr[c].getFullName()+ "\t| Pin:\t" 
						+ act_arr[c].getPin()+"\t| Balance:\t$" + act_arr[c].getBalance() + "\t| Monthly Interest:\t" + 
								act_arr[c].getMonthlyInterest() + "\t| Date Created:\t" + act_arr[c].getDate().toString());
							c++;
						}	
				}
				else {
					 System.out.println("1. File does not exist ");
					serialize();
				}
			}
			catch(Exception ex){

				 System.out.println("2. File error");
			}	
		}
		catch (EOFException endOfFileException) {
			System.out.println("No more records");
		}
		catch(IOException ioEX2) {
			
			System.err.println("Error opening file." + ioEX2);
			 
			 System.out.println("3. File does not exist ");
			 
			serialize();
		}	
	}
	
	public Account(){

		this.dateCreated = new Date();
	};
	
	public Account(int id, double bal){
		
		this();//calls Account()
		this.id = id;
		this.balance = bal;
	}
	
	//added this in but instructions never state where to add the names
	public void registerAcct(String fn, String ln,int pin_, int id){
		
		this.fName = fn;
		this.lName = ln;
		act_arr[id].pin = pin_;
	}
	
	public boolean checkPin(int _id) {
			
		if(act_arr[_id].pin != -1) {
			
			return true;
		}
		
		return false;
	}
	
	//mostly for debugging and possible pin confirmation message
	public int getPin() {
		
		return pin;
	}
	
	public String getFullName() {
		
		return this.fName + " " + this.lName;
		
	}
	
	public int getID() {
		
	return id;	
	}
	
	public double getBalance() {
		
		return balance;		
	}
	
	public double annualInterestRate() {
				
		return annualInterestRate;
	}
	
	public Date getDate() {
		
		return dateCreated;
	}
	
	public double getMonthlyInterestRate() {
			
		return annualInterestRate/100/12;
	}
	
	public double getMonthlyInterest() {
		
		return getBalance() * getMonthlyInterestRate();
	}
	
	public void withdraw(double wd) {
		
		balance = balance -wd;
	}
	
	public void deposit(double dp) {
		
		balance = balance + dp;
	}
	 
	public String accountDisplay(double adjustment, boolean withdrawl) {
		
		String adjust ="\nno adjsutments";
		
		if(adjustment >0) {
			
			if(withdrawl == true) {
				
				adjust = "\nWithdrew: $" + adjustment;
			}
			else if(withdrawl == false) {
				
				adjust = "\nDeposited: $" + adjustment;
			}
			else {
				
				adjust ="\nno adjsutments";
			}
			
		}
		

		return "Account\t #" + getID() +
			 adjust +
			"\n________________" +
			"\nCurrent Balance: $" + getBalance() +
			"\nMonthly Interest: $" + getMonthlyInterest();
		
	}
}
