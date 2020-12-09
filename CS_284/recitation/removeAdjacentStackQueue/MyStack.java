package recitation.removeAdjacentStackQueue;

import recitation.removeAdjacentStackQueue.Queue.Node;

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
	
	//1,1,1,1,1,1,1,2,3,4,3,3 --> 1,2,3,4,3
	public boolean removeAdjacentDuplicates()
		{
		MyStack<E> holder = new MyStack<E>();
		if(top != null)
			{holder.push(this.pop());}
		
		while(top != null)
			{
			if(this.top().equals(holder.top()))
				{
				this.pop();
				}
			else
				{
				holder.push(this.pop());
				}
			}
		while(holder.top != null)
			{this.push(holder.pop());}
		
		return true;
		}
	
	public String toString() {
		  StringBuilder s = new StringBuilder();
		  Node<E> current = top;
		  s.append(" --> ");
		  while(current!=null) {
			  s.append(current.data.toString()+" --> ");
			  current=current.next;
			  
		  }
		  return s.toString();
	  }
	
	
	public static void main(String args[])
		{
		MyStack<Integer> s1 = new MyStack<Integer>();
		s1.push(1);
		s1.push(1);
		s1.push(2);
		s1.push(2);
		s1.push(2);
		s1.push(1);
		s1.push(3);
		
		System.out.println(s1.toString());
		s1.removeAdjacentDuplicates();
		System.out.println(s1.toString());

		}
	
 }
