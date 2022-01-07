package implementations;

import java.util.Scanner;

public class BeanMachine {
	
	static int beans = 0;
	static int rows =0;
	static String bin[][];
	static Scanner input = new Scanner(System.in);
	
	
	public static void run(){
		
		boolean tryagain = true;
		
		
		while(tryagain) {
			
			boolean again = true;

			while(again) {
				
				try {
					System.out.print("Please enter the number of balls to drop: ");
					
					beans = input.nextInt();
					again = false;

					}
					catch(Exception ex) {
					
					System.out.println("\n**Use positive whole numbers please**\n");
					
					input.next();
					}
				}
		
			again = true;
			
			while(again) {
				
				try {
					System.out.print("Please enter the number of slots in the bean machine: ");
					
					rows = input.nextInt();
					again = false;

					}
					catch(Exception ex) {
					
					System.out.println("\n**Use positive whole numbers please**\n");
					
					input.next();
					}		
				}
			
			drop(beans, rows);
			
			again = true;
			
			while(again) {
				
				System.out.print("Try again? (Y/N): ");
				
				String decision = input.next();
				
				if(decision.toUpperCase().charAt(0) == 'Y') {
					
					again = false;	
				}
				else if(decision.toUpperCase().charAt(0) == 'N') {//always to do .charAt!!
					
					again = false;
					tryagain = false;
					System.out.println("Goodbye!");		
				}
				else {
					
					System.out.print("Please enter a 'Y' or 'N'. Try again? (Y/N): ");
					input.next();		
				}
				
			}
		}

		input.close();

	}
	
	
	public static void drop(int beans, int rows) {
		
		int bounce = 0;
		int right = 0;
		
		bin = new String[beans][rows];
		
		//clean up the nulls
		for(int z = 0; z < bin.length; z++) {
			
			for(int y = 0; y<bin[z].length; y++) {
				
				if(bin[z][y]==null) {
				
				bin[z][y] = " ";
				}
			}
		}

		System.out.println("<start>");
		
		//get right progression
		for(int i = 0; i<beans; i++) {
				
			bounce = 0;
			right = 0;
			
			while(bounce < rows -1) {
					
				if(Math.random() < 0.5) {
					
					System.out.print("L");	
				}
				else {
					
					System.out.print("R");
					right++;	
				}
				
				bounce++;
				}
				
				System.out.println(" "+ right);
				
				//will store a 0 only if the space is " " and not already occupied by a 0
				//otherwise it will move down the array. Results get printed in reverse.
				int pos = 0;
				boolean again = true;
				while(again) {
					
					if(bin[pos][right] == " ") {
						
						bin[pos][right] = "0";
						again = false;
					}
					else {
						
						pos++;				
					}			
				}
			}	
		System.out.println();
	
		//output
		for(int j = bin.length-1; j>-1;j--) {
		
			System.out.print(j+" ");
			
			for(int k = 0; k<bin[j].length; k++){
				
				System.out.print(bin[j][k]);	
			}
			
			System.out.println();
			}
		System.out.print("  ");
		for(int l = 0; l<rows;l++) {
			
			System.out.print(l);
			}
		System.out.println("\n");
		}
	}


