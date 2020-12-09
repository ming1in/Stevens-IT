package recitation.removeAdjacentStackQueue;

public class Queue<E>{

	  public static class Node<F> {
		  // data fields
		  private F data;
		  private Node<F> next;
		
		  // Constructors
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
	  private Node<E> front;
	  private Node<E> rear;
	  private int size;
	  
	  // Constuctors
	  Queue() {
		  front=null;
		  rear=null;
		  size=0;
	  }
	  
	  
	  // Methods
	  
	  public void offer(E item) {
		  if (front==null) { // Queue is empty 
			  front = new Node<E>(item);
			  rear = front;

		  } else { // Queue is non-empty
			  rear.next = new Node<E>(item);
			  rear = rear.next;
		  }
		  size++;
	  }

	  public E peek() {
		  if (front==null) {
			  throw new IllegalStateException("peek: empty queue");
		  }
		  return front.data;
		  
	  }
	  
	  public E poll() {
		  if (front==null) {
			  throw new IllegalStateException("peek: empty queue");
		  }
		  E temp = front.data;
		  if (front==rear) {
			  front = null;
			  rear = null;
		  } else {
			  front = front.next;
		  }
		  size--;
		  return temp;
	  }
	  
	  public boolean isEmpty() {
		  return (front==null);
	  }
	  
	  public String toString() {
		  StringBuilder s = new StringBuilder();
		  Node<E> current = front;
		  s.append("<--");
		  while(current!=null) {
			  s.append(current.data.toString()+"<--");
			  current=current.next;
			  
		  }
		  return s.toString();
	  }
	  
	  public boolean removeAdjacentDuplicates()
		{
		Queue<E> holder = new Queue<E>();
		if(front != null)
			{holder.offer(this.poll());}
		
		while(this.front != null)
			{
			if(this.peek().equals(holder.rear.data))
				{
				this.poll();
				}
			else
				{
				holder.offer(this.poll());
				}
			}
		
		this.front = holder.front;
		this.rear = holder.rear;
		this.size = holder.size;
		
		return true;
		}
	  
	  public static void main(String[] args) {
		  Queue<Integer> q = new Queue<Integer>();
		  
		  q.offer(1);
		  q.offer(1);
		  q.offer(2);
		  q.offer(2);
		  q.offer(2);
		  q.offer(1);
		  q.offer(3);
		  
		  System.out.println(q.toString());
		  q.removeAdjacentDuplicates();
		  System.out.println(q.toString());

		  
		  
		  
	  }
	  
	  
	  
 }
