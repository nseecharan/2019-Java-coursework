package workshop2_pkg;

/*
23.5 35 2 10
4.5 3 45 3.5
35 44 5.5 9.6
*/

import java.util.Scanner;

public class Location {
	
	static double row, column, maxValue;
	static int rowpos = 0, colpos = 0;
	
	public static void main(String[] args) {
		
	Scanner input = new Scanner(System.in);

	System.out.print("Enter the number of rows and columns in the array: ");	

		row = checkdouble(input);
		column = checkdouble(input);
		
		double array[][] = new double [(int)row][(int)column];
		

				
			for(int i= 0; i < array.length; i++) {
				
				System.out.println("Enter row " + (i+1) + " of the array");	
				
				for(int j = 0; j < array[i].length; j++) {
					
					array[i][j] = input.nextDouble();
				}
			}
			input.hasNextLine();//solves new line error

		
		System.out.println("\n***The Matrix***");	
		
		//printing from pdf
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
			System.out.print(array[row][column] + " ");
			}
			  System.out.println(); 
			}
		System.out.println("\n***The Matrix***\n");	
		int r=0, c=0;
		double m = 0.0;
		r = locateLargest(array).rowpos;
		c = locateLargest(array).colpos;
		m = locateLargest(array).maxValue;
		
		System.out.print("The location of the largest element is " + m + " at (" + r + ", " + c + ")");	
	}
	
	
	public static Location locateLargest(double[][] a) {
	Location result = null;	
		
	
	for(int i =0; i < a.length; i++) {
		
		for(int j = 0; j<a[i].length; j++) {
			
			if(result.maxValue < a[i][j]) {
			result.maxValue= a[i][j];
			result.rowpos = i;
			result.colpos = j;
			}
		}
	}

	return result;	
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

