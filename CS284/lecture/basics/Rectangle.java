/**
 * This class models rectangles
 * @author Ming Lin
 *
 */
public class Rectangle extends Shape {
	// Data fields
	private int base;
	private int height;
	
	/*
	 * Class fields. 
	 * Note: Class fields are also known as static fields.
	 */
	private static int no_of_instances=0;
	
	// Constructor
	Rectangle(int base, int height)  {
		super("Blue", "DefaultName");  // set the color to the default value of "Blue"
		this.base=base;
		this.height=height;
		no_of_instances = no_of_instances+1;
	}
	
	Rectangle(int base, int height, String color)  {
		this(base,height); // call the two argument constructor from above
		super.setColor(color);
	}
	
    // methods
	
	public int getHeight() {
		return height;
	}

	public int getBase() {
		return base;
	}

	/**
	 * Updates the base of the rectangle
	 * @param base the new base
	 */
	public void setBase(int base) {
		this.base = base;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Computes the area of a rectangle
	 * @return the area
	 */
	public double area() {
		return base*height;
	}
	
	public String toString() {
		return "Base is "+base+" and height is "+height+". "+super.toString();
	}
	// class methods
	
	public static int getNoOfInstances() {
		return no_of_instances;
	}
	
	public Pair<Integer,Integer> getBaseHeight() {
		return new Pair<Integer,Integer>(base,height);
	}
	
	public Pair<Integer,String> getBaseAndColor() {
		return new Pair<Integer,String>(base,this.getColor());
	}

}
	


