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
		
		/**
		 * adds element to queue
		 * 
		 * @param item
		 */
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

		/**
		 * @return element at the front of the queue
		 */
	  public E peek() {
		  if (front==null) {
			  throw new IllegalStateException("peek: empty queue");
		  }
		  return front.data;
		  
	  }
		
		/**
		 * @return element removed from queue
		 */
	  public E poll() {
		  if (front==null) {
			  throw new IllegalStateException("poll: empty queue");
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
	  
	  public static void main(String[] args) {
		  Queue<String> q = new Queue<>();
		  
		  q.offer("a");
		  q.offer("b");
		  q.offer("c");
		  q.offer("d");
		  q.offer("e");

		  System.out.println(q);
		  System.out.println(q.poll());
		  System.out.println(q.poll());
		  System.out.println(q);
		  System.out.println(q.poll());
		  System.out.println(q.poll());
		  System.out.println(q.poll());
		  System.out.println(q);
		  
	  }
	  
	  
	  
 }
