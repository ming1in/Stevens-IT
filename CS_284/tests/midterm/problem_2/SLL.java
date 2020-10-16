package problem_2;

public class SLL<E> {

	public static class Pair<E,F> {
		// data fields
		private E x;
		private F y;

		// Constructor
		public Pair(E x, F y) {
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

		public String toString() {
			return "("+x.toString()+","+y.toString()+")";
		}

	}

    
	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;

		Node(F data) {
			this.data=data;
			this.next=null;
		}

		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}

	}

	// Data fields
	private Node<E> head;
	private int size;

	// Constructor
	SLL() {
		head=null;
		size=0;
	}

	// Methods
	public E addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
		return item;
	}

	// add item at the end of the list
	public E add(E item) {

		if (head==null) { // the list is empty
			head = new Node<E>(item);
			size++;
			return item;
		} 
		// the list is not empty
		Node<E> current = head;

		while (current.next!=null) {
			current = current.next;
		}
		current.next = new Node<E>(item);
		size++;
		return item;

	}	

	// add item at given index
	public E add(E item, int index) {
		if (index<0 || index > size ) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head==null) {
			addFirst(item);
			return item;
		}
		// the list is not empty
		Node<E> current = head;
		for (int i =0; i<index-1; i++) {
			current = current.next;
		}
		current.next = new Node<E>(item,current.next);
		size++;
		return item;
		
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> current=head;

		result.append("[");
		while (current!=null) {
			result.append(current.data.toString()+",");
			current = current.next;
		}
		result.append("]");

		return result.toString();
	}
	

	public static SLL<String> to_list(SLL<Pair<String,Integer>> h) {
	    // TODO
			
			Node<Pair<String, Integer>> current = h.head;
			SLL<String> result = new SLL<String>();

			while (current != null) {
				for (int i = 0; i < current.data.getY(); i++) {
					result.add(current.data.getX());
				}

				current = current.next;
			}

			return result;
	}
	public static void main(String[] args) {
		SLL<Pair<String,Integer>> l = new SLL<>();
		l.add(new Pair<>("a",1));
		l.add(new Pair<>("b",4));
		l.add(new Pair<>("c",2));
		l.add(new Pair<>("k",3));
		l.add(new Pair<>("g",2));
		System.out.println(l);
		SLL<String> l2 = to_list(l);
		
		System.out.println(l2);
		}

}
