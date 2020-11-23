package lectures.basics;

public class Pair<E, F> {
	// data fields
	private E x;
	private F y;
	
	// Constructor
	Pair(E x, F y) {
		super();
		this.x=x;
		this.y=y;
	}

	public E getX() {
		return x;
	}

	public void setX(E x) {
		this.x = x;
	}

	public F getY() {
		return y;
	}

	public void setY(F y) {
		this.y = y;
	}
	
	
}
