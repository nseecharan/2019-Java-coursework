package workshop2_pkg;

//import java.util.Scanner;

public class Luhn {

	
	//static String number = "4388576018410707";
	//https://en.wikipedia.org/wiki/Luhn_algorithm
	
public static void main (String[] args) {
	
	String number = "4388576018410707";//hardcoded for debugging
	
	
	
	//Scanner input = new Scanner(System.in);
	
	System.out.println("Enter a credit card number as a long interger:");
	
	//long number = input.nextLong();
	
	System.out.print(number + " is ");
	
	if(check(number) == true) {
		
		System.out.println("valid");
		
	}
	else {
		
		System.out.println("invalid");	
	}	
	
	//input.close();
	
	
	//System.out.print(check(number));	
}
	
	
	 public static boolean check(String ccNumber)
     {
             int sum = 0;
             boolean alternate = false;
             for (int i = ccNumber.length() - 1; i >= 0; i--)
             {
                     int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                     if (alternate)
                     {
                             n *= 2;
                             if (n > 9)
                             {
                                     n = (n % 10) + 1;
                             }
                     }
                     sum += n;
                     alternate = !alternate;
             }
             return (sum % 10 == 0);
     }
	       
	
	
}
