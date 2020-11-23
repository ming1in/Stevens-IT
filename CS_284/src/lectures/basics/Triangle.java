package lectures.basics;

public class Triangle extends Shape {
	// Data fields
	private int base;
	private int height;
	
	// Constructor
	public Triangle(String color, int base, int height) {
		super(color); // a call to the constructor Shape(String) 
		this.base = base;
		this.height = height;
	}
	
	// Methods 
	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Base is "+base+", height is "+height+". " +super.toString();
	}
	
	public double area() {
		return base*height/2;
	}
	
	
}
