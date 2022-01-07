package shapes;

import Geo_methods.GeometricObject;
import implementations.Colorable;

public class Square extends GeometricObject implements Colorable{

	private double width, height;
	private boolean color;
	
	public Square() {
		
		this.width = 1;
		this.height = 1;
		
	};
	
	public Square(double w, double h) {
		
		this.width = w;
		this.height = h;
		
	}
	
	public Square(double w, double h, String c) {
		
		this.width = w;
		this.height = h;
		
		if(c.toLowerCase().charAt(0) == 'c') {
			
			color = true;
			
		}
		else {
			
			color = false;
		}
	}
	
	public double height() {
		
		return height;
		
	}
	
	public double width() {
		
		return width;
	}
	
	@Override
	public void howToColor() {
		
		if(color == true){
			
			System.out.println(" Color all four sides");	
		}
		else {
			
			System.out.println(" Not colored");
		}
		
		
		
	};
}
