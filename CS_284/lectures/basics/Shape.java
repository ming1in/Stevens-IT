package lectures.basics;

public abstract class Shape implements Colorable {
	// Data field
	private String color;
	private String name;

	public Shape(String color) {
		super();
		this.color = color;
	}

	public Shape(String color, String name) {
		super();
		this.color = color;
		this.name = name;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return "My color is "+color;
	}
	
	public abstract double area();	
}
