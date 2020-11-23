package lectures.basics;

public class PlayingWithShapes {

	public static void printColor(Colorable r) {
		System.out.println(r.getColor());
	}
	
	public static void main (String[] args) {

//		Rectangle r1 = new Rectangle(12,7,"Blue");
//		System.out.println(r1.getBaseHeight().getY());
//		System.out.println(r1.getBaseAndColor().getY());
//		
//		
		
//	    printColor(r1);
	    
	    
	    
//		Circle c1 = new Circle(3,"Green");
//		printColor(c1);
//		
//		Apple a1 = new Apple("Red",4);
//		printColor(a1);
//		
//		Rectangle r2 = new Rectangle(4,9);
//		//		r.setHeight(10);
//		System.out.println("Rectangle r1 has height: "+r1.getHeight());
//		System.out.println("Rectangle r2 has height: "+r2.getHeight());
//		System.out.println("Rectangle r1 has height (again): "+r1.getHeight());
//		System.out.println("The number of rectangles created up until now is "+ Rectangle.getNoOfInstances());
//		System.out.println("The color of r1 is "+r1.getColor());
//		
//		Circle c1 = new Circle(3,"Green");		
//		System.out.println(c1);
		
//		Shape s = new Shape("Orange");
//		
//		System.out.println(s);
//		
//		Triangle t1 = new Triangle("Green",10,12);
//		
//		System.out.println(t1);
		
		Shape[] rs = new Shape[4];
		
		rs[0] = new Rectangle(1,2,"Blue");
		rs[1] = new Rectangle(7,4,"Green");
		rs[2] = new Rectangle(9,12,"Red");
		rs[3] = new Circle(3,"Orange");
		
		for (Shape r:rs ) {
			System.out.println(r.getColor());
		}
	}
}
