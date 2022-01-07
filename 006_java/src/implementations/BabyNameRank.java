package implementations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class BabyNameRank {

	private static int year = 0;
	static String person[];
	static ArrayList<String> list = new ArrayList<>();
	static String resultMSG = "";
	private static String FileName ="";
	
	
	//public static void main(String[] args) throws FileNotFoundException, IOException {

		//for testing
		//getFile("2010");
		//getName("Emily", "M");
		//getName("EmIly", "F");
		//getName("Jacob", "M");
		//getName("jacob", "F");
	//}
	
	//retrieves the file based on the year entered
	public static boolean getFile(String yr) {
	
		year = 0;
		FileName ="";
		person = null;
		list.clear();
		
		try {
			year = Integer.parseInt(yr);
			
			if((year> 2000) && (year< 2011)) {
				
				FileName = "src/rankings/babynamesranking"+year+".txt";
				System.out.println("\n" + FileName);
				
				try(BufferedReader br = new BufferedReader(new FileReader(FileName))){

					int count = 0;

					while(count <1000) {

						list.add(br.readLine());

						count++;
					}
					return true;
				}
				catch(FileNotFoundException nofex) {
					
					System.out.println("File was not found");
					return false;
				}
				catch(IOException e) {
					
					System.out.println("Could not read file");
					return false;
				}
			}
			else {

				resultMSG = "Enter a year ranging from 2001 to 2010";
			}
		}
		catch(NumberFormatException numex){
			//terminal output suggests this happens twice-- figure this out to avoid redundant procedures - everything works though
			resultMSG ="Please use numbers for the year";
			System.out.println(resultMSG);
			
			year = 0;
			
			return false;
		}	
		
		return false;
	}
	
	//get the name and the ranking info
	public static void getName(String nm, String gen) {
		
		resultMSG = "";
		String nameData = "";
		
		String name = nm.toUpperCase();
		String gender = gen.toUpperCase();

		try {
			
			nameData = list.stream()
					.filter(str->str.toUpperCase().matches("(.*)"+name+"(.*)")).findFirst().get().toUpperCase();
			
			if(nameData.indexOf(name) < nameData.length()) {
				
				person = nameData.split("[ 	]");
			}
			
			if(gender.charAt(0) == 'M') {
				
				if(person[5].equals(name.toUpperCase())) {
					
					System.out.println("Gender miss match");
					
					resultMSG = "Sorry the name "+nm+" was not found in the boy category";
				}
				else {
					
					resultMSG = "Boy name " + person[2].substring(0,1)+person[2].substring(1,person[2].length()).toLowerCase()+
							" was ranked #" + person[0]+
							" in " + year + ",\nwith " + person[3] + " boys having this name";
				}	
			}
			else if(gender.charAt(0) == 'F') {
				
				if(person[2].equals(name.toUpperCase())) {
					
					System.out.println("Gender miss match");
					
					resultMSG = "Sorry the name "+nm+" was not found in the girl category";	
				}
				else {
					
					resultMSG = "Girl name " + person[5].substring(0,1)+person[5].substring(1,person[5].length()).toLowerCase()+
							" was ranked #" + person[0]+
							" in " + year + ",\nwith " + person[3] + " girls having this name";	
				}	
			}
			else {
				
				resultMSG = "Please enter only the letter M or F";		
			}		
		}
		catch(StringIndexOutOfBoundsException iex) {
			
			//doubtful this will ever trigger but here as an extra precaution
			System.out.println("From getname: " + iex);
		}
		catch(NoSuchElementException neex) {
			
			System.out.println("From getname: " + neex);
			resultMSG ="Sorry name not found. Names are common to America";
		}
		
		System.out.println(resultMSG+"\n");
	}

	public static String message() {

		return resultMSG;
	}
}
