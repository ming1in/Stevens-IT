package lectures.basics;

public class MyList<E> {
	// data fields
	private E[] data;
	private int free; // index to next available slot
	
	// Constructor
	MyList(int size) {
		super();
		this.data = (E[]) new Object[size];
		this.free = 0;
	}
	
	// Methods

	public E get(int index) {
		if (index<0 || index>= free) { // if index doesn't exist
			throw new IllegalArgumentException();
		}
		// index is in range!
		return data[index];
	}
	
	public E add(E item) {
		data[free] = item;
		free++;
		return item;
	}
	
	public static void main(String[] args) {
		MyList<String> l = new MyList<String>(10);
		
		l.add("hello");
		l.add("hi");
		l.add("howdy");
    
    try {
		  System.out.println(l.get(2));
    } catch (IllegalArgumentException e) {
      System.out.println("oops index is out of bounds");
    }
		
		MyList<Integer> l2 = new MyList<Integer>(10);
		
		l2.add(1);
		l2.add(3);
		l2.add(7);
				
		System.out.println(l2.get(2));
		
		
		
	}
	
	
}
