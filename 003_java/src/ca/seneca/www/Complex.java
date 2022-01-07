//Name: Nigel Seecharan StudentID: 043679042 

package ca.seneca.www;

import java.util.Scanner;

public class Complex implements Cloneable{
	
	private double a,b;
	
	
	/*
	 * a = first1 -- real
	 * b = first2 -- imaginary
	 * c = second1 -- real
	 * d = second2 -- imaginary
	 * i = 2-1
	 */
	
	public static void main(String [] args){
		
		Complex clone_c = null;
		
		Complex complex = new Complex();
		//complex.a = 3.5;
		//complex.b = 5.5;
		
		Scanner input = new Scanner(System.in);
	
		boolean again = true;
		int count = 0;
		
		double tA, tB;
		
		while(again){
			
			try{
				
				if(count == 0) {
					
					System.out.print("Enter the first complex number: ");
				}
				if(count == 1) {
					
					System.out.print("Enter the second complex number: ");
				}
				
				if(count < 2) {
					
					tA = input.nextDouble();
					tB = input.nextDouble();
					
					complex.a = tA;
					complex.b = tB;
					
					//System.out.println("*initialized private members*");
					
				}
					
				if(count == 0) {
					
					//System.out.println("*cloneing*");
					
					try {
						clone_c = (Complex)complex.clone();

					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						System.out.println("*error cloneing did not work*");
						e.printStackTrace();
					}		
				}
				if(count == 2) {
					
					//System.out.println("*Display*");
					char symbol[] = {'+','-','*','/'}; //is this overkill?
					for(int i=0;i<4;i++) {
			
						System.out.print("("+clone_c.toString()+") " +symbol[i]+" (" +complex.toString()+") = ");
						
						if(symbol[i] == '+') {
							
							System.out.print(complex.add(clone_c.a, clone_c.b, complex.a, complex.b));	
						}
						if(symbol[i] == '-') {
							
							System.out.print(complex.subtract(clone_c.a, clone_c.b, complex.a, complex.b));	
						}
						if(symbol[i] == '*') {
							
							System.out.print(complex.multiply(clone_c.a, clone_c.b, complex.a, complex.b));
						}
						if(symbol[i] == '/') {
							
							System.out.print(complex.divide(clone_c.a, clone_c.b, complex.a, complex.b));	
						}
					
						System.out.print("\n");
					}
					
					//just to show the getRealPart and getImaginaryPart works
					System.out.print("|(3.5 + 5.5i)| = " + complex.abs(clone_c.getRealPart(), clone_c.getImaginaryPart()) + "\n");	
				}
				
				if(count >2) {
						
					
					String confirm ="";
					boolean redo = true;
					
					System.out.println("Calculations completed. Enter new values? (Y/N)");
					
					do {
		
						 confirm = input.next();
						 
						 //memo: Y or N prompts must be set to a char for them to work.
						 if(confirm.toLowerCase().charAt(0) == 'y') {
							 
							 redo = false;
							 count = 0;
						 }
						 else if(confirm.toLowerCase().charAt(0) == 'n') {
							 
							 System.out.println("Good bye!");
							 again = false;
							 redo = false;
							 input.close();
						 }
						 else {
							 
							 System.out.println("Answer using Y or N in either upper or lower case. Enter new values?"); 	 
						 }					
					}
					while(redo);
				}
				else {
					
					count++;
					
					//System.out.println("*count: " + count + "*");
				}		
			}
			catch(Exception ex) {
				
				System.out.println("\nTry again");
				input.next();
			}
		}
	}
	
	public Complex() {
		
		this.a =0;
		this.b =0;

	};
	
	//Could be used to initialize the current objects where a only has a value,
	//as performed by the overridden toString() method. Did not use it though.
	public Complex(double a) {
		
		this.a = a;
		this.b = 0;	
	};
	
	//not used but be used to initialize the private data members after input checks.
	public Complex(double a_, double b_) {
		
		this.a = a_;
		this.b = b_;
	};
	
	
	//add, sub, multi, div, abs methods??
	
	public String add(double a, double b, double c, double d) {
		
		
		double add1 = a + c;
		double add2 = b + d;
		
		return add1 + " + " + add2 + "i";	
	}
	
	public String subtract(double a, double b, double c, double d) {
		
		
		double sub1 = a - c;
		double sub2 = b - d;
		
		return sub1 + " + " + sub2 + "i";	
	}
	
	public String multiply(double a, double b, double c, double d) {
		
		double ac = (a * c);
		double bd = (b * d);
		double bc = (b * c);
		double ad = (a * d);
		
		
		double multi1 = ac - bd;
		double multi2 = bc + ad;
		
		return multi1 + " + " + multi2 + "i";	
		
		//http://www.webmath.com/cn_mult.html
		//the example in the lab spec may be wrong I tried the same values
		//with the calculator by entering (3.5+5.5i)(-3.5+1i) 
		//and got -17.75-15.75i
	}
	public String divide(double a, double b, double c, double d) {
		
		double ac = (a * c);
		double bd = (b * d);
		double bc = (b * c);
		double ad = (a * d);
		
		
		double div1 = (ac + bd) / (Math.pow(c, 2) + Math.pow(d, 2));
		double div2 = (bc - ad) / (Math.pow(c, 2) + Math.pow(d, 2));
		
		//no clue which rounding method to use
		return div1 + " + " + div2 + "i";		
	}
	
	public String abs(double a, double b) {
		
		double val = Math.pow(a, 2) + Math.pow(b, 2);
	
		return Math.sqrt(val) +"";		
	}
	
	public double getRealPart() {
		
		
		return this.a;
	};
	
	
	public double getImaginaryPart() {
		
		
		return this.b;
	};
	
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		}  
	
	@Override
	public String toString() {

		if(this.b != 0) {
			
		
			return this.a + " + " + this.b + "i";
		}	
		//could use Complex cx = new Complex(a); and return cx but this is overkill right?
		return this.a+"";
	}	
}
