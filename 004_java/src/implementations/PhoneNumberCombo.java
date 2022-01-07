package implementations;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PhoneNumberCombo {
	
	static char[][] numpad= {
			{' '},{' '},{'a','b','c'},{'d','e','f'},
			{'g','h','i'},{'j','k','l'},{'m','n','o'},
			{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
	};
	
	static Scanner input = new Scanner(System.in);
	static int[] digit2 = new int [7];
	static boolean four= false;
	static String fileName;
	
	static public void run() throws IOException {
		
		boolean again = true;
		boolean checknum = false;
		
		while(again) {
			
			System.out.print("Enter a 7 digit number ranging betwee 2 to 9 and no spaces or '-': ");
			
			String entry = input.next();
	
			if(entry.length() > 7) {
				
				System.out.println("Entry too long");	
			}
			else if(entry.length() <7){
				System.out.println("Entry too short");
			}
			else if(entry.length() == 7){
				
				for(int d = 0; d < entry.length(); d++) {
					
					if(entry.charAt(d) == '1' || entry.charAt(d) == '0') {
					
						System.out.println("0 and 1 are not allowed");
						checknum = false;
						break;
					}
					else {
						
						checknum = true;
					}
				}
				try {
					
					for(int i =0; i< 7; i++) {
						
						if(entry.length() == 7) {
							
							String temp = entry.substring(i, i+1);	
							digit2[i] = Integer.parseInt(temp);	
						}
					}
					
					if(checknum == true) {
						
						fileName = entry;
						again = false;
						input.close();	
						System.out.println("\n"
								+ "************************\n"
								+ "|| Begin writing file ||\n"
								+ "************************");
						GenFile();	
					}
				}
				catch(Exception ex) {
					checknum = false;
					System.out.println("Letters not allowed");					
				}		
			}
		}
		//old genfile code was here
	}
	//orders the results and outputs it to a file in the project route directory
	static public void GenFile() throws IOException {
		
		char a,b,c,d,e,f,g;

		for(int j = 0; j<numpad[digit2[0]].length;j++) {

			a= numpad[digit2[0]][j];
			//System.out.print("\n");//part of progress bar display/ very wide
		
			for(int k = 0; k<numpad[digit2[1]].length;k++) {
			
				b = numpad[digit2[1]][k];
			
				for (int l =0; l <numpad[digit2[2]].length;l++){
			
					c = numpad[digit2[2]][l];
					//System.out.print("\n");//part of progress bar display/ bit wider
			
					for (int m =0; m <numpad[digit2[3]].length;m++) {
				
						d = numpad[digit2[3]][m];
						System.out.print("\n");//part of progress bar display/ decent
				
						for (int n =0; n <numpad[digit2[4]].length;n++) {
					
							e = numpad[digit2[4]][n];
					
							for (int o =0; o <numpad[digit2[5]].length;o++) {
						
								f = numpad[digit2[5]][o];	
						
								for (int p =0; p <numpad[digit2[6]].length;p++) {
							
									g = numpad[digit2[6]][p];
							
									String st = a +""+ b+"" +c+""+d+""+e+""+f+""+g+" ";
									System.out.print(".");//progress bar display
									
									
									try(FileWriter outp = new FileWriter(fileName+".txt", true);){

										outp.write(st);
										outp.flush();			
									}	
								}
							}
						}
					}		
				}					
			}
		}	
		
		File fIn = new File(fileName+".txt");//used to get file size
		System.out.print("\nFile " + fileName + " Generated! File size is: " + fIn.length() + " bytes\n");
	}
	//end of all methods
}
