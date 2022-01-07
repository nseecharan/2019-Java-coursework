//Name: Nigel Seecharan StudentID: 043679042 

package task3_tester;

import Geo_methods.GeometricObject;
import shapes.Square;

public class Task3Test {
	
public static void main(String [] args) {
	
	//hard coded values for ease
	GeometricObject[] geo_arr = {
			new Square(2,5.5, "X"),
			new Square(4.5, 3, "c"),
			new Square(7.7, 6.4),
			new Square(5.2, 7, "C"),
			new Square(6.5, 9)};
	
	GeometricObject geo = new GeometricObject();
	
	for(int i=0; i <geo_arr.length; i++) {
		//access the objects in the array
		//pass values of each objects overloaded constructor to a super class' method
		//invoke overridden method from the subclass
		//Demonstration purposes so no input. See task2 for a more rigorous attempt at exception handling
		System.out.print("The area for Shape "+ (i +1)+ " is: " + geo.get_area(geo_arr[i].width(), geo_arr[i].height()));
		System.out.print(" /");
		geo_arr[i].howToColor();
		}	
	}	
}
