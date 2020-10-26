package stacks;

public class MyStack<E> {

	public static class Node<F> {
		// Data fields
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public Node(F data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
    // Data fields
	private Node<E> top;
	private int size;
	
	// Constructor
	MyStack() {
		top=null;
		size=0;				
	}
	
	// Methods
	
	public E push(E item) {
		top = new Node<>(item,top);
		size++;
		return item;
	}
	
	public E pop() {
		if (top==null) {
			throw new IllegalStateException("pop: empty stack");
		}
		E temp = top.data;
		top = top.next;
		size--;
		return temp;		
	}
	
	public E top() {
		if (top==null) {
			throw new IllegalStateException("top: empty stack");
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	
	
 }
