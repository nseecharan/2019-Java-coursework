package workshop2_pkg;

import java.util.Scanner;

public class Task3 {
	
public static void main(String[] args) {
	
	Scanner input = new Scanner(System.in);
	
	//long number = 4388576018410707L;//for debugging purposes
	
	long number = 0;
	
	//basic error checking, it's not elegant but it works
	do {
		
		System.out.println("Enter a credit card number as a long interger:");
		
		number = checklong(input);
		
		if(number != -1) {
			
			System.out.print(number + " is ");
			
			if(validator(number) == true) {
				
				System.out.println("valid");
				
			}
			else {
				
				System.out.println("invalid");	
			}	
			
		}
		else {
			
			System.out.println("only numbers 0-9, try again.");
		}	
		
		//System.out.println(number);
	}
	while(number < 0);

	input.close();
}

public static boolean validator(long num) { 
	
	/*
	Credit card numbers follow certain patterns. A credit card number must have between
	13 and 16 digits. It must start with:
	
	 4 for Visa cards
	 5 for Master cards
	 37 for American Express cards
	 6 for Discover cards
	 
	 If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is
	invalid. For example, the number 4388576018402626 is invalid, but the number
	4388576018410707 is valid.
	 
	 */
	 
	if((numdigit(num) >= 13 && numdigit(num) <= 16) && (prefix(num, 4) || prefix(num, 5) ||  
            prefix(num, 37) ||  prefix(num, 6)) && ((sumeven(num) + sumodd(num)) % 10 == 0)) {
		
		return true;
	}
	
	return false;
} 

//double the values and add even
public static int sumeven(long num) { 

	int sum = 0; 
    String num_ = num + ""; 
    
    for (int i = numdigit(num) - 2; i >= 0; i -= 2) {  
    	
        sum += digit(Integer.parseInt(num_.charAt(i) + "") * 2);
        
       //System.out.println(num_.charAt(i));//debug	
    }
    
    return sum; 
} 

// add individual numbers if the doubling is a double digit
public static int digit(int num) { 
  
	if (num < 9) { 
    
    	return num;
    }
    
    return num / 10 + num % 10; 
} 

//add odd
public static int sumodd(long num) { 
   
	int sum = 0; 
  
	String num_ = num + ""; 
    
    for (int i = numdigit(num) - 1; i >= 0; i -= 2) {  
     
    	sum += Integer.parseInt(num_.charAt(i) + "");
    	
    	//System.out.println(num_.charAt(i));//debug	
    	
    }
    
    return sum; 
} 

//return the first number
public static boolean prefix(long num, int digit) { 
	
    return get_prefix(num, numdigit(digit)) == digit; 
} 

//get length of number sequence
public static int numdigit(long digit) { 
	
    String num = digit + "";//memo: the + "" allows longs to be cast into strings/ new String will not work. 
    
    return num.length(); 
} 

//search for the beginning number
public static long get_prefix(long num, int k) { 
	
    if (numdigit(num) > k) { 
        
    	String num_ = num + ""; 
        
    	return Long.parseLong(num_.substring(0, k)); 
    } 
    
    return num; 
} 

public static long checklong(Scanner input) {
	
	long dvalue = 0;

	if(input.hasNextLong()) {
		
		dvalue = input.nextLong();	
	}
	else {
		
		dvalue = -1;
		input.next();//prevents infinite loop
	}	
	
		return dvalue;
}	

}
