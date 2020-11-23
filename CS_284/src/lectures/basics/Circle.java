package lectures.basics;

public class Circle extends Shape {
	// Data fields
	private int radius;
	
	
	// Constructors
	
	public Circle(int radius, String color) {
		super(color,"DefaultName"); // Set the default color and name 
		this.radius = radius;
	}

	// Methods
	
	public double area() {
		return Math.PI*radius*radius;
	}
	
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String toString() {
		return "Radius is "+radius+". "+super.toString();
	}



	
	
	
}
